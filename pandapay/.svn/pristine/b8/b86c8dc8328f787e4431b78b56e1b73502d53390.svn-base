package com.pandapay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandapay.dao.ISystemBankDao;
import com.pandapay.entity.DO.SystemBankDO;
import com.pandapay.service.ISystemBankService;

/**
 * 银行对照表
 * @author gql
 *
 */
@Service("systemBankService")
public class SystemBankServiceImpl implements ISystemBankService{

	/** 银行对照表 */
	@Autowired
	private ISystemBankDao systemBankDao;
	
	/**
	 * 查询银行信息（通过银行Id）
	 * @param bankId 银行Id
	 * @return 操作成功：返回银行信息，操作失败：返回null
	 */
	public SystemBankDO queryBackByBackId(int bankId){
		return systemBankDao.queryBackByBackId(bankId);
	}
	
	/**
	 * 查询银行信息（通过银联号）
	 * @param bankNo 银联号
	 * @return 操作成功：返回银行信息，操作失败：返回null
	 */
	public SystemBankDO queryBackByBankNo(String bankNo){
		return systemBankDao.queryBackByBankNo(bankNo);
	}
	
	/**
	 * 查询所有的银行信息
	 * @return 操作成功：返回银行信息，操作失败：返回null
	 */
	public List<SystemBankDO> queryBackAll(){
		return systemBankDao.queryBackAll();
	}
	
	/**
	 * 查询银行信息（通过银行名称，支持模糊匹配）
	 * @param bankName 银行名称
	 * @return 操作成功：返回银行信息，操作失败：返回null
	 */
	public List<SystemBankDO> queryBackByBankName(String bankName){
		return systemBankDao.queryBackByBankName(bankName);
	}
	
}
