package com.pandapay.controller.back;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.MD5Util;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.DO.BackRoleDO;
import com.pandapay.entity.DO.BackerDO;
import com.pandapay.interceptor.BackerWebInterceptor;
import com.pandapay.service.IBackRoleService;
import com.pandapay.service.IBackerService;

/**
 * 管理员管理
 * @author 豆豆
 *
 */
@Controller
@RequestMapping("/backerWeb/backerManage")
@Scope(value="prototype")
public class BackerManageController {

	/**后台管理员*/
	@Autowired
	private IBackerService backerService;
	
	/**系统角色*/
	@Autowired
	private IBackRoleService backRoleService;
	
	/**
	 * 列表查询
	 */
	public void showList(HttpServletRequest request){
		String backerAccount = StringUtil.stringNullHandle(request.getParameter("queryAccount"));
		String pageNumberStr = StringUtil.stringNullHandle(request.getParameter("pageNumber"));
		
		int pageNumber = 0;
		if (StringUtil.isNotNull(pageNumberStr)) {
			pageNumber  = Integer.parseInt(pageNumberStr);
		}
		
		//单页数据条数
		int pageSize = 20;
		long totalNumber = backerService.queryBackerTotal(backerAccount);
		if (totalNumber <= 0) {
			pageNumber = 0;
		}
		//总页码数
		int totalPageNumber = (int) (totalNumber/1.0/pageSize);
		//若页码总数恰为页码大小的整数倍，则总数减一
		if (totalNumber > 0 && Math.ceil(totalNumber/1.0/pageSize) == totalPageNumber) {
			totalPageNumber--;
		}
		
		List<BackerDO> backerList = null;
		List<BackRoleDO> backRoleList = backRoleService.queryBackRoleList(null, 0, 100);;
		if (totalNumber > 0) {
			backerList = backerService.queryBackerList(backerAccount, pageNumber, pageSize);
		}
		
		request.setAttribute("backerList", backerList);
		request.setAttribute("backRoleList",backRoleList);
		
		request.setAttribute("totalNumber", totalNumber);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("totalPageNumber", totalPageNumber);
		
		request.setAttribute("queryAccount", backerAccount);
		//添加当前页面，页面权限码
		request.getSession().setAttribute("backer_pagePowerId", 171100);
	}
	
	/**
	 * 列表展示
	 */
	@RequestMapping("/show.htm")
	public String show(HttpServletRequest request){
		boolean result = BackerWebInterceptor.validatePower(request, 171101);
		if (!result) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限！");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/index";
		}
		
		showList(request);
		request.setAttribute("code", 1);
		request.setAttribute("message", "查询成功！");
		
		return "page/back/backerManage";
	}
	
	/**
	 * 添加管理员
	 */
	@RequestMapping(value="/addBacker.htm", method = RequestMethod.POST)
	public String addBacker(HttpServletRequest request){
		boolean result = BackerWebInterceptor.validatePower(request, 171102);
		if (!result) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限！");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/index";
		}
		
		String backerAccount = StringUtil.stringNullHandle(request.getParameter("addBackerAccount"));
		String password = StringUtil.stringNullHandle(request.getParameter("addPassword"));
		String addRoleIdStr = StringUtil.stringNullHandle(request.getParameter("addRole"));
		
		if (!StringUtil.isNotNull(backerAccount) || !StringUtil.isNotNull(password) || !StringUtil.isNotNull(addRoleIdStr)) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			showList(request);
			return "page/back/backerManage";
		}
		
	    boolean resultExist = backerService.validateBackerIsExist(backerAccount);
	    if (resultExist) {
	    	request.setAttribute("code", 2);
			request.setAttribute("message", "帐号已存在");
			showList(request);
			return "page/back/backerManage";
		}
		
		int roleId = Integer.parseInt(addRoleIdStr);
		
		BackerDO backer = new BackerDO();
		backer.setBackerAccount(backerAccount);
		backer.setPassword(MD5Util.toMd5(password));
		backer.setRoleId(roleId);
		backer.setAddTime(DateUtil.getCurrentTime());
		
		boolean resultBoo = backerService.insertBacker(backer);
		if (resultBoo) {
			request.setAttribute("code", 1);
			request.setAttribute("message", "操作成功");
		}else {
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作失败");
		}
		
		showList(request);
		return "page/back/backerManage";
	}
	
	/**
	 * 修改管理员角色
	 */
	@RequestMapping(value="/roleModify.htm", method = RequestMethod.POST)
	public String modifyRole(HttpServletRequest request){
		boolean result = BackerWebInterceptor.validatePower(request, 171103);
		if (!result) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限！");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/index";
		}
		
		String backerIdStr = StringUtil.stringNullHandle(request.getParameter("modify_backerId"));
		String newRoleIdStr = StringUtil.stringNullHandle(request.getParameter("modify_roleId"));
		
		if (!StringUtil.isNotNull(backerIdStr) || !StringUtil.isNotNull(newRoleIdStr)) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			showList(request);
			return "page/back/backerManage";
		}
		
		int backerId = Integer.parseInt(backerIdStr);
		int newRoleId = Integer.parseInt(newRoleIdStr);
		
		BackerDO backer = backerService.queryBackerByBackerId(backerId);
		if (backer == null) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			showList(request);
			return "page/back/backerManage";
		}
		
		boolean resultBoo = backerService.updateBackerRole(backerId, newRoleId);
		if (resultBoo) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作成功");
		}else{
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作失败");
		}
		
		showList(request);
		return "page/back/backerManage";
	}
	
	/**
	 * 重置密码
	 */
	@RequestMapping(value="/resetPassword.htm", method=RequestMethod.POST)
	public String resetPassword(HttpServletRequest request){
		boolean result = BackerWebInterceptor.validatePower(request, 171105);
		if (!result) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限！");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/index";
		}
		
		String backerIdStr = StringUtil.stringNullHandle(request.getParameter("reset_backerId"));
		
		if (!StringUtil.isNotNull(backerIdStr)) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			showList(request);
			return "page/back/backerManage";
		}
		
		int backerId = Integer.parseInt(backerIdStr);
		
		boolean resultBoo = backerService.updateResetBackerPassword(backerId, MD5Util.toMd5("123456"));
		if (resultBoo) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作成功");
		}else {
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作失败");
		}
		
		showList(request);
		return "page/back/backerManage";
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value="/modifyPassword.htm", method=RequestMethod.POST)
	public String modifyPassword(HttpServletRequest request){
		String password =  StringUtil.stringNullHandle(request.getParameter("password"));
		String oldPasswordIn = StringUtil.stringNullHandle(request.getParameter("oldPassword_input"));
		String backerIdStr = StringUtil.stringNullHandle(request.getParameter("modifyPassword_backerId"));
		String backerAccount = StringUtil.stringNullHandle(request.getParameter("backerAccount"));
		
		if (!StringUtil.isNotNull(password) || !StringUtil.isNotNull(backerIdStr) 
				|| !StringUtil.isNotNull(backerIdStr) || !StringUtil.isNotNull(backerAccount)) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			showList(request);
			return "page/back/backerManage";
		}
		
		BackerDO result = backerService.validateBakcerLogin(backerAccount, MD5Util.toMd5(oldPasswordIn));
		if (result == null) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "原密码错误");
			showList(request);
			return "page/back/backerManage";
		}
		
		int backerId = Integer.parseInt(backerIdStr);
		
		boolean resultBoo = backerService.updateBackerPasswoed(backerId, MD5Util.toMd5(oldPasswordIn), MD5Util.toMd5(password));
		if (resultBoo) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作成功");
		}else {
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作失败");
		}
		
		showList(request);
		return "page/back/backerManage";
	}
	
	/**
	 * 删除管理员
	 */
	@RequestMapping(value="/deleteBacker.htm")
	public String deleteBacker(HttpServletRequest request){
		boolean result = BackerWebInterceptor.validatePower(request, 171104);
		if (!result) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限！");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/index";
		}
		
		String backerIdStr = StringUtil.stringNullHandle(request.getParameter("deleteBackerId"));
		
		if (!StringUtil.isNotNull(backerIdStr)) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			showList(request);
			return "page/back/backerManage";
		}
		
		int backerId = Integer.parseInt(backerIdStr);
		
		boolean resultBoo = backerService.deleteBackerByBackerId(backerId);
		
		if (resultBoo) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "删除成功");
		}else {
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作失败");
		}
		
		showList(request);
		return "page/back/backerManage";
	}
}
