package com.pandapay.entity.DO;

import java.sql.Timestamp;

/**
 * 银行对照表
 * @author gql
 *
 */
public class SystemBankDO {

	private int bankId;  //银行Id
	private String bankName;  //银行名称
	private String bankNo;  //银行银联号
	private Timestamp addTime;  //添加时间
	
	/**
	 * 银行Id
	 * @return the bankId
	 */
	public int getBankId() {
		return bankId;
	}
	
	/**
	 * 银行Id
	 * @param bankId the bankId to set
	 */
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	
	/**
	 * 银行名称
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	
	/**
	 * 银行名称
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	/**
	 * 银行银联号
	 * @return the bankNo
	 */
	public String getBankNo() {
		return bankNo;
	}
	
	/**
	 * 银行银联号
	 * @param bankNo the bankNo to set
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	
	/**
	 * 添加时间
	 * @return the addTime
	 */
	public Timestamp getAddTime() {
		return addTime;
	}
	
	/**
	 * 添加时间
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}
