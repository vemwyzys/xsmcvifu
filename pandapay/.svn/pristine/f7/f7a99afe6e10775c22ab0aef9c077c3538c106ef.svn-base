package com.pandapay.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.IBackerDao;
import com.pandapay.entity.DO.BackerDO;

/**
 * 后台管理员
 * @author whx
 *
 */
@Repository
public class BackerDaoImpl implements IBackerDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增后台管理员
	 * @param backer
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean insertBacker(BackerDO backer){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("Backer_insertBacker", backer);
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
	 * 查询后台管理员信息
	 * @param backerId 后台管理员Id
	 * @return 操作成功：返回管理员信息，失败：返回null
	 */
	public BackerDO queryBackerByBackerId(int backerId){
		BackerDO backer = null;
		try{
			backer = sqlSessionTemplate.selectOne("Backer_queryBackerByBackerId", backerId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return backer;
	}
	
	/**
	 * 查询后台管理员总数
	 * @param backerAccount 后台管理员账号
	 * @return 操作成功：返回后台管理员总数，失败：返回0
	 */
	public int queryBackerTotal(String backerAccount){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("backerAccount", backerAccount);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("Backer_queryBackerTotal", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
	/**
	 * 查询后台管理员列表信息
	 * @param backerAccount 后台管理员账号
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回管理员列表信息，失败：返回null
	 */
	public List<BackerDO> queryBackerList(String backerAccount, int pageNumber, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("backerAccount", backerAccount);
		map.put("startNumber", pageNumber*pageSize);
		map.put("pageSize", pageSize);
		
		List<BackerDO> backerList = null;
		try{
			backerList = sqlSessionTemplate.selectList("Backer_queryBackerList", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return backerList;
	}
	
	/**
	 * 验证后台管理员账号是否存在
	 * @param backerAccount 后台管理员账号
	 * @return 存在：返回true，不存在：返回false
	 */
	public boolean validateBackerIsExist(String backerAccount){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("backerAccount", backerAccount);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("Backer_validateBackerIsExist", map);
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
	 * 验证后台管理员登录
	 * @param backerAccount 后台管理员账号
	 * @param password 后台管理员密码
	 * @return 操作成功：返回后台管理员信息，失败：返回null
	 */
	public BackerDO validateBakcerLogin(String backerAccount, String password){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("backerAccount", backerAccount);
		map.put("password", password);
		
		BackerDO backer = null;
		try{
			backer = sqlSessionTemplate.selectOne("Backer_validateBakcerLogin", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return backer;
	}
	
	/**
	 * 修改后台管理员密码
	 * @param backerId 后台管理员Id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateBackerPasswoed(int backerId, String oldPassword, String newPassword){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("backerId", backerId);
		map.put("oldPassword", oldPassword);
		map.put("newPassword", newPassword);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("Backer_updateBackerPasswoed", map);
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
	 * 重置后台管理员密码
	 * @param backerId 后台管理员Id
	 * @param newPassword 新密码
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateResetBackerPassword(int backerId, String newPassword){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("backerId", backerId);
		map.put("newPassword", newPassword);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("Backer_updateResetBackerPassword", map);
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
	 * 修改后台管理员角色
	 * @param backerId 后台管理员Id
	 * @param roleId 角色Id
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateBackerRole(int backerId, int roleId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("backerId", backerId);
		map.put("roleId", roleId);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("Backer_updateBackerRole", map);
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
	 * 删除后台管理员
	 * @param backerId 后台管理员Id
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean deleteBackerByBackerId(int backerId){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.delete("Backer_deleteBackerByBackerId", backerId);
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
	 * 查询角色下的管理员数量（主要用于删除角色时判断）
	 * @param roleId 角色Id
	 * @return 操作成功：返回总数，失败：返回0
	 */
	public int queryBackerNumberByRoleId(int roleId){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("Backer_queryBackerNumberByRoleId", roleId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
}
