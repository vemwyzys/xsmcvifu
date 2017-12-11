package com.pandapay.service;

import java.sql.Timestamp;
import java.util.List;

import com.pandapay.entity.DO.BackerRecordInviteCodeDO;

/**
 * 邀请码发放记录
 * @author gql
 *
 */
public interface IBackerRecordInviteCodeService {

	/**
	 * 新增邀请码发放记录
	 * @param backerRecordInviteCode 邀请码发放记录
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean insertRecord(BackerRecordInviteCodeDO backerRecordInviteCode);
	
	/**
	 * 查询邀请码发放记录总数（后台操作）
	 * @param userAccount 用户帐号
	 * @param remarks 操作备注
	 * @param backerAccount 操作的管理员帐号
	 * @param startTime 操作时间的开始时间
	 * @param endTime 操作时间的结束时间
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryRecordTotalOfBack(String userAccount, String remarks, String backerAccount, Timestamp startTime, Timestamp endTime);
	
	/**
	 * 查询邀请码发放记录列表（后台操作）
	 * @param userAccount 用户帐号
	 * @param remarks 操作备注
	 * @param backerAccount 操作的管理员帐号
	 * @param startTime 操作时间的开始时间
	 * @param endTime 操作时间的结束时间
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回记录列表，操作失败：返回null
	 */
	public List<BackerRecordInviteCodeDO> queryRecordListOfBack(String userAccount, String remarks, String backerAccount,
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize);
	
}
