package com.pandapay.controller.wap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iqmkj.utils.MD5Util;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.service.IUserMessageCodeService;
import com.pandapay.service.IUserService;

/**
 * 用户注册
 * @author 豆豆
 *
 */
@Controller
@RequestMapping("/userWap/wapRegister")
@Scope(value="prototype")
public class WapRegisterController {
	
	/**用户信息*/
	@Autowired
	private IUserService userService;
	
	/**用户短信验证码*/
	@Autowired
	private IUserMessageCodeService userMessageCodeService;
	
	/**打开注册页面*/
	@RequestMapping("/show")
	public String showRegister(HttpServletRequest request){
		return "page/wap/wapRegister";
	}
	
	/**注册*/
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(HttpServletRequest request){
		String userAccount = StringUtil.stringNullHandle(request.getParameter("phone"));
		String vertifyCode = StringUtil.stringNullHandle(request.getParameter("vertifyCode"));
		String password = StringUtil.stringNullHandle(request.getParameter("password"));
		String inviteCode = StringUtil.stringNullHandle(request.getParameter("inviteCode"));
		
		if (!StringUtil.isNotNull(userAccount) || !StringUtil.isNotNull(vertifyCode) || !StringUtil.isNotNull(password)) {
			request.setAttribute("code", -1);
			request.setAttribute("message", "参数错误！");
			return "page/wap/wapRegister";
		}
		
		password = MD5Util.toMd5(password);
		boolean resultBoo = userService.validataUserAccount(userAccount);
		if (resultBoo) {
			request.setAttribute("code", -1);
			request.setAttribute("message", "该手机号已注册！");
			
			request.setAttribute("phone", userAccount);
			request.setAttribute("vertifyCode", vertifyCode);
			request.setAttribute("inviteCode", inviteCode);
			return "page/wap/wapRegister";
		}
		
		//验证验证码
		JsonObjectBO codeJson = userMessageCodeService.validateMessageCode(userAccount, vertifyCode);
		if (codeJson.getCode() != 1) {
			request.setAttribute("code", -1);
			request.setAttribute("message", codeJson.getMessage());
			
			request.setAttribute("phone", userAccount);
			request.setAttribute("inviteCode", inviteCode);
			return "page/wap/wapRegister";
		}
		
		JsonObjectBO resultJson = userService.addUser(userAccount, password, inviteCode);
		if (resultJson== null || resultJson.getCode() != 1) {
			request.setAttribute("code", -1);
			request.setAttribute("message", "注册失败！");
			
			request.setAttribute("phone", userAccount);
			request.setAttribute("vertifyCode", vertifyCode);
			request.setAttribute("inviteCode", inviteCode);
			return "page/wap/wapRegister";
		}
		
		//注册成功跳转登录页
		request.setAttribute("code", -1);
		request.setAttribute("message", "注册成功！");
		return "page/wap/wapLogin";
		
	}
	
	/**发送验证码*/
	@RequestMapping(value="/sendCode", method=RequestMethod.POST)
	public @ResponseBody JsonObjectBO verifyCode(HttpServletRequest request){
		JsonObjectBO responseJson = new JsonObjectBO();
		String phone = StringUtil.stringNullHandle(request.getParameter("phone"));
		
		if (!StringUtil.isNotNull(phone)) {
			request.setAttribute("code", -1);
			request.setAttribute("message", "请填写正确的手机号！");
			return responseJson;
		}
		
		//发送验证码
		JsonObjectBO codeJson = userMessageCodeService.addMessageCode(phone);
		if (codeJson.getCode() != 1) {
			request.setAttribute("code", -1);
			request.setAttribute("message", codeJson.getMessage());
			return responseJson;
		}
		
		responseJson.setCode(codeJson.getCode());
		responseJson.setMessage(codeJson.getMessage());
		
		return responseJson;
	}

}
