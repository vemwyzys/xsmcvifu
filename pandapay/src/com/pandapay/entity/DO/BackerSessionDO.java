package com.pandapay.entity.DO;

import java.sql.Timestamp;

/**
 * 管理员登录记录
 * @author gql
 *
 */
public class BackerSessionDO {
	
	private String sessionId;  //sessionId,时间标识（14位）+随机数（11位）
	private int backerId;  //管理员Id
	private String backerAccount;  //管理员帐号
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
	 * 管理员Id
	 * @return the backerId
	 */
	public int getBackerId() {
		return backerId;
	}
	
	/**
	 * 管理员Id
	 * @param backerId the backerId to set
	 */
	public void setBackerId(int backerId) {
		this.backerId = backerId;
	}
	
	/**
	 * 管理员帐号
	 * @return the backerAccount
	 */
	public String getBackerAccount() {
		return backerAccount;
	}
	
	/**
	 * 管理员帐号
	 * @param backerAccount the backerAccount to set
	 */
	public void setBackerAccount(String backerAccount) {
		this.backerAccount = backerAccount;
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
