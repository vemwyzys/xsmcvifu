package com.pandapay.entity.DO;

import java.sql.Timestamp;

/**
 * 用户提现订单
 * @author 豆豆
 *
 */
public class UserOrderTakeCashDO {

	private String orderNo; //订单号,时间标识（14位）+随机数（11位）
	private int userId; //用户id
	private String userAccount; //用户账号
	private double takeCashAmount; //提现金额,单位:元
	private int takeCashWay; //收款方式,1:支付宝, 2:微信
	private String takeCashAccount; //收款账号,支付宝账号或者微信账号
	private Timestamp addTime; //申请时间
	private int takeCashStatus; //提现状态,0:待打款,1:打款成功,2:打款失败
	private String remarks; //提现备注
	private Timestamp updateTime; //最后更新时间
	
	/**
	 * 订单号,时间标识（14位）+随机数（11位）
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * 订单号,时间标识（14位）+随机数（11位）
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * 用户id
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * 用户id
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * 用户账号
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}
	
	/**
	 * 用户账号
	 * @param userAccount the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	/**
	 * 提现金额,单位:元
	 * @return the takeCashAmount
	 */
	public double getTakeCashAmount() {
		return takeCashAmount;
	}
	
	/**
	 * 提现金额,单位:元
	 * @param takeCashAmount the takeCashAmount to set
	 */
	public void setTakeCashAmount(double takeCashAmount) {
		this.takeCashAmount = takeCashAmount;
	}
	
	/**
	 * 收款方式,1:支付宝, 2:微信
	 * @return the takeCashWay
	 */
	public int getTakeCashWay() {
		return takeCashWay;
	}
	
	/**
	 * 收款方式,1:支付宝, 2:微信
	 * @param takeCashWay the takeCashWay to set
	 */
	public void setTakeCashWay(int takeCashWay) {
		this.takeCashWay = takeCashWay;
	}
	
	/**
	 * 收款账号,支付宝账号或者微信账号
	 * @return the takeCashAccount
	 */
	public String getTakeCashAccount() {
		return takeCashAccount;
	}
	
	/**
	 * 收款账号,支付宝账号或者微信账号
	 * @param takeCashAccount the takeCashAccount to set
	 */
	public void setTakeCashAccount(String takeCashAccount) {
		this.takeCashAccount = takeCashAccount;
	}
	
	/**
	 * 申请时间
	 * @return the addTime
	 */
	public Timestamp getAddTime() {
		return addTime;
	}
	
	/**
	 * 申请时间
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
	/**
	 * 提现状态,0:待打款,1:打款成功,2:打款失败
	 * @return the takeCashStatus
	 */
	public int getTakeCashStatus() {
		return takeCashStatus;
	}
	
	/**
	 * 提现状态,0:待打款,1:打款成功,2:打款失败
	 * @param takeCashStatus the takeCashStatus to set
	 */
	public void setTakeCashStatus(int takeCashStatus) {
		this.takeCashStatus = takeCashStatus;
	}
	
	/**
	 * 提现备注
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	
	/**
	 * 提现备注
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * 最后更新时间
	 * @return the updateTime
	 */
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * 最后更新时间
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
		
}
