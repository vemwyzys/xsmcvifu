package com.pandapay.entity.DO;

import java.sql.Timestamp;

/**
 * 用户账号关联微信
 * @author gql
 *
 */
public class UserRelationWeixinDO {

	private int id;  //记录Id
	private int userId;  //用户Id
	private String openId;  //微信openId
	private Timestamp addTime;  //关联时间
	
	/**
	 * 记录Id
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 记录Id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * 微信openId
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}
	
	/**
	 * 微信openId
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	/**
	 * 关联时间
	 * @return the addTime
	 */
	public Timestamp getAddTime() {
		return addTime;
	}
	
	/**
	 * 关联时间
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}
