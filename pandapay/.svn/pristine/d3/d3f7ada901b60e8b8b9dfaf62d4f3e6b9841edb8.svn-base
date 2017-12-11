package com.pandapay.controller.wap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pandapay.entity.BO.UserSessionBO;
import com.pandapay.interceptor.UserWapInterceptor;
import com.pandapay.service.IUserService;

/**
 * 我的推广码
 * @author XEX
 *
 */
@Controller
@RequestMapping("/userWap/wapPromotionCode")
@Scope(value="prototype")
public class WapPromotionCodeCotroller {
	
	/** 显示主页 */
	@RequestMapping(value = "/show.htm")
	public String show(HttpServletRequest request){
		UserSessionBO userSessionBO = UserWapInterceptor.getUser(request);
		//TODO
		request.setAttribute("userSessionBO", userSessionBO);
		return "page/wap/MyJsp";
	}
	
}
