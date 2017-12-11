package config;

/**
 * 发送短信配置
 * @author gql
 *
 */
public class SendMessageConfig {
	
	/** 短信发送接口地址 */
	public static final String url = "http://gw.api.taobao.com/router/rest";
	
	/** 短信发送key */
	public static final String appkey = "23462681";
	
	/** 短信发送密钥 */
	public static final String secret = "3611ab62c7f6812b781047ab157b354e";
	
	/** 短信扩展参数 */
	public static final String extend = "123456";
	
	/** 短信类型 */
	public static final String smsType = "normal";
	
	/** 短信签名 */
	public static final String smsFreeSignName = "身份验证";
	
	/** 短信模版编码（短信验证码） */
	public static final String smsTemplateCode = "SMS_15450044";
	
}
