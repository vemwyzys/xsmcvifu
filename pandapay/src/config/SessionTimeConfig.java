package config;

/**
 * session时间配置
 * @author gql
 *
 */
public class SessionTimeConfig {
	
	/** 后台管理员登录过期时间 */
	public static final long SESSION_OVERTIME_BACKER = 60*60*1000;  //60分钟
	
	/** 用户登录过期时间 */
	public static final long SESSION_OVERTIME_USER = 60*60*1000;  //60分钟
	
	/** 用户登录过期时间(超长，适用于如：APP) */
	public static final long SESSION_OVERTIME_USER_LONG = 7*24*60*60*1000;  //7天
	
	
	/** session状态：正常 */
	public static final int SESSION_STATUS_NORMAL = 1;
	
	/** session状态：过期 */
	public static final int SESSION_STATUS_OVERTIME = 2;
	
	/** session状态：被挤下线 */
	public static final int SESSION_STATUS_LOSE = 3;
	
	/** session状态：失效 */
	public static final int SESSION_STATUS_LOGINOFF = 4;
	
	
	/** 过期session保留时间 */
	public static final long SESSION_OUT_STAY_TIMES = 7*24*60*60*1000;  //7天
	
}
