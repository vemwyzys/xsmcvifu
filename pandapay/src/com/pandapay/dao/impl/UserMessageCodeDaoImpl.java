package com.pandapay.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.IUserMessageCodeDao;
import com.pandapay.entity.DO.UserMessageCodeDO;

/**
 * 用户短信验证码
 * @author 豆豆
 *
 */
@Repository
public class UserMessageCodeDaoImpl implements IUserMessageCodeDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增短信验证码
	 * @param userMessageCode 短信验证码信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertMessageCode(UserMessageCodeDO userMessageCode){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("UserMessageCode_insertMessageCode", userMessageCode);
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
	 * 查询短信验证码（最新的）
	 * @param phoneNo 手机号码
	 * @param messageCode 验证码
	 * @return 操作成功：返回短信验证码信息，操作失败：返回null
	 */
	public UserMessageCodeDO queryMessageCode(String phoneNo, String messageCode){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phoneNo", phoneNo);
		map.put("messageCode", messageCode);
		
		UserMessageCodeDO userMessageCode = null;
		try{
			userMessageCode = sqlSessionTemplate.selectOne("UserMessageCode_queryMessageCode", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return userMessageCode;
	}
	
	/**
	 * 查询今天已发短信验证码条数
	 * @param phoneNo 手机号码
	 * @param todayDate 今天凌晨的时间
	 * @return 操作成功：返回已发条数，操作失败：返回0
	 */
	public int queryTodayNum(String phoneNo, Timestamp todayDate){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phoneNo", phoneNo);
		map.put("todayDate", todayDate);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("UserMessageCode_queryTodayNum", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
	/**
	 * 修改短信验证码使用状态
	 * @param id 记录Id
	 * @param useStatus 使用状态,0:未使用,1:已使用
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateUseStatus(long id, int useStatus){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("useStatus", useStatus);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("UserMessageCode_updateUseStatus", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
}
