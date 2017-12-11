package com.pandapay.controller.wap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pandapay.service.IUserService;

/**
 * 我的（用户中心）
 * @author XEX
 *
 */
@Controller
@RequestMapping("/userWap/wapUser")
@Scope(value="prototype")
public class WapUserCotroller {
	
	/** 用户信息 */
	@Autowired
	private IUserService userService;
	
	/** 显示主页 */
	@RequestMapping(value = "/show.htm")
	public String show(HttpServletRequest request){
		return "page/wap/MyJsp";
	}
	
	/** 修改登录密码 */
	@RequestMapping(value = "/modifyPassword.htm")
	public String modifyPassword(HttpServletRequest request){
		return "page/wap/MyJsp";
	}
	
	/** 个人信息，没有认证不予显示 */
	@RequestMapping(value = "/person.htm")
	public String person(HttpServletRequest request){
		return "page/wap/MyJsp";
	}
	
}
