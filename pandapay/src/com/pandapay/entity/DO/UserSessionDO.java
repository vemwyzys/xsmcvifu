package com.pandapay.entity.DO;

import java.sql.Timestamp;

/**
 * 用户登录记录
 * @author 豆豆
 *
 */
public class UserSessionDO {

	private String sessionId;  //sessionId,时间标识（14位）+随机数（11位）
	private int userId;  //用户Id
	private String userAccount;  //用户帐号
	private int loginFrom;  //登录来源,1:wap,2:web,3:android,4:IOS,5:weixin
	private String ipAddress;  //操作时的ip地址
	private Timestamp addTime;  //登录时间

	/**
	 * sessionId,时间标识（14位）+随机数（11位）
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * sessionId,时间标识（14位）+随机数（11位）
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * 用户Id
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * 用户Id
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 用户帐号
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * 用户帐号
	 * @param userAccount the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * 登录来源,1:wap,2:web,3:android,4:IOS,5:weixin
	 * @return the loginFrom
	 */
	public int getLoginFrom() {
		return loginFrom;
	}

	/**
	 * 登录来源,1:wap,2:web,3:android,4:IOS,5:weixin
	 * @param loginFrom the loginFrom to set
	 */
	public void setLoginFrom(int loginFrom) {
		this.loginFrom = loginFrom;
	}

	/**
	 * 操作时的ip地址
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * 操作时的ip地址
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * 登录时间
	 * @return the addTime
	 */
	public Timestamp getAddTime() {
		return addTime;
	}

	/**
	 * 登录时间
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}
