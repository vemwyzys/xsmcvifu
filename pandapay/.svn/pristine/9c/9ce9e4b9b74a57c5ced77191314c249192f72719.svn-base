package com.pandapay.controller.back;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.DO.BackRoleDO;
import com.pandapay.entity.DTO.BackRolePowerDTO;
import com.pandapay.entity.VO.BackRoleVO;
import com.pandapay.interceptor.BackerWebInterceptor;
import com.pandapay.service.IBackRolePowerService;
import com.pandapay.service.IBackRoleService;
import com.pandapay.service.IBackerService;

/**
 * 后台管理员角色管理
 * @author gql
 *
 */
@Controller
@RequestMapping("/backerWeb/backerRole")
@Scope(value="prototype")
public class BackerRoleCotroller {
	
	/** 系统角色 */
	@Autowired
	private IBackRoleService backRoleService;
	
	/** 角色权限 */
	@Autowired
	private IBackRolePowerService backRolePowerService;
	
	/** 后台管理员 */
	@Autowired
	private IBackerService backerService;
	
	/** 显示列表 */
	public void showList(HttpServletRequest request){
		String roleName = StringUtil.stringNullHandle(request.getParameter("roleName"));
		String pageNumberStr = StringUtil.stringNullHandle(request.getParameter("pageNumber"));
		
		int pageNumber = 0;
		if(StringUtil.isNotNull(pageNumberStr)){
			pageNumber = Integer.parseInt(pageNumberStr);
		}
		
		int pageSize = 20;
        long totalNumber = backRoleService.queryBackRoleTotal(roleName);
        //总页码数
        int totalPageNumber = (int) (totalNumber / pageSize);
        //如果总数刚好是分页的倍数值，需要减1
        if(totalPageNumber > 0 && Math.ceil(totalNumber/1.0/pageSize) == totalPageNumber){
            totalPageNumber --;
        }
        
        List<BackRoleDO> backRoleList = null;
        if(totalNumber > 0){
        	backRoleList = backRoleService.queryBackRoleList(roleName, pageNumber, pageSize);
        }
        
        List<BackRoleVO> roleList = null;
        if(backRoleList != null && backRoleList.size() > 0){
        	roleList = new ArrayList<BackRoleVO>();
        	for (BackRoleDO backRoleDO : backRoleList) {
        		StringBuffer rolePower = new StringBuffer();
        		List<BackRolePowerDTO> rolePowerList = backRolePowerService.queryBackRolePowerDTOList(backRoleDO.getRoleId());
        		if(rolePowerList != null && rolePowerList.size() > 0){
        			//二级权限
        			List<BackRolePowerDTO> twoLevelPowerList = new ArrayList<BackRolePowerDTO>();
        			for (BackRolePowerDTO backRolePowerDTO : rolePowerList) {
						if(backRolePowerDTO.getPowerLevel() == 2){
							twoLevelPowerList.add(backRolePowerDTO);
						}
					}
        			
        			//业务权限
        			for (BackRolePowerDTO twoLevelPower : twoLevelPowerList) {
        				StringBuffer twoPower = new StringBuffer();
        				//开头
        				twoPower.append("[");
        				twoPower.append(twoLevelPower.getPowerName());
        				twoPower.append(":");
        				//中间数据
        				for (BackRolePowerDTO threeLevelPower : rolePowerList) {
    						if(threeLevelPower.getPowerLevel() == 3 && threeLevelPower.getUperPowerId() == twoLevelPower.getPowerId()){
    							twoPower.append(threeLevelPower.getPowerName() + ",");
    						}
    					}
        				//结尾
        				twoPower.deleteCharAt(twoPower.length() - 1);
        				twoPower.append("],");
        				//添加到总数据中
        				rolePower.append(twoPower.toString());
					}
        			//总数据结尾处理
        			rolePower.deleteCharAt(rolePower.length() - 1);
        		}
        		
        		BackRoleVO backRoleVO = new BackRoleVO();
        		backRoleVO.setRoleId(backRoleDO.getRoleId());
        		backRoleVO.setRoleName(backRoleDO.getRoleName());
        		backRoleVO.setRolePower(rolePower.toString());
        		backRoleVO.setAddTime(backRoleDO.getAddTime());
        		roleList.add(backRoleVO);
			}
        }
        
        request.setAttribute("roleName", roleName);
        
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("totalNumber", totalNumber);
        request.setAttribute("totalPageNumber", totalPageNumber);
        
        request.setAttribute("roleList", roleList);
        
        //当前页面的权限标识
        request.getSession().setAttribute("backer_pagePowerId", 171000);
	}
	
	/** 查询 */
	@RequestMapping(value = "/show.htm")
	public String show(HttpServletRequest request){
		//业务功能权限
		boolean havePowerBoo =  BackerWebInterceptor.validatePower(request, 171001);
		if(!havePowerBoo){
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/home";
		}
		
		showList(request);
		
		request.setAttribute("code", 1);
		request.setAttribute("message", "查询成功");
		return "page/back/roleList";
	}
	
	/** 删除角色 */
	@RequestMapping(value = "/roleDelete.htm", method = RequestMethod.POST)
	public String roleDelete(HttpServletRequest request){
		//业务功能权限
		boolean havePowerBoo =  BackerWebInterceptor.validatePower(request, 171004);
		if(!havePowerBoo){
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/home";
		}
		
		String delete_roleId = StringUtil.stringNullHandle(request.getParameter("delete_roleId"));
		
		if(!StringUtil.isNotNull(delete_roleId)){
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			showList(request);
			return "page/back/roleList";
		}
		
		int roleId = Integer.parseInt(delete_roleId);
		
		int useNumber = backerService.queryBackerNumberByRoleId(roleId);
		if(useNumber > 0){
			request.setAttribute("code", 2);
			request.setAttribute("message", "该角色已被使用不能删除");
			showList(request);
			return "page/back/roleList";
		}
		
		boolean resultBoo = backRoleService.deleteBackRole(roleId);
		if(resultBoo){
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作成功");
		}else{
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作失败");
		}
		
		showList(request);
		return "page/back/roleList";
	}
	
	/** 打开新增角色页面 */
	@RequestMapping(value = "/roleAddPage.htm")
	public String roleAddPage(HttpServletRequest request){
		//业务功能权限
		boolean havePowerBoo =  BackerWebInterceptor.validatePower(request, 171002);
		if(!havePowerBoo){
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/home";
		}
		
		request.setAttribute("code", 1);
		request.setAttribute("message", "操作成功");
		return "page/back/roleAdd";
	}
	
	/** 新增角色 */
	@RequestMapping(value = "/roleAdd.htm", method = RequestMethod.POST)
	public String roleAdd(HttpServletRequest request){
		//业务功能权限
		boolean havePowerBoo =  BackerWebInterceptor.validatePower(request, 171002);
		if(!havePowerBoo){
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/home";
		}
		
		String roleName = StringUtil.stringNullHandle(request.getParameter("add_roleName"));
		String add_powerIdList = StringUtil.stringNullHandle(request.getParameter("add_powerIdList"));
		
		if(!StringUtil.isNotNull(roleName) || !StringUtil.isNotNull(add_powerIdList)){
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			return "page/back/roleAdd";
		}
		
		List<Integer> powerIdList = new ArrayList<Integer>();
		String[] powerIdArr =  add_powerIdList.split(",");
		for (String powerIdStr : powerIdArr) {
			if(StringUtil.isNotNull(powerIdStr)){
				powerIdList.add(Integer.parseInt(powerIdStr));
			}
		}
		
		BackRoleDO backRoleDO = new BackRoleDO();
		backRoleDO.setRoleName(roleName);
		backRoleDO.setAddTime(DateUtil.getCurrentTime());
		
		boolean addBoo = backRoleService.addBackRole(backRoleDO, powerIdList);
		if(addBoo){
			showList(request);
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作成功");
			return "page/back/roleList";
		}else{
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作失败");
			return "page/back/roleAdd";
		}
	}
	
	/** 打开修改角色页面 */
	@RequestMapping(value = "/roleModifyPage.htm", method = RequestMethod.POST)
	public String roleModifyPage(HttpServletRequest request){
		//业务功能权限
		boolean havePowerBoo =  BackerWebInterceptor.validatePower(request, 171003);
		if(!havePowerBoo){
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/home";
		}
		
		String modify_roleId = StringUtil.stringNullHandle(request.getParameter("modify_roleId"));
		
		if(!StringUtil.isNotNull(modify_roleId)){
			showList(request);
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			return "page/back/roleList";
		}
		
		int roleId = Integer.parseInt(modify_roleId);
		
		BackRoleDO backRoleDO = backRoleService.queryBackRole(roleId);
		if(backRoleDO == null){
			showList(request);
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			return "page/back/roleList";
		}
		
		List<Integer> powerList = backRolePowerService.queryPowerIdByRoleId(roleId);
		
		request.setAttribute("role", backRoleDO);
		request.setAttribute("powerList", powerList);
		
		request.setAttribute("code", 1);
		request.setAttribute("message", "操作成功");
		return "page/back/roleModify";
	}
	
	/** 修改角色 */
	@RequestMapping(value = "/roleModify.htm", method = RequestMethod.POST)
	public String roleModify(HttpServletRequest request){
		//业务功能权限
		boolean havePowerBoo =  BackerWebInterceptor.validatePower(request, 171003);
		if(!havePowerBoo){
			request.setAttribute("code", 2);
			request.setAttribute("message", "您没有该权限");
			request.getSession().setAttribute("backer_pagePowerId", 0);
			return "page/back/home";
		}
		
		String modify_roleId = StringUtil.stringNullHandle(request.getParameter("modify_roleId"));
		String roleName = StringUtil.stringNullHandle(request.getParameter("modify_roleName"));
		String modify_powerIdList = StringUtil.stringNullHandle(request.getParameter("modify_powerIdList"));
		
		if(!StringUtil.isNotNull(modify_roleId) || !StringUtil.isNotNull(roleName) || !StringUtil.isNotNull(modify_powerIdList)){
			request.setAttribute("code", 2);
			request.setAttribute("message", "参数错误");
			return "page/back/roleModify";
		}
		
		int roleId = Integer.parseInt(modify_roleId);
		
		List<Integer> powerIdList = new ArrayList<Integer>();
		String[] powerIdArr =  modify_powerIdList.split(",");
		for (String powerIdStr : powerIdArr) {
			if(StringUtil.isNotNull(powerIdStr)){
				powerIdList.add(Integer.parseInt(powerIdStr));
			}
		}
		
		boolean resultBoo = backRoleService.updateBackRole(roleId, roleName, powerIdList);
		if(resultBoo){
			showList(request);
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作成功");
			return "page/back/roleList";
		}else{
			request.setAttribute("code", 2);
			request.setAttribute("message", "操作失败");
			return "page/back/roleModify";
		}
	}
	
}
