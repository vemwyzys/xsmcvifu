package com.pandapay.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.iqmkj.utils.DateUtil;
import com.pandapay.dao.IBackRoleDao;
import com.pandapay.entity.DO.BackRoleDO;
import com.pandapay.entity.DO.BackRolePowerDO;
import com.pandapay.service.IBackRolePowerService;
import com.pandapay.service.IBackRoleService;

/**
 * 系统角色
 * @author whx
 *
 */
@Service("backRoleService")
public class BackRoleServiceImpl implements IBackRoleService{

	/** 系统角色 */
	@Autowired
	private IBackRoleDao backRoleDao;
	
	/** 角色权限 */
	@Autowired
	private IBackRolePowerService backRolePowerService;
	
	/**
	 * 新增系统角色信息
	 * @param backRole 系统角色信息
	 * @return 操作成功：返回系统角色信息，失败：返回null
	 */
	public BackRoleDO insertBackRole(BackRoleDO backRole){
		return backRoleDao.insertBackRole(backRole);
	}
	
	/**
	 * 新增系统角色
	 * @param backRole 系统角色信息
	 * @param powerIdList 权限列表
	 * @return 操作成功：返回true，失败：返回false
	 */
	@Transactional
	public boolean addBackRole(BackRoleDO backRole, List<Integer> powerIdList){
		//业务执行状态
		boolean executeSuccess = false;
		
		BackRoleDO backRoleDO = insertBackRole(backRole);
		if(backRoleDO != null && powerIdList != null && powerIdList.size() > 0){
			List<BackRolePowerDO> backRolePowerList = new ArrayList<BackRolePowerDO>();
			Timestamp curTime = DateUtil.getCurrentTime();
			for (Integer powerId : powerIdList) {
				BackRolePowerDO backRolePowerDO = new BackRolePowerDO();
				backRolePowerDO.setRoleId(backRoleDO.getRoleId());
				backRolePowerDO.setPowerId(powerId);
				backRolePowerDO.setAddTime(curTime);
				backRolePowerList.add(backRolePowerDO);
			}
			
			int addResultNum = backRolePowerService.insertBackRolePower(backRolePowerList);
			if(addResultNum == powerIdList.size()){
				executeSuccess = true;
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
	/**
	 * 查询系统角色信息
	 * @param roleId 角色Id
	 * @return 操作成功：返回系统角色信息，失败：返回null
	 */
	public BackRoleDO queryBackRole(int roleId){
		return backRoleDao.queryBackRole(roleId);
	}
	
	/**
	 * 查询系统角色总数
	 * @param roleName 角色名称，查询全部填null
	 * @return 操作成功：返回系统角色总数，失败：返回0
	 */
	public int queryBackRoleTotal(String roleName){
		return backRoleDao.queryBackRoleTotal(roleName);
	}
	
	/**
	 * 查询系统角色列表
	 * @param roleName 角色名称，查询全部填null
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回系统角色信息列表，失败：返回null
	 */
	public List<BackRoleDO> queryBackRoleList(String roleName, int pageNumber, int pageSize){
		return backRoleDao.queryBackRoleList(roleName, pageNumber, pageSize);
	}
	
	/**
	 * 修改系统角色名称
	 * @param roleId 角色Id
	 * @param roleName 角色名称
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateBackRoleName(int roleId, String roleName){
		return backRoleDao.updateBackRoleName(roleId, roleName);
	}
	
	/**
	 * 修改系统角色
	 * @param roleId 角色Id
	 * @param roleName 角色名称
	 * @param powerIdList 权限列表
	 * @return 操作成功：返回true，失败：返回false
	 */
	@Transactional
	public boolean updateBackRole(int roleId, String roleName, List<Integer> powerIdList){
		//业务执行状态
		boolean executeSuccess = false;
		
		boolean updateBackRoleName = updateBackRoleName(roleId, roleName);
		if(updateBackRoleName){
			boolean deleteRolePower = backRolePowerService.deleteAllBackRolePowerByRoleId(roleId);
			if(deleteRolePower){
				if(powerIdList != null && powerIdList.size() > 0){
					List<BackRolePowerDO> backRolePowerList = new ArrayList<BackRolePowerDO>();
					Timestamp curTime = DateUtil.getCurrentTime();
					for (Integer powerId : powerIdList) {
						BackRolePowerDO backRolePowerDO = new BackRolePowerDO();
						backRolePowerDO.setRoleId(roleId);
						backRolePowerDO.setPowerId(powerId);
						backRolePowerDO.setAddTime(curTime);
						backRolePowerList.add(backRolePowerDO);
					}
					
					int addResultNum = backRolePowerService.insertBackRolePower(backRolePowerList);
					if(addResultNum == powerIdList.size()){
						executeSuccess = true;
					}
				}
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
	/**
	 * 删除系统角色
	 * @param roleId 角色Id
	 * @return 操作成功：返回true，失败：返回false
	 */
	@Transactional
	public boolean deleteBackRole(int roleId){
		//业务执行状态
		boolean executeSuccess = false;
		
		boolean deleteRole = backRoleDao.deleteBackRole(roleId);
		if(deleteRole){
			boolean deleteRolePower = backRolePowerService.deleteAllBackRolePowerByRoleId(roleId);
			if(deleteRolePower){
				executeSuccess = true;
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
}
