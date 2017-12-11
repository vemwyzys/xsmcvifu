package com.pandapay.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.IBackRoleDao;
import com.pandapay.entity.DO.BackRoleDO;

/**
 * 系统角色
 * @author whx
 *
 */
@Repository
public class BackRoleDaoImpl implements IBackRoleDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增系统角色信息
	 * @param backRole 系统角色信息
	 * @return 操作成功：返回系统角色信息，失败：返回null
	 */
	public BackRoleDO insertBackRole(BackRoleDO backRole){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("BackRole_insertBackRole", backRole);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return backRole;
		}else{
			return null;
		}
	}
	
	/**
	 * 查询系统角色信息
	 * @param roleId 角色Id
	 * @return 操作成功：返回系统角色信息，失败：返回null
	 */
	public BackRoleDO queryBackRole(int roleId){
		BackRoleDO backRole = null;
		try{
			backRole = sqlSessionTemplate.selectOne("BackRole_queryBackRole", roleId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return backRole;
	}
	
	/**
	 * 查询系统角色总数
	 * @param roleName 角色名称，查询全部填null
	 * @return 操作成功：返回系统角色总数，失败：返回0
	 */
	public int queryBackRoleTotal(String roleName){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleName", roleName);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("BackRole_queryBackRoleTotal", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
	/**
	 * 查询系统角色列表
	 * @param roleName 角色名称，查询全部填null
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回系统角色信息列表，失败：返回null
	 */
	public List<BackRoleDO> queryBackRoleList(String roleName, int pageNumber, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startNumber", pageNumber*pageSize);
		map.put("pageSize", pageSize);
		
		map.put("roleName", roleName);
		
		List<BackRoleDO> backRoleList = null;
		try{
			backRoleList = sqlSessionTemplate.selectList("BackRole_queryBackRoleList", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return backRoleList;
	}
	
	/**
	 * 修改系统角色名称
	 * @param roleId 角色Id
	 * @param roleName 角色名称
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateBackRoleName(int roleId, String roleName){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", roleId);
		map.put("roleName", roleName);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("BackRole_updateBackRoleName", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 删除系统角色
	 * @param roleId 角色Id
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean deleteBackRole(int roleId){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.delete("BackRole_deleteBackRole", roleId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
}
