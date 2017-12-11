package com.pandapay.dao;

import java.sql.Timestamp;
import java.util.List;

import com.pandapay.entity.DO.UserInviteCodeDO;

/**
 * 邀请码
 * @author 豆豆
 *
 */
public interface IUserInviteCodeDao {
	
	/**
	 * 新增邀请码
	 * @param userInviteCode 邀请码信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertInviteCode(UserInviteCodeDO userInviteCode);
	
	/**
	 * 批量新增邀请码
	 * @param inviteCodeList 邀请码列表
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean addInviteCodeList(List<UserInviteCodeDO> inviteCodeList);
	
	/**
	 * 修改邀请码使用状态
	 * @param inviteCode 邀请码
	 * @param useStatus 使用状态,0:未使用,1:已使用
	 * @param userIdUsed 使用者id
	 * @param userAccountUsed 使用者账号
	 * @param usedTime 使用时间
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateInviteCodeUseStatus(String inviteCode, int useStatus, int userIdUsed, String userAccountUsed, Timestamp usedTime);
	
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
