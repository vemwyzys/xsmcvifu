package com.pandapay.controller.back;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.DO.BackerRecordInviteCodeDO;
import com.pandapay.interceptor.BackerWebInterceptor;
import com.pandapay.service.IBackerRecordInviteCodeService;

/**
 * 发放邀请码记录
 * @author zjl
 *
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/backerWeb/backerInviteCodeRecord/")
public class BackerInviteCodeRecordController {
	
	/** 邀请码记录服务*/
	@Autowired
	private IBackerRecordInviteCodeService backerRecordInviteCodeService;
	
	/** 查询数据*/
	public void showList(HttpServletRequest request){
		
		String userAccount = StringUtil.stringNullHandle(request.getParameter("userAccount"));
		String remarks = StringUtil.stringNullHandle(request.getParameter("remarks"));
		String backerAccount = StringUtil.stringNullHandle(request.getParameter("backerAccount"));
		String startTimeStr = StringUtil.stringNullHandle(request.getParameter("startTime"));
		String endTimeStr = StringUtil.stringNullHandle(request.getParameter("endTime"));
		String pageNumberStr = StringUtil.stringNullHandle(request.getParameter("pageNumber"));
		
		Timestamp startTime = null;
		if(StringUtil.isNotNull(startTimeStr)){
			startTime = Timestamp.valueOf(startTimeStr);
		}
		
		Timestamp endTime = null;
		if(StringUtil.isNotNull(endTimeStr)){
			endTime = Timestamp.valueOf(endTimeStr);
		}
		
		int pageNumber = 0;
		if(StringUtil.isNotNull(pageNumberStr)){
			pageNumber = Integer.parseInt(pageNumberStr);
		}
		
		int pageSize = 20;
		int totalNumber = backerRecordInviteCodeService.queryRecordTotalOfBack(userAccount, remarks, backerAccount, startTime, endTime);
		//总页码数
		int totalPageNumber = (int) (totalNumber/1.0/pageSize);
		//若页码总数恰为页码大小的整数倍，则总数减一
		if (totalNumber > 0 && Math.ceil(totalNumber/1.0/pageSize) == totalPageNumber) {
			totalPageNumber--;
		}
		if(pageNumber > totalPageNumber){
			pageNumber = totalPageNumber;
		}
		
		List<BackerRecordInviteCodeDO> list = null;
		if(totalNumber > 0){
			list = backerRecordInviteCodeService.queryRecordListOfBack(userAccount, remarks, backerAccount, startTime, endTime, pageNumber, pageSize);
		}
		
		request.setAttribute("userAccount", userAccount);
		request.setAttribute("remarks", remarks);
		request.setAttribute("backerAccount", backerAccount);
		request.setAttribute("startTime", startTimeStr);
		request.setAttribute("endTime", endTimeStr);
		
		request.setAttribute("totalNumber", totalNumber);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("totalPageNumber", totalPageNumber);
		
		request.setAttribute("list", list);
		
		//添加当前页面，页面权限码
		request.getSession().setAttribute("backer_pagePowerId", 181200);
		
	}
	
	/** 显示页面*/
	@RequestMapping("show.htm")
	public String show(HttpServletRequest request){
		
		boolean result = BackerWebInterceptor.validatePower(request, 181201);
		if (!result) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限！");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/index";
		}
		
		showList(request);
		
		request.setAttribute("code", 1);
		request.setAttribute("message", "操作成功！");
		return "page/back/backerRecordInviteCode";
	}

}
