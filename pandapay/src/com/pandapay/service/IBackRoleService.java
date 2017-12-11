package com.pandapay.service;

import java.util.List;

import com.pandapay.entity.DO.BackRoleDO;

/**
 * 系统角色
 * @author whx
 *
 */
public interface IBackRoleService {

	/**
	 * 新增系统角色信息
	 * @param backRole 系统角色信息
	 * @return 操作成功：返回系统角色信息，失败：返回null
	 */
	public BackRoleDO insertBackRole(BackRoleDO backRole);
	
	/**
	 * 新增系统角色
	 * @param backRole 系统角色信息
	 * @param powerIdList 权限列表
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean addBackRole(BackRoleDO backRole, List<Integer> powerIdList);
	
	/**
	 * 查询系统角色信息
	 * @param roleId 角色Id
	 * @return 操作成功：返回系统角色信息，失败：返回null
	 */
	public BackRoleDO queryBackRole(int roleId);
	
	/**
	 * 查询系统角色总数
	 * @param roleName 角色名称，查询全部填null
	 * @return 操作成功：返回系统角色总数，失败：返回0
	 */
	public int queryBackRoleTotal(String roleName);
	
	/**
	 * 查询系统角色列表
	 * @param roleName 角色名称，查询全部填null
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回系统角色信息列表，失败：返回null
	 */
	public List<BackRoleDO> queryBackRoleList(String roleName, int pageNumber, int pageSize);	
	
	/**
	 * 修改系统角色名称
	 * @param roleId 角色Id
	 * @param roleName 角色名称
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateBackRoleName(int roleId, String roleName);
	
	/**
	 * 修改系统角色
	 * @param roleId 角色Id
	 * @param roleName 角色名称
	 * @param powerIdList 权限列表
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateBackRole(int roleId, String roleName, List<Integer> powerIdList);
	
	/**
	 * 删除系统角色
	 * @param backId 角色Id
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean deleteBackRole(int roleId);
	
}
