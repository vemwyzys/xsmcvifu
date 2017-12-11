package com.pandapay.service;

import java.sql.Timestamp;
import java.util.List;

import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.DO.UserInviteCodeDO;

/**
 * 邀请码
 * @author 豆豆
 *
 */
public interface IUserInviteCodeService {

	/**
	 * 新增邀请码
	 * @param userInviteCode 邀请码信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertInviteCode(UserInviteCodeDO userInviteCode);
	
	/**
	 * 批量添加邀请码（主要用于后台发放）
	 * @param userIdOwn 拥有者用户Id
	 * @param addNumber 添加的数量，单次最大999
	 * @param outTime 过期时间
	 * @param remarks 操作备注
	 * @param backerId 操作的管理员id
	 * @param backerAccount 操作的管理员帐号
	 * @param ipAddress 操作时的ip地址
	 * @return 操作成功：返回code=1，操作失败：返回code!=1
	 */
	public JsonObjectBO addInviteCodeList(int userIdOwn, int addNumber, Timestamp outTime, String remarks,
			int backerId, String backerAccount, String ipAddress);
	
	/**
	 * 使用邀请码
	 * @param inviteCode 邀请码
	 * @param userIdUsed 使用者用户Id
	 * @return 操作成功：返回code=1，操作失败：返回code!=1
	 */
	public JsonObjectBO useInviteCode(String inviteCode, int userIdUsed);
	
	/**
	 * 查询邀请码信息
	 * @param inviteCode 邀请码
	 * @return 操作成功：返回邀请码信息，操作失败：返回null
	 */
	public UserInviteCodeDO queryInviteCodeByInviteCode(String inviteCode);
	
	/**
	 * 查询邀请码总数（用户操作）
	 * @param userIdOwn 所属用户id
	 * @param useStatus 使用状态,0:未使用,1:已使用,查询全部填-1
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryInviteCodeTotalOfUser(int userIdOwn,  int useStatus);
	
	/**
	 * 查询邀请码列表（用户操作）
	 * @param userIdOwn 所属用户id
	 * @param useStatus 使用状态,0:未使用,1:已使用,查询全部填-1
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回邀请码列表，操作失败：返回null
	 */
	public List<UserInviteCodeDO> queryInviteCodeListOfUser(int userIdOwn, int useStatus, int pageNumber, int pageSize);
	
	/**
	 * 查询邀请码总数（后台操作）
	 * @param inviteCode 邀请码
	 * @param userAccountOwn 所属账号
	 * @param useStatus 使用状态,0:未使用,1:已使用,查询全部填-1
	 * @param startTime 使用时间的开始时间
	 * @param endTime 使用时间的结束时间
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryInviteCodeTotalOfBack(String inviteCode, String userAccountOwn, int useStatus, Timestamp startTime, Timestamp endTime);
	
	/**
	 * 查询邀请码列表（后台操作）
	 * @param inviteCode 邀请码
	 * @param userAccountOwn 所属账号
	 * @param useStatus 使用状态,0:未使用,1:已使用,查询全部填-1
	 * @param startTime 使用时间的开始时间
	 * @param endTime 使用时间的结束时间
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回邀请码列表，操作失败：返回null
	 */
	public List<UserInviteCodeDO> queryInviteCodeListOfBack(String inviteCode, String userAccountOwn, int useStatus,
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize);
	
}
