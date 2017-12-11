package com.pandapay.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.IUserRecordAmountDao;
import com.pandapay.entity.DO.UserRecordAmountDO;

/**
 * 用户账户金额记录
 * @author gql
 *
 */
@Repository
public class UserRecordAmountDaoImpl implements IUserRecordAmountDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增账户金额记录
	 * @param userRecordAmount 账户金额记录
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertRecord(UserRecordAmountDO userRecordAmount){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("UserRecordAmount_insertRecord", userRecordAmount);
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
	 * 查询账户金额记录总数（用户操作）
	 * @param userId 用户Id
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryRecordTotalOfUser(int userId){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("UserRecordAmount_queryRecordTotalOfUser", userId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
	/**
	 * 查询账户金额记录列表（用户操作）
	 * @param userId 用户Id
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回记录列表，操作失败：返回null
	 */
	public List<UserRecordAmountDO> queryRecordListOfUser(int userId, int pageNumber, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		
		map.put("startNumber", pageNumber*pageSize);
		map.put("pageSize", pageSize);
		
		List<UserRecordAmountDO> resultList = null;
		try{
			resultList = sqlSessionTemplate.selectList("UserRecordAmount_queryRecordListOfUser", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultList;
	}
	
}
