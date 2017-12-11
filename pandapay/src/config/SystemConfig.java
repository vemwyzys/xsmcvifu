package config;

/**
 * 系统配置
 * @author gql
 *
 */
public class SystemConfig {
	
	/** 系统账号的用户Id */
	public static final int systemUserId = 0;
	
	/** 系统账号的用户支付Id */
	public static final String systemUserPayId = "17041500005159";
	
	
	/** 商家收款单笔手续费，单位：元 */
	public static final double payOrderCost = 2;
	
	/** 收款单笔限额（初始值），单位：元 */
	public static final double pay_singleLimitAmount_init = 20000;
	
	/** 收款费率（初始值/普通会员） */
	public static final double pay_receiptRate_init = 0.005;
	
	/** 收款费率（VIP会员） */
	public static final double pay_receiptRate_VIP = 0.0026;
	
	/** 收款返利利率 */
	public static final double pay_return_rate = 0.0012;
	
}
