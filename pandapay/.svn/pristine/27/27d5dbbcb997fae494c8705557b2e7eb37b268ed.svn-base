package com.pandapay.interceptor;

import java.sql.Timestamp;
import java.util.Iterator;
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
import com.pandapay.entity.BO.UserSessionBO;
import com.pandapay.entity.DO.UserDO;
import com.pandapay.entity.DO.UserSessionDO;
import com.pandapay.service.IUserSessionService;

import config.SessionTimeConfig;

/**
 * 用户拦截器(WAP端访问)
 * @author gql
 *
 */
public class UserWapInterceptor implements HandlerInterceptor {

	/** session记录池 */
	private static final Map<String, UserSessionBO> sessionMap = new ConcurrentHashMap<String, UserSessionBO>();
	
	private static IUserSessionService userSessionService;  //用户登录记录
    /** 用户登录记录 */
    private static IUserSessionService getSessionService(HttpServletRequest request){
    	if(userSessionService == null){
    		userSessionService = (IUserSessionService) ApplicationContextHandle.getBean("userSessionService");
    	}
    	return userSessionService;
    }
	
    /**
     * 在请求到达运行的方法之前，用于拦截非法请求
     * 在controlller之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controlller) throws Exception {
    	//获取请求中携带的sessionId
		String sessionId = (String) request.getSession().getAttribute("user_WapSid");
		sessionId = StringUtil.stringNullHandle(sessionId);
		
		//如果session中没有sessionId，则从request中再取
		if(!StringUtil.isNotNull(sessionId)){
			sessionId = StringUtil.stringNullHandle(request.getParameter("user_WapSid"));
		}
		
		if(!StringUtil.isNotNull(sessionId)){
			request.setAttribute("code", -1);
			request.setAttribute("message", "登录过期，请重新登录");
			request.getRequestDispatcher("/wapLogin").forward(request, response);
			return false;
		}
		
		UserSessionBO userSessionBO = sessionMap.get(sessionId);
		if(userSessionBO == null){
			request.setAttribute("code", -1);
			request.setAttribute("message", "登录过期，请重新登录");
			request.getRequestDispatcher("/wapLogin").forward(request, response);
			return false;
		}
		if(DateUtil.getCurrentTimeMillis() >= userSessionBO.getOutTime()){
			loginOut(request);  //退出登录
			
			request.setAttribute("resultCode", -1);
			request.setAttribute("resultMessage", "长时间未操作，请重新登录");
			request.getRequestDispatcher("/userWap/wapLogin").forward(request, response);
			return false;
		}
		
		request.getSession().setAttribute("user_WapSid", sessionId);
		
		//重新设置session失效时间
		long outTime = DateUtil.getCurrentTimeMillis() + SessionTimeConfig.SESSION_OVERTIME_USER;
		userSessionBO.setOutTime(outTime);
		
		return true;
    }
    
    /**
	 * 记录登录成功
	 * @param request 当前用户请求
	 * @param userDO 用户信息
	 * @return 操作成功：返回sessionId，操作失败：返回null
	 */
	public static String loginSuccess(HttpServletRequest request, UserDO userDO){
		Timestamp curTime = DateUtil.getCurrentTime();
		String sessionId = DateUtil.longToTimeStr(curTime.getTime(), DateUtil.dateFormat6) + NumberUtil.createNumberStr(11);
		String ipAddress = IpAddressUtil.getIpAddress(request);
		
		//登录信息
		UserSessionDO userSessionDO = new UserSessionDO();
		userSessionDO.setSessionId(sessionId);
		userSessionDO.setUserId(userDO.getUserId());
		userSessionDO.setUserAccount(userDO.getUserAccount());
		userSessionDO.setIpAddress(ipAddress);
		userSessionDO.setAddTime(curTime);
		
		//向数据库添加登录记录
		boolean addBoo = getSessionService(request).insertUserSession(userSessionDO);
		if(addBoo){
			//session缓存信息
			long outTime = curTime.getTime() + SessionTimeConfig.SESSION_OVERTIME_USER;
			UserSessionBO userSessionBO = new UserSessionBO();
			userSessionBO.setUserId(userDO.getUserId());
			userSessionBO.setUserAccount(userDO.getUserAccount());
			userSessionBO.setOutTime(outTime);
			sessionMap.put(sessionId, userSessionBO);
			
			request.getSession().setAttribute("user_WapSid", sessionId);
			return sessionId;
		}
		
		return null;
	}
    
    /**
	 * 获取用户信息
	 * @param request 当前用户请求
	 * @return 查询成功：返回数据，未查询到数据或查询失败：返回NULL
	 */
	public static UserSessionBO getUser(HttpServletRequest request){
		String sessionId = (String) request.getSession().getAttribute("user_WapSid");
		
		UserSessionBO userSessionBO = null;
		if(StringUtil.isNotNull(sessionId)){
			userSessionBO = sessionMap.get(sessionId);
			if(userSessionBO != null && DateUtil.getCurrentTimeMillis() >= userSessionBO.getOutTime()){
				//session过期
				sessionMap.remove(sessionId);
				userSessionBO = null;
			}
		}
		return userSessionBO;
	}
	
	/**
	 * 获取用户信息
	 * @param sessionId
	 * @return 查询成功：返回数据，未查询到数据或查询失败：返回NULL
	 */
	public static UserSessionBO getUser(String sessionId){
		UserSessionBO userSessionBO = null;
		if(StringUtil.isNotNull(sessionId)){
			userSessionBO = sessionMap.get(sessionId);
			if(userSessionBO != null && DateUtil.getCurrentTimeMillis() >= userSessionBO.getOutTime()){
				//session过期
				sessionMap.remove(sessionId);
				userSessionBO = null;
			}
		}
		return userSessionBO;
	}
    
    /**
	 * 退出登录
	 * @param request 当前用户请求
	 */
	public static void loginOut(HttpServletRequest request){
		String sessionId = (String) request.getSession().getAttribute("user_WapSid");
		
		request.getSession().removeAttribute("user_WapSid");
		
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
			
			Iterator<Entry<String, UserSessionBO>> iter = sessionMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, UserSessionBO> entry = (Map.Entry<String, UserSessionBO>) iter.next();
				String sessionId = entry.getKey();
				UserSessionBO userSessionBO = entry.getValue();
				
				if(curTime >= userSessionBO.getOutTime()){
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
