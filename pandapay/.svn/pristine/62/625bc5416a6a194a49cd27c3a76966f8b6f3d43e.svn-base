package com.pandapay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandapay.dao.IBackerDao;
import com.pandapay.entity.DO.BackerDO;
import com.pandapay.service.IBackerService;

/**
 * 后台管理员
 * @author whx
 *
 */
@Service("backerService")
public class BackerServiceImpl implements IBackerService{

	/** 后台管理员 */
	@Autowired
	private IBackerDao backerDao;
	
	/**
	 * 新增后台管理员
	 * @param backer
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean insertBacker(BackerDO backer){
		return backerDao.insertBacker(backer);
	}
	
	/**
	 * 查询后台管理员信息
	 * @param backerId 后台管理员Id
	 * @return 操作成功：返回管理员信息，失败：返回null
	 */
	public BackerDO queryBackerByBackerId(int backerId){
		return backerDao.queryBackerByBackerId(backerId);
	}
	
	/**
	 * 查询后台管理员总数
	 * @param backerAccount 后台管理员账号
	 * @return 操作成功：返回后台管理员总数，失败：返回0
	 */
	public int queryBackerTotal(String backerAccount){
		return backerDao.queryBackerTotal(backerAccount);
	}
	
	/**
	 * 查询后台管理员列表信息
	 * @param backerAccount 后台管理员账号
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回管理员列表信息，失败：返回null
	 */
	public List<BackerDO> queryBackerList(String backerAccount, int pageNumber, int pageSize){
		return backerDao.queryBackerList(backerAccount, pageNumber, pageSize);
	}
	
	/**
	 * 验证后台管理员账号是否存在
	 * @param backerAccount 后台管理员账号
	 * @return 存在：返回true，不存在：返回false
	 */
	public boolean validateBackerIsExist(String backerAccount){
		return backerDao.validateBackerIsExist(backerAccount);
	}
	
	/**
	 * 验证后台管理员登录
	 * @param backerAccount 后台管理员账号
	 * @param password 后台管理员密码
	 * @return 操作成功：返回后台管理员信息，失败：返回null
	 */
	public BackerDO validateBakcerLogin(String backerAccount, String password){
		return backerDao.validateBakcerLogin(backerAccount, password);
	}
	
	/**
	 * 修改后台管理员密码
	 * @param backerId 后台管理员Id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateBackerPasswoed(int backerId, String oldPassword, String newPassword){
		return backerDao.updateBackerPasswoed(backerId, oldPassword, newPassword);
	}
	
	/**
	 * 重置后台管理员密码
	 * @param backerId 后台管理员Id
	 * @param newPassword 新密码
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateResetBackerPassword(int backerId, String newPassword){
		return backerDao.updateResetBackerPassword(backerId, newPassword);
	}
	
	/**
	 * 修改后台管理员角色
	 * @param backerId 后台管理员Id
	 * @param roleId 角色Id
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateBackerRole(int backerId, int roleId){
		return backerDao.updateBackerRole(backerId, roleId);
	}
	
	/**
	 * 删除后台管理员
	 * @param backerId 后台管理员Id
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean deleteBackerByBackerId(int backerId){
		return backerDao.deleteBackerByBackerId(backerId);
	}
	
	/**
	 * 查询角色下的管理员数量（主要用于删除角色时判断）
	 * @param roleId 角色Id
	 * @return 操作成功：返回总数，失败：返回0
	 */
	public int queryBackerNumberByRoleId(int roleId){
		return backerDao.queryBackerNumberByRoleId(roleId);
	}
	
}
