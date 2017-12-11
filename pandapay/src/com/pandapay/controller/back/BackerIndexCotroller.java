package com.pandapay.controller.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理主页
 * @author gql
 *
 */
@Controller
@RequestMapping("/backerWeb/backerIndex")
@Scope(value="prototype")
public class BackerIndexCotroller {
	
	/** 显示主页 */
	@RequestMapping(value = "/show.htm")
	public String show(HttpServletRequest request){
		request.setAttribute("code", 1);
		request.setAttribute("message", "查询成功");
		
		//当前页面的权限标识
        request.getSession().setAttribute("backer_pagePowerId", 0);
		return "page/back/index";
	}
	
}
