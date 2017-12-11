package com.pandapay.entity.DTO;

/**
 * 角色权限数据
 * @author whx
 *
 */ 
public class BackRolePowerDTO {

	private int powerId;  //权限Id
	private String powerName;  //权限名称
	private int powerLevel;  //权限等级，从1级开始
	private int uperPowerId;  //上级权限Id
	
	/**
	 * 权限Id
	 * @return the powerId
	 */
	public int getPowerId() {
		return powerId;
	}
	
	/**
	 * 权限Id
	 * @param powerId the powerId to set
	 */
	public void setPowerId(int powerId) {
		this.powerId = powerId;
	}
	
	/**
	 * 权限名称
	 * @return the powerName
	 */
	public String getPowerName() {
		return powerName;
	}
	
	/**
	 * 权限名称
	 * @param powerName the powerName to set
	 */
	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}
	
	/**
	 * 权限等级，从1级开始
	 * @return the powerLevel
	 */
	public int getPowerLevel() {
		return powerLevel;
	}
	
	/**
	 * 权限等级，从1级开始
	 * @param powerLevel the powerLevel to set
	 */
	public void setPowerLevel(int powerLevel) {
		this.powerLevel = powerLevel;
	}
	
	/**
	 * 上级权限Id
	 * @return the uperPowerId
	 */
	public int getUperPowerId() {
		return uperPowerId;
	}
	
	/**
	 * 上级权限Id
	 * @param uperPowerId the uperPowerId to set
	 */
	public void setUperPowerId(int uperPowerId) {
		this.uperPowerId = uperPowerId;
	}
	
}
