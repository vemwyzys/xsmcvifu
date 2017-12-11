package com.pandapay.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.ISystemBankDao;
import com.pandapay.entity.DO.SystemBankDO;

/**
 * 银行对照表
 * @author gql
 *
 */
@Repository
public class SystemBankDaoImpl implements ISystemBankDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 查询银行信息（通过银行Id）
	 * @param bankId 银行Id
	 * @return 操作成功：返回银行信息，操作失败：返回null
	 */
	public SystemBankDO queryBackByBackId(int bankId){
		SystemBankDO systemBank = null;
		try{
			systemBank = sqlSessionTemplate.selectOne("SystemBank_queryBackByBackId", bankId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return systemBank;
	}
	
	/**
	 * 查询银行信息（通过银联号）
	 * @param bankNo 银行银联号
	 * @return 操作成功：返回银行信息，操作失败：返回null
	 */
	public SystemBankDO queryBackByBankNo(String bankNo){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bankNo", bankNo);
		
		SystemBankDO systemBank = null;
		try{
			systemBank = sqlSessionTemplate.selectOne("SystemBank_queryBackByBankNo", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return systemBank;
	}
	
	/**
	 * 查询所有的银行信息
	 * @return 操作成功：返回银行信息，操作失败：返回null
	 */
	public List<SystemBankDO> queryBackAll(){
		List<SystemBankDO> backList = null;
		try{
			backList = sqlSessionTemplate.selectList("SystemBank_queryBackAll");
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return backList;
	}
	
	/**
	 * 查询银行信息（通过银行名称，支持模糊匹配）
	 * @param bankName 银行名称
	 * @return 操作成功：返回银行信息，操作失败：返回null
	 */
	public List<SystemBankDO> queryBackByBankName(String bankName){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bankName", bankName);
		
		List<SystemBankDO> backList = null;
		try{
			backList = sqlSessionTemplate.selectList("SystemBank_queryBackByBankName", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return backList;
	}
	
}
