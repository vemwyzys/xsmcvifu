package com.pandapay.service;

import java.util.List;

import com.pandapay.entity.DO.UserRecordAmountDO;

/**
 * 用户账户金额记录
 * @author gql
 *
 */
public interface IUserRecordAmountService {

	/**
	 * 新增账户金额记录
	 * @param userRecordAmount 账户金额记录
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertRecord(UserRecordAmountDO userRecordAmount);
	
	/**
	 * 查询账户金额记录总数（用户操作）
	 * @param userId 用户Id
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryRecordTotalOfUser(int userId);
	
	/**
	 * 查询账户金额记录列表（用户操作）
	 * @param userId 用户Id
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回记录列表，操作失败：返回null
	 */
	public List<UserRecordAmountDO> queryRecordListOfUser(int userId, int pageNumber, int pageSize);
	
}
