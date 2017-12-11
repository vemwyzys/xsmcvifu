package com.pandapay.service;

import com.pandapay.entity.DO.UserSessionDO;

/**
 * 用户登录记录
 * @author whx
 *
 */
public interface IUserSessionService {

	/**
	 * 新增用户登录记录
	 * @param userSession 用户登录记录
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean insertUserSession(UserSessionDO userSession);
	
	/**
	 * 查询用户登录记录信息
	 * @param sessionId sessionId
	 * @return 操作成功：返回用户登录记录信息，失败：返回null
	 */
	public UserSessionDO queryUserSession(String sessionId);
	
}
