package com.pandapay.entity.DO;

import java.sql.Timestamp;

/**
 * 邀请码
 * @author 豆豆
 *
 */
public class UserInviteCodeDO {
	
	private String inviteCode; //邀请码,时间标识（14位）+随机数（11位）
	private int userIdOwn; //所属用户id
	private String userAccountOwn; //所属账号
	private Timestamp addTime; //获得时间
	private Timestamp outTime; //过期时间
	private int useStatus; //使用状态,0:未使用,1:已使用
	private int userIdUsed; //使用者id
	private String userAccountUsed; //使用者账号
	private Timestamp usedTime; //使用时间
	
	/**
	 * 邀请码,时间标识（14位）+随机数（11位）
	 * @return the inviteCode
	 */
	public String getInviteCode() {
		return inviteCode;
	}
	
	/**
	 * 邀请码,时间标识（14位）+随机数（11位）
	 * @param inviteCode the inviteCode to set
	 */
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	
	/**
	 * 所属用户id
	 * @return the userIdOwn
	 */
	public int getUserIdOwn() {
		return userIdOwn;
	}
	
	/**
	 * 所属用户id
	 * @param userIdOwn the userIdOwn to set
	 */
	public void setUserIdOwn(int userIdOwn) {
		this.userIdOwn = userIdOwn;
	}
	
	/**
	 * 所属账号
	 * @return the userAccountOwn
	 */
	public String getUserAccountOwn() {
		return userAccountOwn;
	}
	
	/**
	 * 所属账号
	 * @param userAccountOwn the userAccountOwn to set
	 */
	public void setUserAccountOwn(String userAccountOwn) {
		this.userAccountOwn = userAccountOwn;
	}
	
	/**
	 * 获得时间
	 * @return the addTime
	 */
	public Timestamp getAddTime() {
		return addTime;
	}
	
	/**
	 * 获得时间
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
	/**
	 * 过期时间
	 * @return the outTime
	 */
	public Timestamp getOutTime() {
		return outTime;
	}
	
	/**
	 * 过期时间
	 * @param outTime the outTime to set
	 */
	public void setOutTime(Timestamp outTime) {
		this.outTime = outTime;
	}
	
	/**
	 * 使用状态,0:未使用,1:已使用
	 * @return the useStatus
	 */
	public int getUseStatus() {
		return useStatus;
	}
	
	/**
	 * 使用状态,0:未使用,1:已使用
	 * @param useStatus the useStatus to set
	 */
	public void setUseStatus(int useStatus) {
		this.useStatus = useStatus;
	}
	
	/**
	 * 使用者id
	 * @return the userIdUsed
	 */
	public int getUserIdUsed() {
		return userIdUsed;
	}
	
	/**
	 * 使用者id
	 * @param userIdUsed the userIdUsed to set
	 */
	public void setUserIdUsed(int userIdUsed) {
		this.userIdUsed = userIdUsed;
	}
	
	/**
	 * 使用者账号
	 * @return the userAccountUsed
	 */
	public String getUserAccountUsed() {
		return userAccountUsed;
	}
	
	/**
	 * 使用者账号
	 * @param userAccountUsed the userAccountUsed to set
	 */
	public void setUserAccountUsed(String userAccountUsed) {
		this.userAccountUsed = userAccountUsed;
	}

	/**
	 * 使用时间
	 * @return the usedTime
	 */
	public Timestamp getUsedTime() {
		return usedTime;
	}

	/**
	 * 使用时间
	 * @param usedTime the usedTime to set
	 */
	public void setUsedTime(Timestamp usedTime) {
		this.usedTime = usedTime;
	}
	
}
