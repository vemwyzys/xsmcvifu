package com.pandapay.entity.DO;

import java.sql.Timestamp;

/**
 * 首页广告
 * @author wss
 *
 */
public class SystemAdsDO {
	
	private int adsId; //广告id
	private String adsTitle; //广告标题
	private String adsImage; //广告图片地址
	private String adsLink; //链接地址
	private int showPosition; //广告显示位置,0:顶部广告,1:底部广告
	private int preAdsId; //前一位置的广告id
	private Timestamp addTime; //添加时间
	
	/**
	 * 广告id
	 * @return the adsId
	 */
	public int getAdsId() {
		return adsId;
	}
	
	/**
	 * 广告id
	 * @param adsId the adsId to set
	 */
	public void setAdsId(int adsId) {
		this.adsId = adsId;
	}
	
	/**
	 * 广告标题
	 * @return the adsTitle
	 */
	public String getAdsTitle() {
		return adsTitle;
	}
	
	/**
	 * 广告标题
	 * @param adsTitle the adsTitle to set
	 */
	public void setAdsTitle(String adsTitle) {
		this.adsTitle = adsTitle;
	}
	
	/**
	 *广告图片地址 
	 * @return the adsImage
	 */
	public String getAdsImage() {
		return adsImage;
	}
	
	/**
	 * 广告图片地址
	 * @param adsImage the adsImage to set
	 */
	public void setAdsImage(String adsImage) {
		this.adsImage = adsImage;
	}
	
	/**
	 * 链接地址
	 * @return the adsLink
	 */
	public String getAdsLink() {
		return adsLink;
	}
	
	/**
	 * 链接地址
	 * @param adsLink the adsLink to set
	 */
	public void setAdsLink(String adsLink) {
		this.adsLink = adsLink;
	}
	
	/**
	 * 广告显示位置,0:顶部广告,1:底部广告
	 * @return the showPosition
	 */
	public int getShowPosition() {
		return showPosition;
	}
	
	/**
	 * 广告显示位置,0:顶部广告,1:底部广告
	 * @param showPosition the showPosition to set
	 */
	public void setShowPosition(int showPosition) {
		this.showPosition = showPosition;
	}
	
	/**
	 * 前一位置的广告id
	 * @return the preAdsId
	 */
	public int getPreAdsId() {
		return preAdsId;
	}

	/**
	 * 前一位置的广告id
	 * @param preAdsId the preAdsId to set
	 */
	public void setPreAdsId(int preAdsId) {
		this.preAdsId = preAdsId;
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
