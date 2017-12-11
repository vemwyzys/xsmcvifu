package com.pandapay.controller.wap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iqmkj.utils.MD5Util;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.DO.UserDO;
import com.pandapay.interceptor.UserWapInterceptor;
import com.pandapay.service.IUserService;

/**
 * 登录
 * @author zjl
 *
 */
@Controller
@RequestMapping("/userWap/wapLogin")
@Scope(value="prototype")
public class WapLoginCotroller {
	
	/** 用户信息 */
	@Autowired
	private IUserService userService;
	
	/** 显示主页 */
	@RequestMapping(value = "/show.htm")
	public String show(HttpServletRequest request){
		return "page/wap/MyJsp";
	}
	
	@RequestMapping(value = "")
	public String openLogin(HttpServletRequest request){
		return "page/wap/wapLogin";
	}
	
	/** 登录 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request){
		
		String userAccount = StringUtil.stringNullHandle(request.getParameter("userAccount"));
		String password = StringUtil.stringNullHandle(request.getParameter("password"));
		
		request.setAttribute("userAccount", userAccount);
		
		if(!StringUtil.isNotNull(userAccount) || !StringUtil.isNotNull(password)){
			request.setAttribute("code", -1);
			request.setAttribute("message", "参数错误！");
			return "page/wap/wapLogin";
		}
		
		password = MD5Util.toMd5(password);
		UserDO userDO = userService.validateUserLogin(userAccount, password);
		if(userDO == null){
			request.setAttribute("code", -1);
			request.setAttribute("message", "用户名或密码错误！");
			return "page/wap/wapLogin";
		}
		
		UserWapInterceptor.loginSuccess(request, userDO);
		
		request.setAttribute("code", 1);
		request.setAttribute("message", "登录成功！");
		return "page/wap/index";
	}
	
	/** 注册 */
	@RequestMapping(value = "/registe.htm")
	public String registe(HttpServletRequest request){
		return "page/wap/MyJsp";
	}
	
	/** 找回密码  */
	@RequestMapping(value = "/findPassword.htm")
	public String findPassword(HttpServletRequest request){
		return "page/wap/MyJsp";
	}
}
