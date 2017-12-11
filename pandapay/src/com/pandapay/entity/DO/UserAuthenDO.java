package com.pandapay.entity.DO;

import java.sql.Timestamp;

import config.FileUrlConfig;

/**
 * 用户认证信息
 * @author 豆豆
 */
public class UserAuthenDO {
	
	private int userAuthenId; //用户认证id
	private int userId; //用户Id
	private String userAccount; //用户账号
	private String storeName; //店铺名称
	private String contactsName; //联系人
	private String address; //地址
	private String idCard; //身份证号
	private String bankCard; //结算银行卡号
	private String bankName; //结算银行名称
	private String bankNo; //银行银联号
	private String idCardImg; //身份证正面照片
	private String idCardHandleImg; //手持身份证正面照片
	private String bankCardImg; //银行卡正面照片
	private Timestamp addTime; //申请时间
	private int authenStatus; //认证状态，0：待认证，1：认证通过，2：认证失败
	private Timestamp authenTime; //审核时间
	private String userPayId; //支付平台的商户编号
	private String remarks; //备注
	
	private String idCardImgFull; //身份证正面照片，全路径
	private String idCardHandleImgFull; //手持身份证正面照片，全路径
	private String bankCardImgFull; //银行卡正面照片，全路径
	
	/**
	 * 用户认证id
	 * @return the userAuthenId
	 */
	public int getUserAuthenId() {
		return userAuthenId;
	}
	
	/**
	 * 用户认证id
	 * @param userAuthenId the userAuthenId to set
	 */
	public void setUserAuthenId(int userAuthenId) {
		this.userAuthenId = userAuthenId;
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
	 * 店铺名称
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}
	
	/**
	 * 店铺名称
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	/**
	 * 联系人
	 * @return the contactsName
	 */
	public String getContactsName() {
		return contactsName;
	}
	
	/**
	 * 联系人
	 * @param contactsName the contactsName to set
	 */
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}
	
	/**地址
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * 地址
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 身份证号
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}
	
	/**
	 * 身份证号
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	/**
	 * 结算银行卡号
	 * @return the bankCard
	 */
	public String getBankCard() {
		return bankCard;
	}
	
	/**
	 * 结算银行卡号
	 * @param bankCard the bankCard to set
	 */
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	
	/**
	 * 结算银行名称
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	
	/**
	 * 结算银行名称
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
	 * 身份证正面照片
	 * @return the idCardImg
	 */
	public String getIdCardImg() {
		return idCardImg;
	}
	
	/**
	 * 身份证正面照片
	 * @param idCardImg the idCardImg to set
	 */
	public void setIdCardImg(String idCardImg) {
		this.idCardImg = idCardImg;
		
		setIdCardImgFull(FileUrlConfig.file_visit_url + idCardImg);
	}
	
	/**
	 * 手持身份证正面照片
	 * @return the idCardHandleImg
	 */
	public String getIdCardHandleImg() {
		return idCardHandleImg;
	}
	
	/**
	 * 手持身份证正面照片
	 * @param idCardHandleImg the idCardHandleImg to set
	 */
	public void setIdCardHandleImg(String idCardHandleImg) {
		this.idCardHandleImg = idCardHandleImg;
		
		setIdCardHandleImgFull(FileUrlConfig.file_visit_url + idCardHandleImg);
	}
	
	/**
	 * 银行卡正面照片
	 * @return the bankCardImg
	 */
	public String getBankCardImg() {
		return bankCardImg;
	}
	
	/**
	 * 银行卡正面照片
	 * @param bankCardImg the bankCardImg to set
	 */
	public void setBankCardImg(String bankCardImg) {
		this.bankCardImg = bankCardImg;
		
		setBankCardImgFull(FileUrlConfig.file_visit_url + bankCardImg);
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
	 * 认证状态，0：待认证，1：认证通过，2：认证失败
	 * @return the authenStatus
	 */
	public int getAuthenStatus() {
		return authenStatus;
	}
	
	/**
	 * 认证状态，0：待认证，1：认证通过，2：认证失败
	 * @param authenStatus the authenStatus to set
	 */
	public void setAuthenStatus(int authenStatus) {
		this.authenStatus = authenStatus;
	}
	
	/**
	 * 审核时间
	 * @return the authenTime
	 */
	public Timestamp getAuthenTime() {
		return authenTime;
	}
	
	/**
	 * 审核时间
	 * @param authenTime the authenTime to set
	 */
	public void setAuthenTime(Timestamp authenTime) {
		this.authenTime = authenTime;
	}
	
	/**
	 * 支付平台的商户编号
	 * @return the userPayId
	 */
	public String getUserPayId() {
		return userPayId;
	}
	
	/**
	 * 支付平台的商户编号
	 * @param userPayId the userPayId to set
	 */
	public void setUserPayId(String userPayId) {
		this.userPayId = userPayId;
	}
	
	/**
	 * 备注 
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	
	/**
	 * 备注
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 身份证正面照片，全路径
	 * @return the idCardImgFull
	 */
	public String getIdCardImgFull() {
		return idCardImgFull;
	}

	/**
	 * 身份证正面照片，全路径
	 * @param idCardImgFull the idCardImgFull to set
	 */
	public void setIdCardImgFull(String idCardImgFull) {
		this.idCardImgFull = idCardImgFull;
	}

	/**
	 * 手持身份证正面照片，全路径
	 * @return the idCardHandleImgFull
	 */
	public String getIdCardHandleImgFull() {
		return idCardHandleImgFull;
	}

	/**
	 * 手持身份证正面照片，全路径
	 * @param idCardHandleImgFull the idCardHandleImgFull to set
	 */
	public void setIdCardHandleImgFull(String idCardHandleImgFull) {
		this.idCardHandleImgFull = idCardHandleImgFull;
	}

	/**
	 * 银行卡正面照片，全路径
	 * @return the bankCardImgFull
	 */
	public String getBankCardImgFull() {
		return bankCardImgFull;
	}

	/**
	 * 银行卡正面照片，全路径
	 * @param bankCardImgFull the bankCardImgFull to set
	 */
	public void setBankCardImgFull(String bankCardImgFull) {
		this.bankCardImgFull = bankCardImgFull;
	}

}
