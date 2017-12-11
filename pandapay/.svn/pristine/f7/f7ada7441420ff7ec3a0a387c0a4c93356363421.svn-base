package com.pandapay.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.IBackerRecordInviteCodeDao;
import com.pandapay.entity.DO.BackerRecordInviteCodeDO;

/**
 * 邀请码发放记录
 * @author gql
 *
 */
@Repository
public class BackerRecordInviteCodeDaoImpl implements IBackerRecordInviteCodeDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增邀请码发放记录
	 * @param backerRecordInviteCode 邀请码发放记录
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean insertRecord(BackerRecordInviteCodeDO backerRecordInviteCode){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("BackerRecordInviteCode_insertRecord", backerRecordInviteCode);
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
	 * 查询邀请码发放记录总数（后台操作）
	 * @param userAccount 用户帐号
	 * @param remarks 操作备注
	 * @param backerAccount 操作的管理员帐号
	 * @param startTime 操作时间的开始时间
	 * @param endTime 操作时间的结束时间
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryRecordTotalOfBack(String userAccount, String remarks, String backerAccount, Timestamp startTime, Timestamp endTime){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userAccount", userAccount);
		map.put("remarks", remarks);
		map.put("backerAccount", backerAccount);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("BackerRecordInviteCode_queryRecordTotalOfBack", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
	/**
	 * 查询邀请码发放记录列表（后台操作）
	 * @param userAccount 用户帐号
	 * @param remarks 操作备注
	 * @param backerAccount 操作的管理员帐号
	 * @param startTime 操作时间的开始时间
	 * @param endTime 操作时间的结束时间
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回记录列表，操作失败：返回null
	 */
	public List<BackerRecordInviteCodeDO> queryRecordListOfBack(String userAccount, String remarks, String backerAccount,
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userAccount", userAccount);
		map.put("remarks", remarks);
		map.put("backerAccount", backerAccount);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		map.put("startNumber", pageNumber*pageSize);
		map.put("pageSize", pageSize);
		
		List<BackerRecordInviteCodeDO> resultList = null;
		try{
			resultList = sqlSessionTemplate.selectList("BackerRecordInviteCode_queryRecordListOfBack", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultList;
	}
	
}
