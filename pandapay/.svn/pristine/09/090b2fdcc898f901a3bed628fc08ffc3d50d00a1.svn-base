package com.pandapay.service;

import java.util.List;

import com.pandapay.entity.DO.BackerDO;

/**
 * 后台管理员
 * @author whx
 *
 */
public interface IBackerService {

	/**
	 * 新增后台管理员
	 * @param backer
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean insertBacker(BackerDO backer);
	
	/**
	 * 查询后台管理员信息
	 * @param backerId 后台管理员Id
	 * @return 操作成功：返回管理员信息，失败：返回null
	 */
	public BackerDO queryBackerByBackerId(int backerId);
	
	/**
	 * 查询后台管理员总数
	 * @param backerAccount 后台管理员账号
	 * @return 操作成功：返回后台管理员总数，失败：返回0
	 */
	public int queryBackerTotal(String backerAccount);
	
	/**
	 * 查询后台管理员列表信息
	 * @param backerAccount 后台管理员账号
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回管理员列表信息，失败：返回null
	 */
	public List<BackerDO> queryBackerList(String backerAccount, int pageNumber, int pageSize);
	
	/**
	 * 验证后台管理员账号是否存在
	 * @param backerAccount 后台管理员账号
	 * @return 存在：返回true，不存在：返回false
	 */
	public boolean validateBackerIsExist(String backerAccount);
	
	/**
	 * 验证后台管理员登录
	 * @param backerAccount 后台管理员账号
	 * @param password 后台管理员密码
	 * @return 操作成功：返回后台管理员信息，失败：返回null
	 */
	public BackerDO validateBakcerLogin(String backerAccount, String password);	
	
	/**
	 * 修改后台管理员密码
	 * @param backerId 后台管理员Id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateBackerPasswoed(int backerId, String oldPassword, String newPassword);
	
	/**
	 * 重置后台管理员密码
	 * @param backerId 后台管理员Id
	 * @param newPassword 新密码
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateResetBackerPassword(int backerId, String newPassword);
	
	/**
	 * 修改后台管理员角色
	 * @param backerId 后台管理员Id
	 * @param roleId 角色Id
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateBackerRole(int backerId, int roleId);
	
	/**
	 * 删除后台管理员
	 * @param backerId 后台管理员Id
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean deleteBackerByBackerId(int backerId);
	
	/**
	 * 查询角色下的管理员数量（主要用于删除角色时判断）
	 * @param roleId 角色Id
	 * @return 操作成功：返回总数，失败：返回0
	 */
	public int queryBackerNumberByRoleId(int roleId);
	
}
