package com.pandapay.service;

import java.util.List;

import com.pandapay.entity.DO.BackRolePowerDO;
import com.pandapay.entity.DTO.BackRolePowerDTO;

/**
 * 角色权限
 * @author whx
 *
 */
public interface IBackRolePowerService {

	/**
	 * 新增角色权限
	 * @param backRolePowerList 角色权限信息列表
	 * @return 操作成功：返回新增成功数量：失败：返回0
	 */
	public int insertBackRolePower(List<BackRolePowerDO> backRolePowerList);
	
	/**
	 * 查询角色拥有的权限
	 * @param roleId 角色Id
	 * @return 操作成功：返回权限Id列表，失败：返回null
	 */
	public List<Integer> queryPowerIdByRoleId(int roleId);
	
	/**
	 * 查询角色拥有的权限（角色展示）
	 * @param roleId 角色Id
	 * @return 操作成功：返回角色权限列表，失败：返回null
	 */
	public List<BackRolePowerDTO> queryBackRolePowerDTOList(int roleId);
	
	/**
	 * 验证角色是否拥有该权限
	 * @param roleId 角色Id
	 * @param powerId 权限Id
	 * @return 拥有该权限：返回true，未拥有该权限：返回false
	 */
	public boolean validateBackRolePower(int roleId, int powerId);
	
	/**
	 * 删除角色下的所有权限（删除角色）
	 * @param roleId 角色Id
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean deleteAllBackRolePowerByRoleId(int roleId);
	
}
