package com.pandapay.controller.app;

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
import com.pandapay.interceptor.UserWapInterceptor;
import com.pandapay.service.IUserOrderPayService;


/**
 * 我要收款
 * @author XEX
 *
 */
@Controller
@RequestMapping("/userWap/appReceiveMoney")
@Scope(value="prototype")
public class AppReceiveMoneyCotroller {
	
	/** 用户支付订单 */
	@Autowired
	private IUserOrderPayService userOrderPayService;
	
	/** 我要收款 */
	@RequestMapping(value = "/receiveMoney")
	public @ResponseBody JsonObjectBO receiveMoney(HttpServletRequest request, @RequestBody String requestJsonString){
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
		
		String sessionId = StringUtil.stringNullHandle(requestJson.getString("sessionId"));
		double payAmount = requestJson.getDoubleValue("payAmount");
		int payWay = requestJson.getIntValue("payWay");//支付渠道,1：支付宝，2：微信
		
    	if(!StringUtil.isNotNull(sessionId) 
    			|| payAmount <= 0 
    			|| payAmount > 1000000
    			|| payWay <= 0 
    			|| payWay > 2){
    		responseJson.setCode(2);
        	responseJson.setMessage("参数错误");
			return responseJson;
    	}
    	
    	UserSessionBO user = UserWapInterceptor.getUser(sessionId);
    	if(user == null){
    		responseJson.setCode(-1);
        	responseJson.setMessage("登录过期，请重新登录");
			return responseJson;
    	}
    	
		JsonObjectBO userOrderPay = userOrderPayService.addPayOrder(user.getUserId(), payAmount, payWay);
		if (userOrderPay.getCode() != 1){
			responseJson.setCode(201);
        	responseJson.setMessage(userOrderPay.getMessage());
			return responseJson;
		}
		
		responseJson.setCode(1);
    	responseJson.setMessage(userOrderPay.getMessage());
    	responseJson.setData(userOrderPay.getData());
		return responseJson;
	}
	
}
