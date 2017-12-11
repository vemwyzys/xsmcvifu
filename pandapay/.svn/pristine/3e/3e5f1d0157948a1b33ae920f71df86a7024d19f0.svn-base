package com.pandapay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandapay.dao.IBackRolePowerDao;
import com.pandapay.entity.DO.BackRolePowerDO;
import com.pandapay.entity.DTO.BackRolePowerDTO;
import com.pandapay.service.IBackRolePowerService;

/**
 * 角色权限
 * @author whx
 *
 */
@Service("backRolePowerService")
public class BackRolePowerServiceImpl implements IBackRolePowerService{

	/** 角色权限 */
	@Autowired
	private IBackRolePowerDao backRolePowerDao;

	/**
	 * 新增角色权限
	 * @param backRolePowerList 角色权限信息列表
	 * @return 操作成功：返回新增成功数量：失败：返回0
	 */
	public int insertBackRolePower(List<BackRolePowerDO> backRolePowerList){
		return backRolePowerDao.insertBackRolePower(backRolePowerList);
	}
	
	/**
	 * 查询角色拥有的权限
	 * @param roleId 角色Id
	 * @return 操作成功：返回权限Id列表，失败：返回null
	 */
	public List<Integer> queryPowerIdByRoleId(int roleId){
		return backRolePowerDao.queryPowerIdByRoleId(roleId);
	}
	
	/**
	 * 查询角色拥有的权限（角色展示）
	 * @param roleId 角色Id
	 * @return 操作成功：返回角色权限列表，失败：返回null
	 */
	public List<BackRolePowerDTO> queryBackRolePowerDTOList(int roleId){
		return backRolePowerDao.queryBackRolePowerDTOList(roleId);
	}
	
	/**
	 * 验证角色是否拥有该权限
	 * @param roleId 角色Id
	 * @param powerId 权限Id
	 * @return 拥有该权限：返回true，未拥有该权限：返回false
	 */
	public boolean validateBackRolePower(int roleId, int powerId){
		return backRolePowerDao.validateBackRolePower(roleId, powerId);
	}
	
	/**
	 * 删除角色下的所有权限（删除角色）
	 * @param roleId 角色Id
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean deleteAllBackRolePowerByRoleId(int roleId){
		return backRolePowerDao.deleteAllBackRolePowerByRoleId(roleId);
	}
	
}
