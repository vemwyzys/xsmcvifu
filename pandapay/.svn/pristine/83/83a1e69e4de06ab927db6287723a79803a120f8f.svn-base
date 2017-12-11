package com.pandapay.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iqmkj.utils.CheckMobile;

/**
 * 访问分发
 * @author gql
 *
 */
@Controller
@RequestMapping("")
@Scope(value="prototype")
public class VisitCotroller {
	
	/**
	 * 根目录访问
	 */
	@RequestMapping(value = "")
	public String visitPage(HttpServletRequest request){
		boolean isPhone = CheckMobile.check(request);
		if(isPhone){
			return "redirect:/wapLogin";
		}else{
			return "redirect:/backLogin";
		}
	}
	
	/**
	 * 后台管理员登录
	 */
	@RequestMapping(value = "/backLogin")
	public String backLogin(){
		return "page/back/login";
	}
	
	/**
	 * 用户WAP端登录
	 */
	@RequestMapping(value = "/wapLogin")
	public String wapLogin(){
		return "page/wap/wapLogin";
	}
	
}
