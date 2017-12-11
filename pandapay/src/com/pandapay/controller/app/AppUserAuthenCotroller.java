package com.pandapay.controller.app;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.FileWriteRemoteUtil;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.BO.UserSessionBO;
import com.pandapay.entity.DO.SystemBankDO;
import com.pandapay.entity.DO.UserAuthenDO;
import com.pandapay.interceptor.UserWapInterceptor;
import com.pandapay.service.ISystemBankService;
import com.pandapay.service.IUserAuthenService;

import config.FileUrlConfig;


/**
 * 实名认证
 * @author XEX
 *
 */
@Controller
@RequestMapping("/userWap/appUserAuthen")
@Scope(value="prototype")
public class AppUserAuthenCotroller {
	
	/** 用户认证信息 */
	@Autowired
	private IUserAuthenService userAuthenService;
	
	/** 银行对照表 */
	@Autowired
	private ISystemBankService systemBankService;
	
	/** 实名认证 */
	@RequestMapping(value = "/userAuthen")
	public @ResponseBody JsonObjectBO userAuthen(HttpServletRequest request, @RequestParam("images") MultipartFile[] images, @RequestParam("data") String requestJsonString){
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
		String storeName = StringUtil.stringNullHandle(requestJson.getString("storeName"));
		String contactsName = StringUtil.stringNullHandle(requestJson.getString("contactsName"));
		String address = StringUtil.stringNullHandle(requestJson.getString("address"));
		String idCard = StringUtil.stringNullHandle(requestJson.getString("idCard"));
		String bankCard = StringUtil.stringNullHandle(requestJson.getString("bankCard"));
		String bankName = StringUtil.stringNullHandle(requestJson.getString("bankName"));
		
    	if(!StringUtil.isNotNull(sessionId) || !StringUtil.isNotNull(storeName)
    			|| !StringUtil.isNotNull(contactsName)
    			|| !StringUtil.isNotNull(address)
    			|| !StringUtil.isNotNull(idCard)
    			|| !StringUtil.isNotNull(bankCard)
    			|| !StringUtil.isNotNull(bankName)){
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
    	
    	if (images == null || images.length < 3){
    		responseJson.setCode(2);
    		responseJson.setMessage("上传图片参数错误");
    		return responseJson;
    	}
    	
    	String idCardImg = null;
    	String idCardHandleImg = null;
    	String bankCardImg = null;
    	try {
	    	for (MultipartFile multipartFile : images) {
	    		if (multipartFile == null || multipartFile.isEmpty()){
	    			continue;
	    		}
	    		if (multipartFile.getOriginalFilename().contains("idCardImg")){
					idCardImg = FileWriteRemoteUtil.uploadFile(multipartFile.getOriginalFilename(), multipartFile.getInputStream(), FileUrlConfig.file_user_authen_url);
	    		} else if (multipartFile.getOriginalFilename().contains("idCardHandleImg")){
	    			idCardHandleImg = FileWriteRemoteUtil.uploadFile(multipartFile.getOriginalFilename(), multipartFile.getInputStream(), FileUrlConfig.file_user_authen_url);
	    		} else if (multipartFile.getOriginalFilename().contains("bankCardImg")){
	    			bankCardImg = FileWriteRemoteUtil.uploadFile(multipartFile.getOriginalFilename(), multipartFile.getInputStream(), FileUrlConfig.file_user_authen_url);
	    		}
			}
    	} catch (IOException e) {
    		responseJson.setCode(403);
    		responseJson.setMessage("上传图片数据异常");
    		return responseJson;
    	}
    	
    	if(!StringUtil.isNotNull(idCardImg) || !StringUtil.isNotNull(idCardHandleImg)
    			|| !StringUtil.isNotNull(bankCardImg)){
    		responseJson.setCode(403);
    		responseJson.setMessage("上传图片数据错误");
			return responseJson;
    	}
    	
    	List<SystemBankDO> systemBankDOList = systemBankService.queryBackByBankName(bankName);
    	if (systemBankDOList == null || systemBankDOList.size() == 0 || systemBankDOList.size() > 1){
    		responseJson.setCode(402);
    		responseJson.setMessage("银行选择错误");
    		return responseJson;
    	}
    	SystemBankDO bank = systemBankDOList.get(0);
    	
    	UserAuthenDO userAuthen = new UserAuthenDO();
    	userAuthen.setUserId(user.getUserId()); //用户Id
    	userAuthen.setUserAccount(user.getUserAccount()); //用户账号
    	userAuthen.setStoreName(storeName); //店铺名称
    	userAuthen.setContactsName(contactsName); //联系人
    	userAuthen.setAddress(address); //地址
    	userAuthen.setIdCard(idCard); //身份证号
    	userAuthen.setBankCard(bankCard); //结算银行卡号
    	userAuthen.setBankName(bankName); //结算银行名称
    	userAuthen.setBankNo(bank.getBankNo()); //银行银联号
    	userAuthen.setIdCardImg(idCardImg); //身份证正面照片
    	userAuthen.setIdCardHandleImg(idCardHandleImg); //手持身份证正面照片
    	userAuthen.setBankCardImg(bankCardImg); //银行卡正面照片
    	userAuthen.setAddTime(DateUtil.getCurrentTime()); //申请时间
    	userAuthen.setAuthenStatus(0); //认证状态，0：待认证，1：认证通过，2：认证失败
    	
		boolean userAuthenFlag = userAuthenService.insertUserAuthen(userAuthen);
		if (userAuthenFlag){
			responseJson.setCode(1);
        	responseJson.setMessage("操作成功");
		} else {
			responseJson.setCode(401);
        	responseJson.setMessage("操作失败");
		}
		return responseJson;
	}
}
