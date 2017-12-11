package com.pandapay.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.StringUtil;
import com.pandapay.dao.IUserAuthenDao;
import com.pandapay.entity.DO.UserAuthenDO;
import com.pandapay.entity.DO.UserDO;
import com.pandapay.service.IUserAuthenService;
import com.pandapay.service.IUserService;
import com.pandapay.utils.CodePayUtil;

/**
 * 用户认证信息
 * @author 豆豆
 */
@Service("userAuthenService")
public class UserAuthenServiceImpl implements IUserAuthenService {
	
	/**用户认证信息*/
	@Autowired
	private IUserAuthenDao userAuthenDao;
	
	/** 用户信息 */
	@Autowired
	private IUserService userService;
	
	/**
	 * 新增用户认证信息
	 * @param userAuthen 用户认证信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	@Transactional
	public boolean insertUserAuthen(UserAuthenDO userAuthen){
		Timestamp curTime = DateUtil.getCurrentTime();
		userAuthen.setAddTime(curTime);
		userAuthen.setAuthenStatus(0);
		
		//业务执行状态
		boolean executeSuccess = false;
		
		boolean insertUserAuthen = userAuthenDao.insertUserAuthen(userAuthen);
		if(insertUserAuthen){
			executeSuccess = userService.updateAuthenStatus(userAuthen.getUserId(), 0, curTime);
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
	/**
	 * 查询最新认证通过的信息
	 * @param userId 用户Id
	 * @return 操作成功：返回认证信息，操作失败：返回null
	 */
	public UserAuthenDO queryLastAuthened(int userId){
		return userAuthenDao.queryLastAuthened(userId);
	}
	
	/**
	 * 查询最新的认证信息（以提交认证时间为准的最新）
	 * @param userId 用户Id
	 * @return 操作成功：返回认证信息，操作失败：返回null
	 */
	public UserAuthenDO queryNewAuthen(int userId){
		return userAuthenDao.queryNewAuthen(userId);
	}
	
	/**
	 * 认证通过
	 * @param userAuthenId 用户认证Id
	 * @param remarks 备注，可以为空
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateAuthenSuccess(int userAuthenId, String remarks){
		Timestamp curTime = DateUtil.getCurrentTime();
		
		UserAuthenDO userAuthenDO = userAuthenDao.queryUserAuthen(userAuthenId);
		if(userAuthenDO == null){
			return false;
		}
		UserDO userDO = userService.queryUserByUserId(userAuthenDO.getUserId());
		if(userDO == null){
			return false;
		}
		
		//业务执行状态
		boolean executeSuccess = false;
		
		//认证通过
		CodePayUtil codePayUtil = new CodePayUtil();
		String userPayId = codePayUtil.businessRegister(userDO.getUserAccount(), userAuthenDO.getContactsName(),
				userAuthenDO.getIdCard(), userAuthenDO.getBankCard(), userAuthenDO.getBankNo(), userDO.getReceiptRate());
		
		if(StringUtil.isNotNull(userPayId)){
			boolean  authenTabStatus = userAuthenDao.updateAuthenStatus(userAuthenDO.getUserAuthenId(), 1, curTime, userPayId, remarks);
			if(authenTabStatus){
				executeSuccess = userService.updateAuthenStatus(userAuthenDO.getUserId(), 1, curTime);
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
			//支付状态还原
			UserAuthenDO lastUserAuthenDO = userAuthenDao.queryLastAuthened(userDO.getUserId());
			if(lastUserAuthenDO != null){
				codePayUtil.businessRegister(userDO.getUserAccount(), lastUserAuthenDO.getContactsName(),
						lastUserAuthenDO.getIdCard(), lastUserAuthenDO.getBankCard(), lastUserAuthenDO.getBankNo(), userDO.getReceiptRate());
			}
		}
		return executeSuccess;
	}
	
	/**
	 * 认证拒绝
	 * @param userAuthenId 用户认证id
	 * @param remarks 备注，可以为空
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	@Transactional
	public boolean updateAuthenFail(int userAuthenId, String remarks){
		Timestamp curTime = DateUtil.getCurrentTime();
		
		UserAuthenDO userAuthenDO = userAuthenDao.queryUserAuthen(userAuthenId);
		if(userAuthenDO == null){
			return false;
		}
		
		//业务执行状态
		boolean executeSuccess = false;
				
		boolean  authenTabStatus = userAuthenDao.updateAuthenStatus(userAuthenDO.getUserAuthenId(), 2, curTime, "", remarks);
		if(authenTabStatus){
			executeSuccess = userService.updateAuthenStatus(userAuthenDO.getUserId(), 2, curTime);
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
}
