package com.pandapay.dao;

import java.sql.Timestamp;
import java.util.List;

import com.pandapay.entity.DO.BackerRecordWalletAmountDO;

/**
 * 用户钱包账户操作记录
 * @author 豆豆
 *
 */
public interface IBackerRecordWalletAmountDao {

	/**
	 * 新增用户钱包账户操作记录
	 * @param backerRecordWalletAmount 用户钱包账户操作记录
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean insertRecord(BackerRecordWalletAmountDO backerRecordWalletAmount);
	
	/**
	 * 查询用户钱包账户操作记录总数（后台操作）
	 * @param userAccount 用户帐号
	 * @param handleType 操作备注, 操作类型,1:增加,2:减少, 查询全部填0
	 * @param remarks 操作备注
	 * @param backerAccount 操作的管理员帐号
	 * @param startTime 操作时间的开始时间
	 * @param endTime 操作时间的结束时间
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryRecordTotalOfBack(String userAccount, int handleType, String remarks, String backerAccount, Timestamp startTime, Timestamp endTime);
	
	/**
	 * 查询用户钱包账户操作记录列表（后台操作）
	 * @param userAccount 用户帐号
	 * @param handleType 操作备注, 操作类型,1:增加,2:减少, 查询全部填0
	 * @param remarks 操作备注
	 * @param backerAccount 操作的管理员帐号
	 * @param startTime 操作时间的开始时间
	 * @param endTime 操作时间的结束时间
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回记录列表，操作失败：返回null
	 */
	public List<BackerRecordWalletAmountDO> queryRecordListOfBack(String userAccount, int handleType, String remarks, String backerAccount,
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize);
	
}
