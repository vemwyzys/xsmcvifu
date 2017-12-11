package com.pandapay.interceptor;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.iqmkj.config.ApplicationContextHandle;
import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.IpAddressUtil;
import com.iqmkj.utils.NumberUtil;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.BO.BackerSessionBO;
import com.pandapay.entity.DO.BackerDO;
import com.pandapay.entity.DO.BackerSessionDO;
import com.pandapay.service.IBackRolePowerService;
import com.pandapay.service.IBackerService;
import com.pandapay.service.IBackerSessionService;

import config.SessionTimeConfig;

/**
 * 后台管理员拦截器(WEB端访问)
 * @author gql
 *
 */
public class BackerWebInterceptor implements HandlerInterceptor {

	/** session记录池 */
	private static final Map<String, BackerSessionBO> sessionMap = new ConcurrentHashMap<String, BackerSessionBO>();
	
	private static IBackerService backerService;  //后台管理员服务层
	/** 后台管理员服务层 */
    private static IBackerService getBackerService(HttpServletRequest request){
    	if(backerService == null){
    		backerService = (IBackerService) ApplicationContextHandle.getBean("backerService");
    	}
    	return backerService;
    }
	
    private static IBackerSessionService backerSessionService;  //后台管理员登录记录
    /** 后台管理员登录记录 */
    private static IBackerSessionService getSessionService(HttpServletRequest request){
    	if(backerSessionService == null){
    		backerSessionService = (IBackerSessionService) ApplicationContextHandle.getBean("backerSessionService");
    	}
    	return backerSessionService;
    }
    
    private static IBackRolePowerService backRolePowerService;  //后台管理员的角色权限服务层
    /** 后台管理员的角色权限服务层 */
    private static IBackRolePowerService getRolePowerService(HttpServletRequest request){
    	if(backRolePowerService == null){
    		backRolePowerService = (IBackRolePowerService) ApplicationContextHandle.getBean("backRolePowerService");
    	}
    	return backRolePowerService;
    }
    
    
    /**
     * 在请求到达运行的方法之前，用于拦截非法请求
     * 在controlller之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controlller) throws Exception {
    	//获取请求中携带的sessionId
		String sessionId = (String) request.getSession().getAttribute("backer_WebSid");
		
		sessionId = StringUtil.stringNullHandle(sessionId);
		if(!StringUtil.isNotNull(sessionId)){
			request.setAttribute("code", -1);
			request.setAttribute("message", "登录过期，请重新登录");
			request.getRequestDispatcher("/backLogin").forward(request, response);
			return false;
		}
		
		BackerSessionBO backerSessionBO = sessionMap.get(sessionId);
		if(backerSessionBO == null){
			request.setAttribute("code", -1);
			request.setAttribute("message", "登录过期，请重新登录");
			request.getRequestDispatcher("/backLogin").forward(request, response);
			return false;
		}
		if(DateUtil.getCurrentTimeMillis() >= backerSessionBO.getOutTime()){
			loginOut(request);  //退出登录
			
			request.setAttribute("resultCode", -1);
			request.setAttribute("resultMessage", "长时间未操作，请重新登录");
			request.getRequestDispatcher("/backLogin").forward(request, response);
			return false;
		}
		
		//重新设置session失效时间
		long outTime = DateUtil.getCurrentTimeMillis() + SessionTimeConfig.SESSION_OVERTIME_BACKER;
		backerSessionBO.setOutTime(outTime);
		
		//查询后台管理员信息
		BackerDO backerDO = getBackerService(request).queryBackerByBackerId(backerSessionBO.getBackerId());
		if(backerDO == null){
			request.setAttribute("code", -1);
			request.setAttribute("message", "登录过期，请重新登录");
			request.getRequestDispatcher("/backLogin").forward(request, response);
			return false;
		}
		
		//获取角色权限
		List<Integer> rolePowerList = getRolePowerService(request).queryPowerIdByRoleId(backerDO.getRoleId());
		request.getSession().setAttribute("backer_rolePowerList", rolePowerList);
		request.getSession().setAttribute("backer_WebSid", sessionId);
		request.getSession().setAttribute("backer_backerAccount", backerDO.getBackerAccount());
		
		return true;
    }
    
    /**
	 * 记录登录成功
	 * @param request 当前管理员请求
	 * @param backerDO 后台管理员信息
	 * @return 操作成功：返回sessionId，操作失败：返回null
	 */
	public static String loginSuccess(HttpServletRequest request, BackerDO backerDO){
		Timestamp curTime = DateUtil.getCurrentTime();
		String sessionId = DateUtil.longToTimeStr(curTime.getTime(), DateUtil.dateFormat6) + NumberUtil.createNumberStr(11);
		String ipAddress = IpAddressUtil.getIpAddress(request);
		
		//登录信息
		BackerSessionDO backerSessionDO = new BackerSessionDO();
		backerSessionDO.setSessionId(sessionId);
		backerSessionDO.setBackerId(backerDO.getBackerId());
		backerSessionDO.setBackerAccount(backerDO.getBackerAccount());
		backerSessionDO.setIpAddress(ipAddress);
		backerSessionDO.setAddTime(curTime);
		
		//向数据库添加登录记录
		boolean addBoo = getSessionService(request).insertBackerSession(backerSessionDO);
		if(addBoo){
			//session缓存信息
			long outTime = curTime.getTime() + SessionTimeConfig.SESSION_OVERTIME_BACKER;
			BackerSessionBO backerSessionBO = new BackerSessionBO();
			backerSessionBO.setBackerId(backerDO.getBackerId());
			backerSessionBO.setBackerAccount(backerDO.getBackerAccount());
			backerSessionBO.setOutTime(outTime);
			sessionMap.put(sessionId, backerSessionBO);
			
			request.getSession().setAttribute("backer_WebSid", sessionId);
			return sessionId;
		}
		
		return null;
	}
    
    /**
	 * 获取管理员信息
	 * @param request 当前管理员请求
	 * @return 查询成功：返回数据，未查询到数据或查询失败：返回NULL
	 */
	public static BackerSessionBO getBacker(HttpServletRequest request){
		String sessionId = (String) request.getSession().getAttribute("backer_WebSid");
		
		BackerSessionBO backerSessionBO = null;
		if(StringUtil.isNotNull(sessionId)){
			backerSessionBO = sessionMap.get(sessionId);
			if(backerSessionBO != null && DateUtil.getCurrentTimeMillis() >= backerSessionBO.getOutTime()){
				//session过期
				sessionMap.remove(sessionId);
				backerSessionBO = null;
			}
		}
		return backerSessionBO;
	}
	
	/**
	 * 验证是否有该权限
	 * @param request 当前管理员请求
	 * @param powerId 权限id
	 * @return 有该权限：返回true，没有该权限：返回false
	 */
	public static boolean validatePower(HttpServletRequest request, int powerId){
		@SuppressWarnings("unchecked")
		List<Integer> rolePowerList = (List<Integer>) request.getSession().getAttribute("backer_rolePowerList");
		if(rolePowerList != null && rolePowerList.size() > 0){
			for (Integer rolePowerId : rolePowerList) {
				if(rolePowerId == powerId){
					return true;
				}
			}
		}
		return false;
	}
    
    /**
	 * 退出登录
	 * @param request 当前管理员请求
	 */
	public static void loginOut(HttpServletRequest request){
		String sessionId = (String) request.getSession().getAttribute("backer_WebSid");
		
		request.getSession().removeAttribute("backer_WebSid");
		request.getSession().removeAttribute("backer_backerAccount");
		request.getSession().removeAttribute("backer_rolePowerList");
		
		if(StringUtil.isNotNull(sessionId)){
			sessionMap.remove(sessionId);
		}
	}
	
	/**
	 * 删除失效的session缓存
	 */
	public static void deleteSession(){
		if(sessionMap.size() > 0){
			long curTime = DateUtil.getCurrentTimeMillis();
			
			Iterator<Entry<String, BackerSessionBO>> iter = sessionMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, BackerSessionBO> entry = (Map.Entry<String, BackerSessionBO>) iter.next();
				String sessionId = entry.getKey();
				BackerSessionBO backerSessionBO = entry.getValue();
				
				if(curTime >= backerSessionBO.getOutTime()){
					sessionMap.remove(sessionId);
				}
			}
		}
	}
    
	/**
	 * 用于重定向方法，这个方法可以重新返回一个新的页面，进行新的数据展示
	 * 在controlller之后
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object controlller, ModelAndView modelAndView) throws Exception {
	}
	
	/**
	 * 所有程序完成之后最终会执行的方法，一般用于销毁对象IO等操作
	 * 在postHandle之后
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object controlller, Exception exception) throws Exception {
	}
	
}
