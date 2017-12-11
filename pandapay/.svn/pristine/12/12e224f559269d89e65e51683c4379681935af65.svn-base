package com.pandapay.controller.wap;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.BO.UserSessionBO;
import com.pandapay.entity.DO.UserInviteCodeDO;
import com.pandapay.interceptor.UserWapInterceptor;
import com.pandapay.service.IUserInviteCodeService;

/**
 * 我的邀请码
 * @author XEX
 *
 */
@Controller
@RequestMapping("/userWap/wapInviteCodeCode")
@Scope(value="prototype")
public class WapInviteCodeCotroller {
	
	/** 邀请码服务*/
	@Autowired
	private IUserInviteCodeService userInviteCodeService;
	
	/** 显示我的邀请码未使用页面  */
	@RequestMapping(value = "/noUseShow.htm")
	public String noUseShow(HttpServletRequest request){
		showList(request);
		request.setAttribute("code", 0);
		request.setAttribute("message", "查询成功");
		return "page/wap/MyJsp";
	}
	
	/** 显示我的邀请码已使用页面  */
	@RequestMapping(value = "/usedShow.htm")
	public String usedShow(HttpServletRequest request){
		showList(request);
		request.setAttribute("code", 0);
		request.setAttribute("message", "查询成功");
		return "page/wap/MyJsp";
	}
	
	/** 未使用的邀请码分页查询  */
	@RequestMapping(value = "/noUseList.htm")
	public @ResponseBody JsonObjectBO noUseList(HttpServletRequest request, @RequestBody String requestJsonString){
		JsonObjectBO responseJson = new JsonObjectBO();
		
		JSONObject requestJson = null;
		try {
			requestJson = JSONObject.parseObject(requestJsonString);
		} catch (Exception e) {
			responseJson.setCode(-1);
        	responseJson.setMessage("JSON格式错误");
			return responseJson;
		}
		
		if (requestJson == null){
			responseJson.setCode(-1);
        	responseJson.setMessage("JSON格式错误");
			return responseJson;
		}
		
		UserSessionBO userSessionBO = UserWapInterceptor.getUser(request);
		String pageNumberStr = StringUtil.stringNullHandle(requestJson.getString("pageNumber"));
		
		int pageNumber = 0;
		if(StringUtil.isNotNull(pageNumberStr)){
			pageNumber = Integer.parseInt(pageNumberStr);
		}
		
		int pageSize = 20;
        int totalNumber = userInviteCodeService.queryInviteCodeTotalOfUser(userSessionBO.getUserId(), 0);
        //总页码数
        int totalPageNumber = (int) (totalNumber/1.0 /pageSize);
        //如果总数刚好是分页的倍数值，需要减1
        if(totalPageNumber > 0 && Math.ceil(totalNumber/1.0/pageSize) == totalPageNumber){
            totalPageNumber --;
        }
        
        List<UserInviteCodeDO> userInviteCodeList = null;
        if(totalNumber > 0){
        	userInviteCodeList = userInviteCodeService.queryInviteCodeListOfUser(userSessionBO.getUserId(), 0, pageNumber, pageSize);
        }
        
        JSONObject resultJSON = new JSONObject();
        resultJSON.put("pageNumber", pageNumber);
        resultJSON.put("totalNumber", totalNumber);
        resultJSON.put("totalPageNumber", totalPageNumber);
        
        resultJSON.put("userInviteCodeList", userInviteCodeList);
		
        responseJson.setData(resultJSON);
		return responseJson;
	}
	
	/** 已使用的邀请码分页查询  */
	@RequestMapping(value = "/usedList.htm")
	public @ResponseBody JsonObjectBO usedList(HttpServletRequest request, @RequestBody String requestJsonString){
		JsonObjectBO responseJson = new JsonObjectBO();
		
		JSONObject requestJson = null;
		try {
			requestJson = JSONObject.parseObject(requestJsonString);
		} catch (Exception e) {
			responseJson.setCode(-1);
        	responseJson.setMessage("JSON格式错误");
			return responseJson;
		}
		
		if (requestJson == null){
			responseJson.setCode(-1);
        	responseJson.setMessage("JSON格式错误");
			return responseJson;
		}
		
		UserSessionBO userSessionBO = UserWapInterceptor.getUser(request);
		String pageNumberStr = StringUtil.stringNullHandle(requestJson.getString("pageNumber"));
		
		int pageNumber = 0;
		if(StringUtil.isNotNull(pageNumberStr)){
			pageNumber = Integer.parseInt(pageNumberStr);
		}
		
		int pageSize = 20;
        int totalNumber = userInviteCodeService.queryInviteCodeTotalOfUser(userSessionBO.getUserId(), 0);
        //总页码数
        int totalPageNumber = (int) (totalNumber/1.0 /pageSize);
        //如果总数刚好是分页的倍数值，需要减1
        if(totalPageNumber > 0 && Math.ceil(totalNumber/1.0/pageSize) == totalPageNumber){
            totalPageNumber --;
        }
        
        List<UserInviteCodeDO> userInviteCodeList = null;
        if(totalNumber > 0){
        	userInviteCodeList = userInviteCodeService.queryInviteCodeListOfUser(userSessionBO.getUserId(), 0, pageNumber, pageSize);
        }
        
        JSONObject resultJSON = new JSONObject();
        resultJSON.put("pageNumber", pageNumber);
        resultJSON.put("totalNumber", totalNumber);
        resultJSON.put("totalPageNumber", totalPageNumber);
        
        resultJSON.put("userInviteCodeList", userInviteCodeList);
		
        responseJson.setData(resultJSON);
		return responseJson;
	}
	
	/**
	 * 列表数据
	 */
	public void showList(HttpServletRequest request){
		UserSessionBO userSessionBO = UserWapInterceptor.getUser(request);
		String useStatusStr = StringUtil.stringNullHandle(request.getParameter("useStatus"));
		String pageNumberStr = StringUtil.stringNullHandle(request.getParameter("pageNumber"));
		
		int useStatus = 0;
		if(StringUtil.isNotNull(useStatusStr)){
			useStatus = Integer.parseInt(useStatusStr);
		}

		int pageNumber = 0;
		if(StringUtil.isNotNull(pageNumberStr)){
			pageNumber = Integer.parseInt(pageNumberStr);
		}
		
		int pageSize = 20;
        int totalNumber = userInviteCodeService.queryInviteCodeTotalOfUser(userSessionBO.getUserId(), useStatus);
        //总页码数
        int totalPageNumber = (int) (totalNumber/1.0 /pageSize);
        //如果总数刚好是分页的倍数值，需要减1
        if(totalPageNumber > 0 && Math.ceil(totalNumber/1.0/pageSize) == totalPageNumber){
            totalPageNumber --;
        }
        
        List<UserInviteCodeDO> userInviteCodeList = null;
        if(totalNumber > 0){
        	userInviteCodeList = userInviteCodeService.queryInviteCodeListOfUser(userSessionBO.getUserId(), useStatus, pageNumber, pageSize);
        }
        
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("totalNumber", totalNumber);
        request.setAttribute("totalPageNumber", totalPageNumber);
        
        request.setAttribute("userInviteCodeList", userInviteCodeList);
	}
	
}
