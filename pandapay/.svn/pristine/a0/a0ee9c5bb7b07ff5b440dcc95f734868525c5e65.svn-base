package com.pandapay.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.IUserSessionDao;
import com.pandapay.entity.DO.UserSessionDO;

/**
 * 用户登录记录
 * @author whx
 *
 */
@Repository
public class UserSessionDaoImpl implements IUserSessionDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增用户登录记录
	 * @param userSession 用户登录记录
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean insertUserSession(UserSessionDO userSession){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("UserSession_insertUserSession", userSession);
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
	 * 查询用户登录记录信息
	 * @param sessionId sessionId
	 * @return 操作成功：返回用户登录记录信息，失败：返回null
	 */
	public UserSessionDO queryUserSession(String sessionId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sessionId", sessionId);
		
		UserSessionDO userSession = null;
		try{
			userSession = sqlSessionTemplate.selectOne("UserSession_queryUserSession", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return userSession;
	}
	
}
