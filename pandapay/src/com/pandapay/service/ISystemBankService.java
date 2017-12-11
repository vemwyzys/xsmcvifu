package com.pandapay.service;

import java.util.List;

import com.pandapay.entity.DO.SystemBankDO;

/**
 * 银行对照表
 * @author gql
 *
 */
public interface ISystemBankService {

	/**
	 * 查询银行信息（通过银行Id）
	 * @param bankId 银行Id
	 * @return 操作成功：返回银行信息，操作失败：返回null
	 */
	public SystemBankDO queryBackByBackId(int bankId);
	
	/**
	 * 查询银行信息（通过银联号）
	 * @param bankNo 银联号
	 * @return 操作成功：返回银行信息，操作失败：返回null
	 */
	public SystemBankDO queryBackByBankNo(String bankNo);
	
	/**
	 * 查询所有的银行信息
	 * @return 操作成功：返回银行信息，操作失败：返回null
	 */
	public List<SystemBankDO> queryBackAll();
	
	/**
	 * 查询银行信息（通过银行名称，支持模糊匹配）
	 * @param bankName 银行名称
	 * @return 操作成功：返回银行信息，操作失败：返回null
	 */
	public List<SystemBankDO> queryBackByBankName(String bankName);
	
}
