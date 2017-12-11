package com.pandapay.controller.wap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pandapay.service.IUserInviteCodeService;
import com.pandapay.service.IUserService;

/**
 * 兑换邀请码
 * @author XEX
 *
 */
@Controller
@RequestMapping("/userWap/wapExchangeInviteCode")
@Scope(value="prototype")
public class WapExchangeInviteCodeCotroller {
	
	/** 用户信息 */
	@Autowired
	private IUserService userService;
	
	/** 邀请码服务*/
	@Autowired
	private IUserInviteCodeService userInviteCodeService;
	
	/** 显示主页 */
	@RequestMapping(value = "/show.htm")
	public String show(HttpServletRequest request){
		//TODO
		//还有网页端兑换和扫一扫
		return "page/wap/MyJsp";
	}
	
}
