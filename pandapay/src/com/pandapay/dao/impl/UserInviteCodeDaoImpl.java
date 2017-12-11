package com.pandapay.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.IUserInviteCodeDao;
import com.pandapay.entity.DO.UserInviteCodeDO;

/**
 * 邀请码
 * @author 豆豆
 *
 */
@Repository
public class UserInviteCodeDaoImpl implements IUserInviteCodeDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增邀请码
	 * @param userInviteCode 邀请码信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertInviteCode(UserInviteCodeDO userInviteCode){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("UserInviteCode_insertInviteCode", userInviteCode);
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
	 * 批量新增邀请码
	 * @param inviteCodeList 邀请码列表
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean addInviteCodeList(List<UserInviteCodeDO> inviteCodeList){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("UserInviteCode_addInviteCodeList", inviteCodeList);
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
	 * 修改邀请码使用状态
	 * @param inviteCode 邀请码
	 * @param useStatus 使用状态,0:未使用,1:已使用
	 * @param userIdUsed 使用者id
	 * @param userAccountUsed 使用者账号
	 * @param usedTime 使用时间
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateInviteCodeUseStatus(String inviteCode, int useStatus, int userIdUsed, String userAccountUsed, Timestamp usedTime){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("inviteCode", inviteCode);
		map.put("useStatus", useStatus);
		map.put("userIdUsed", userIdUsed);
		map.put("userAccountUsed", userAccountUsed);
		map.put("usedTime", usedTime);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("UserInviteCode_updateInviteCodeUseStatus", map);
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
	 * 查询邀请码信息
	 * @param inviteCode 邀请码
	 * @return 操作成功：返回邀请码信息，操作失败：返回null
	 */
	public UserInviteCodeDO queryInviteCodeByInviteCode(String inviteCode){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("inviteCode", inviteCode);
		
		UserInviteCodeDO userInviteCode = null;
		try{
			userInviteCode = sqlSessionTemplate.selectOne("UserInviteCode_queryInviteCodeByInviteCode", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return userInviteCode;
	}
	
	/**
	 * 查询邀请码总数（用户操作）
	 * @param userIdOwn 所属用户id
	 * @param useStatus 使用状态,0:未使用,1:已使用,查询全部填-1
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryInviteCodeTotalOfUser(int userIdOwn,  int useStatus){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userIdOwn", userIdOwn);
		map.put("useStatus", useStatus);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("UserInviteCode_queryInviteCodeTotalOfUser", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
	/**
	 * 查询邀请码列表（用户操作）
	 * @param userIdOwn 所属用户id
	 * @param useStatus 使用状态,0:未使用,1:已使用,查询全部填-1
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回邀请码列表，操作失败：返回null
	 */
	public List<UserInviteCodeDO> queryInviteCodeListOfUser(int userIdOwn, int useStatus, int pageNumber, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userIdOwn", userIdOwn);
		map.put("useStatus", useStatus);
		
		map.put("startNumber", pageNumber*pageSize);
		map.put("pageSize", pageSize);
		
		List<UserInviteCodeDO> resultList = null;
		try{
			resultList = sqlSessionTemplate.selectList("UserInviteCode_queryInviteCodeListOfUser", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultList;
	}
	
	/**
	 * 查询邀请码总数（后台操作）
	 * @param inviteCode 邀请码
	 * @param userAccountOwn 所属账号
	 * @param useStatus 使用状态,0:未使用,1:已使用,查询全部填-1
	 * @param startTime 使用时间的开始时间
	 * @param endTime 使用时间的结束时间
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryInviteCodeTotalOfBack(String inviteCode, String userAccountOwn, int useStatus, Timestamp startTime, Timestamp endTime){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("inviteCode", inviteCode);
		map.put("userAccountOwn", userAccountOwn);
		map.put("useStatus", useStatus);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("UserInviteCode_queryInviteCodeTotalOfBack", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
	/**
	 * 查询邀请码列表（后台操作）
	 * @param inviteCode 邀请码
	 * @param userAccountOwn 所属账号
	 * @param useStatus 使用状态,0:未使用,1:已使用,查询全部填-1
	 * @param startTime 使用时间的开始时间
	 * @param endTime 使用时间的结束时间
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回邀请码列表，操作失败：返回null
	 */
	public List<UserInviteCodeDO> queryInviteCodeListOfBack(String inviteCode, String userAccountOwn, int useStatus,
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("inviteCode", inviteCode);
		map.put("userAccountOwn", userAccountOwn);
		map.put("useStatus", useStatus);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		map.put("startNumber", pageNumber*pageSize);
		map.put("pageSize", pageSize);
		
		List<UserInviteCodeDO> resultList = null;
		try{
			resultList = sqlSessionTemplate.selectList("UserInviteCode_queryInviteCodeListOfBack", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultList;
	}
	
}
