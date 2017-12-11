package com.pandapay.controller.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.DO.SystemAppVersionDO;
import com.pandapay.service.ISystemAppVersionService;

/**
 * app更新
 * @author XEX
 *
 */
@Controller
@RequestMapping("/userWap/appUpdate")
@Scope(value="prototype")
public class AppUpdateCotroller {
	
	/** APP版本管理 */
	@Autowired
	private ISystemAppVersionService systemAppVersionService;
	
	/** 查询最新的app版本信息用于更新 */
	@RequestMapping(value = "/appUpdate")
	public @ResponseBody JsonObjectBO appUpdate(HttpServletRequest request, @RequestBody String requestJsonString){
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
		
		int versionType = requestJson.getIntValue("versionType");
		if (versionType != 1 && versionType != 2){
			responseJson.setCode(2);
        	responseJson.setMessage("参数错误");
			return responseJson;
		}
		
    	SystemAppVersionDO appVersion = systemAppVersionService.queryNewByVersionType(versionType);
    	if (appVersion == null){
    		responseJson.setCode(501);
        	responseJson.setMessage("没有查询到最新版本");
			return responseJson;
    	}
    	
    	JSONObject data = new JSONObject();
    	data.put("appVersion", appVersion);
    	
    	responseJson.setCode(1);
    	responseJson.setMessage("查询成功");
    	responseJson.setData(data);
		return responseJson;
	}
}
