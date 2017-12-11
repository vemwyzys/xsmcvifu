package com.pandapay.controller.wap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pandapay.service.ISystemAdsService;
import com.pandapay.service.IUserService;

/**
 * 首页
 * @author XEX
 *
 */
@Controller
@RequestMapping("/userWap/wapIndex")
@Scope(value="prototype")
public class WapIndexCotroller {
	
	/** 用户信息 */
	@Autowired
	private IUserService userService;
	
	/**首页广告*/
	@Autowired
	private ISystemAdsService systemAdsService;
	
	/** 显示主页 */
	@RequestMapping(value = "/show")
	public String show(HttpServletRequest request){
		return "page/wap/MyJsp";
	}
	
}
