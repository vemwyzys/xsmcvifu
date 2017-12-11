package com.pandapay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandapay.dao.IUserSessionDao;
import com.pandapay.entity.DO.UserSessionDO;
import com.pandapay.service.IUserSessionService;

/**
 * 用户登录记录
 * @author whx
 *
 */
@Service("userSessionService")
public class UserSessionServiceImpl implements IUserSessionService{

	/** 用户登录记录 */
	@Autowired
	private IUserSessionDao userSessionDao;
	
	/**
	 * 新增用户登录记录
	 * @param userSession 用户登录记录
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean insertUserSession(UserSessionDO userSession){
		return userSessionDao.insertUserSession(userSession);
	}
	
	/**
	 * 查询用户登录记录信息
	 * @param sessionId sessionId
	 * @return 操作成功：返回用户登录记录信息，失败：返回null
	 */
	public UserSessionDO queryUserSession(String sessionId){
		return userSessionDao.queryUserSession(sessionId);
	}
	
}
