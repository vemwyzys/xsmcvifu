package com.pandapay.controller.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.iqmkj.utils.MD5Util;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.DO.UserDO;
import com.pandapay.interceptor.UserWapInterceptor;
import com.pandapay.service.IUserService;

/**
 * 用户登录
 * @author XEX
 *
 */
@Controller
@RequestMapping("/userWap/appLogin")
@Scope(value="prototype")
public class AppLoginCotroller {
	
	/** 用户信息 */
	@Autowired
	private IUserService userService;
	
	/** 用户登录 */
	@RequestMapping(value = "/login")
	public @ResponseBody JsonObjectBO login(HttpServletRequest request, @RequestBody String requestJsonString){
		JsonObjectBO responseJson = new JsonObjectBO();
		
		JSONObject requestJson = null;
		try {
			requestJson = JSONObject.parseObject(requestJsonString);
		} catch (Exception e) {
			responseJson.setCode(3);
        	responseJson.setMessage("JSON格式错误");
			return responseJson;
		}
		
		if (requestJson == null){
			responseJson.setCode(3);
        	responseJson.setMessage("JSON格式错误");
			return responseJson;
		}
		
		String userAccount = StringUtil.stringNullHandle(requestJson.getString("userAccount"));
		String password = StringUtil.stringNullHandle(requestJson.getString("password"));
    	if(!StringUtil.isNotNull(userAccount)|| !StringUtil.isNotNull(password)){
    		responseJson.setCode(2);
        	responseJson.setMessage("参数错误");
			return responseJson;
    	}
		
    	UserDO userDO = userService.validateUserLogin(userAccount, MD5Util.toMd5(password));
    	if (userDO == null){
    		responseJson.setCode(101);
        	responseJson.setMessage("用户名或密码错误");
			return responseJson;
    	}
    	
    	String sessionId = UserWapInterceptor.loginSuccess(request, userDO);
    	if (!StringUtil.isNotNull(sessionId)){
    		responseJson.setCode(4);
        	responseJson.setMessage("获取sessionId的失败");
			return responseJson;
    	}
    	
    	JSONObject data = new JSONObject();
    	data.put("user", userDO);
    	data.put("sessionId", sessionId);
    	
    	responseJson.setCode(1);
    	responseJson.setMessage("登录成功");
    	responseJson.setData(data);
		return responseJson;
	}
}
