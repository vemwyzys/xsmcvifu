package com.pandapay.controller.back;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.FileWriteRemoteUtil;
import com.iqmkj.utils.LogUtil;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.DO.SystemAppVersionDO;
import com.pandapay.interceptor.BackerWebInterceptor;
import com.pandapay.service.ISystemAppVersionService;

import config.FileUrlConfig;

/**
 * APP版本管理
 * @author 豆豆
 *
 */
@Controller
@RequestMapping("/backerWeb/appManage")
@Scope(value="prototype")
public class SystemAppManageController {

	/**APP版本管理*/
	@Autowired
	private ISystemAppVersionService systemAppVersionService;
	
	/**列表展示*/
	public void showList(HttpServletRequest request){
		String startTimeStr = StringUtil.stringNullHandle(request.getParameter("startTime"));
		String endTimeStr = StringUtil.stringNullHandle(request.getParameter("endTime"));
		String versionTypeStr = StringUtil.stringNullHandle(request.getParameter("versionType"));
		String versionNumber = StringUtil.stringNullHandle(request.getParameter("versionNumber"));
		String pageNumberStr = StringUtil.stringNullHandle(request.getParameter("pageNumber"));
		
		Timestamp startTime = null;
		if (StringUtil.isNotNull(startTimeStr)) {
			startTime = DateUtil.stringToTimestamp(startTimeStr);
		}
		
		Timestamp endTime = null;
		if (StringUtil.isNotNull(endTimeStr)) {
			endTime = DateUtil.stringToTimestamp(endTimeStr);
		}
		
		int versionType = 0;
		if (StringUtil.isNotNull(versionTypeStr)) {
			versionType = Integer.parseInt(versionTypeStr);
		}
		
		int pageNumber = 0;
		if (StringUtil.isNotNull(pageNumberStr)) {
			pageNumber = Integer.parseInt(pageNumberStr);
		}
		
		int pageSize = 20;
		int totalNumber = systemAppVersionService.querySystemAppVersionTotalOfBack(versionType, versionNumber, startTime, endTime);
		if (totalNumber <= 0) {
			pageNumber = 0;
		}
		int totalPageNumber = totalNumber/pageSize;
		if (totalNumber > 0 && Math.ceil(totalNumber/1.0/pageSize) == totalPageNumber) {
			totalPageNumber--;
		}
		
		List<SystemAppVersionDO> list = null;
		if (totalNumber > 0) {
			list = systemAppVersionService.querySystemAppVersionListOfBack(versionType, versionNumber, startTime, endTime, totalPageNumber, pageSize);
		}
		
		request.setAttribute("appVersionList", list);
		request.setAttribute("fileServiceRootUrl", FileUrlConfig.file_visit_url);
		
		request.setAttribute("totalNumber", totalNumber);
		request.setAttribute("totalPageNumber", totalPageNumber);
		request.setAttribute("pageNumber", pageNumber);
		
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("versionType", versionType);
		request.setAttribute("versionNumber", versionNumber);
		
		request.getSession().setAttribute("backer_pagePowerId", 161000);
		
	}
	
	/**检查文件上传类型*/
	public boolean checkFile(MultipartFile file){
		String fileName = file.getOriginalFilename().toLowerCase();
		String suffix = fileName.substring(fileName.lastIndexOf('.')+1, fileName.length());
		if ("apk".equals(suffix)) {
			return true;
		}
		return false;
	}
	
	/**查询*/
	@RequestMapping("/show.htm")
	public String show(HttpServletRequest request){
		boolean reult = BackerWebInterceptor.validatePower(request, 161001);
		if (!reult) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/index";
		}
		
		showList(request);
		request.setAttribute("code", 1);
		request.setAttribute("message", "查询成功");
		return "page/back/appManage";
	}
	
	/**新增版本*/
	@RequestMapping(value="/add.htm", method=RequestMethod.POST)
	public String add(HttpServletRequest request, @RequestParam("apk_file")MultipartFile file){
		boolean result = BackerWebInterceptor.validatePower(request, 161002);
		if (!result) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/index";
		}
		
		String versionTypeStr = StringUtil.stringNullHandle(request.getParameter("add_versionType"));
		String versionNumber = StringUtil.stringNullHandle(request.getParameter("add_versionNumber")); 
		String uploadUrl = StringUtil.stringNullHandle(request.getParameter("uploadUrl"));
		String updateDescribe = StringUtil.stringNullHandle(request.getParameter("add_updateDescribe"));
		
		if (!StringUtil.isNotNull(versionNumber) || !StringUtil.isNotNull(versionTypeStr) || !StringUtil.isNotNull(uploadUrl)
				|| !StringUtil.isNotNull(updateDescribe)){
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			
			showList(request);
			return "page/back/appManage";
		}
		
		int versionType = Integer.parseInt(versionTypeStr);
		if (versionType == 1) {
			if (file == null || file.isEmpty()) {
				request.setAttribute("code", 2);
				request.setAttribute("message", "参数错误");
				
				showList(request);
				return "page/back/appManage";
			}
		}
		
		if (file != null && !file.isEmpty()) {
			if (!checkFile(file)) {
				request.setAttribute("code", 2);
				request.setAttribute("message", "上传文件格式错误，请上传APK文件");
				
				showList(request);
				return "page/back/appManage";
			}
		}
		
		SystemAppVersionDO systemAppVersion = new SystemAppVersionDO();
		systemAppVersion.setAddTime(DateUtil.getCurrentTime());
		systemAppVersion.setUpdateDescribe(updateDescribe);
		systemAppVersion.setVersionNumber(versionNumber);
		systemAppVersion.setVersionType(versionType);
		systemAppVersion.setUploadUrl(uploadUrl);
		
		if (versionType == 1) {
			if (file != null && !file.isEmpty()) {
				try {
					uploadUrl = FileWriteRemoteUtil.uploadFile(file.getOriginalFilename(), file.getInputStream(), FileUrlConfig.file_remote_apk_url);
					systemAppVersion.setUploadUrl(uploadUrl);
				} catch (IOException e) {
					LogUtil.printErrorLog(e);
				}
			}
		}
		
		boolean resultBoo = systemAppVersionService.insertSystemAppVersion(systemAppVersion);
		if (resultBoo) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "新增成功");
		}else {
			request.setAttribute("code", 2);
			request.setAttribute("message", "新增失败");
		}
		
		showList(request);
		return "page/back/appManage";
	}
	
	/**删除*/
	@RequestMapping(value="delete.htm", method=RequestMethod.POST)
	public String delete(HttpServletRequest request){
		boolean result = BackerWebInterceptor.validatePower(request, 161003);
		if (!result) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/index";
		}
		
		String versionIdStr = StringUtil.stringNullHandle(request.getParameter("deleteId"));
		String versionTypeStr = StringUtil.stringNullHandle(request.getParameter("delete_versionType"));
		
		if (!StringUtil.isNotNull(versionIdStr) || !StringUtil.isNotNull(versionTypeStr) ) {
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			
			showList(request);
			return "page/back/appManage";
		}
		
		int versionType = Integer.parseInt(versionTypeStr);
		int versionId = Integer.parseInt(versionIdStr);
		String deleteFile = "";
		if (versionType == 1) {
			SystemAppVersionDO oldApp = systemAppVersionService.queryVersionByVersionId(versionId);
			if (oldApp == null) {
				request.setAttribute("code", 2);
				request.setAttribute("message", "参数错误");
				
				showList(request);
				return "page/back/appManage";
			}
			deleteFile = oldApp.getUploadUrl();
		}
		boolean resultBoo = systemAppVersionService.deleteSystemAppVersion(versionId);
		if (resultBoo) {
			//删除APK文件
			if (deleteFile != null && deleteFile != "") {
				FileWriteRemoteUtil.deleteFile(deleteFile);
			}
			request.setAttribute("code", 2);
			request.setAttribute("message", "删除成功");
		}else {
			request.setAttribute("code", 2);
			request.setAttribute("message", "删除失败");
		}
		
		showList(request);
		return "page/back/appManage";
	}
}
