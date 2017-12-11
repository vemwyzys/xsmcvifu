package com.pandapay.entity.DO;

import java.sql.Timestamp;

/**
 * 系统角色
 * @author gql
 *
 */
public class BackRoleDO {
	
	private int roleId;  //角色Id
	private String roleName;  //角色名称
	private Timestamp addTime;  //添加时间
	
	/**
	 * 角色Id
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * 角色Id
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * 角色名称
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 角色名称
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * 添加时间
	 * @return the addTime
	 */
	public Timestamp getAddTime() {
		return addTime;
	}

	/**
	 * 添加时间
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}
