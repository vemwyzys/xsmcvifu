package com.pandapay.controller.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.code.kaptcha.Constants;
import com.iqmkj.utils.MD5Util;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.DO.BackerDO;
import com.pandapay.interceptor.BackerWebInterceptor;
import com.pandapay.service.IBackerService;

/**
 * 后台管理员登录
 * @author gql
 *
 */
@Controller
@RequestMapping("/backerWeb/backerWebLogin")
@Scope(value="prototype")
public class BackerWebLoginCotroller {
	
	/** 后台管理员 */
	@Autowired
	private IBackerService backerService;
	
	/**
	 * 登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request){
		String backerAccount = StringUtil.stringNullHandle(request.getParameter("backerAccount"));
		String password = StringUtil.stringNullHandle(request.getParameter("password"));
		String pageCode = StringUtil.stringNullHandle(request.getParameter("pageCode"));
		String sessionCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		
		request.setAttribute("backerAccount", backerAccount);
		
		if(!StringUtil.isNotNull(pageCode) || !StringUtil.isNotNull(sessionCode)){
			request.setAttribute("code", 2);
			request.setAttribute("message", "验证码错误");
			return "page/back/login";
		}
		
		pageCode = pageCode.toLowerCase();
		sessionCode = sessionCode.toLowerCase();
		if(!pageCode.endsWith(sessionCode)){
			request.setAttribute("code", 2);
			request.setAttribute("message", "验证码错误");
			return "page/back/login";
		}
		
		if(!StringUtil.isNotNull(backerAccount) || !StringUtil.isNotNull(password)){
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			return "page/back/login";
		}
		
		password = MD5Util.toMd5(password);
		
		BackerDO backerDO = backerService.validateBakcerLogin(backerAccount, password);
		if(backerDO == null){
			request.setAttribute("code", 2);
			request.setAttribute("message", "账号或密码错误");
			return "page/back/login";
		}
		
		BackerWebInterceptor.loginSuccess(request, backerDO);
		
		request.setAttribute("code", 1);
		request.setAttribute("message", "登录成功");
		
		return "redirect:/backerWeb/backerIndex/show.htm";
	}
	
	/**
	 * 退出登录
	 */
	@RequestMapping(value = "/loginOut.htm")
	public String loginOut(HttpServletRequest request){
		BackerWebInterceptor.loginOut(request);
		
		request.setAttribute("code", 2);
		request.setAttribute("message", "退出登录成功");
		return "page/back/login";
	}
	
}
