package com.pandapay.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.StringUtil;
import com.pandapay.dao.IUserDao;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.DO.BackerRecordWalletAmountDO;
import com.pandapay.entity.DO.UserAuthenDO;
import com.pandapay.entity.DO.UserDO;
import com.pandapay.entity.DO.UserRecordAmountDO;
import com.pandapay.entity.VO.UserBackVO;
import com.pandapay.service.IBackerRecordWalletAmountService;
import com.pandapay.service.IUserAuthenService;
import com.pandapay.service.IUserRecordAmountService;
import com.pandapay.service.IUserService;
import com.pandapay.utils.CodePayUtil;

import config.SystemConfig;

/**
 * 用户信息
 * @author gql
 *
 */
@Service("userService")
public class UserServiceImpl implements IUserService{

	/** 用户信息 */
	@Autowired
	private IUserDao userDao;
	
	/** 用户钱包账户操作记录 */
	@Autowired
	private IBackerRecordWalletAmountService backerRecordWalletAmountService;
	
	/** 用户账户金额记录 */
	@Autowired
	private IUserRecordAmountService userRecordAmountService;
	
	/** 用户认证信息 */
	@Autowired
	private IUserAuthenService userAuthenService;
	
	/**
	 * 新增用户
	 * @param userAccount 用户帐号
	 * @param password 登录密码（加密后的）
	 * @param inviteCode 邀请码，没有值填null
	 * @return 操作成功：返回code=1，操作失败：返回code!=-1
	 */
	public JsonObjectBO addUser(String userAccount, String password, String inviteCode){
		JsonObjectBO resultJson = new JsonObjectBO();
		
		UserDO inviteUser = null;
		if(StringUtil.isNotNull(inviteCode)){
			int inviteUserId = Integer.parseInt(inviteCode);
			if(inviteUserId > 0){
				inviteUser = userDao.queryUserByUserId(inviteUserId);
				if(inviteUser == null){
					resultJson.setCode(2);
					resultJson.setMessage("邀请人信息不存在");
					return resultJson;
				}
			}
		}
		
		Timestamp curTime = DateUtil.getCurrentTime();
		UserDO addUser = new UserDO();
		addUser.setUserAccount(userAccount);
		addUser.setPassword(password);
		addUser.setWalletAmount(0);
		addUser.setRedPacketAmount(0);
		addUser.setSingleLimitAmount(SystemConfig.pay_singleLimitAmount_init);
		addUser.setReceiptRate(SystemConfig.pay_receiptRate_init);
		addUser.setAddTime(curTime);
		addUser.setAuthenStatus(0);
		addUser.setVipIdentity(0);
		addUser.setVipOutTime(curTime);
		addUser.setUpdateTime(curTime);
		
		if(inviteUser != null){
			addUser.setInviteUserId(inviteUser.getUserId());
			addUser.setInviteUserAccount(inviteUser.getUserAccount());
		}
		
		boolean insertUser = userDao.insertUser(addUser);
		if(insertUser){
			resultJson.setCode(1);
			resultJson.setMessage("添加成功");
			return resultJson;
		}else{
			resultJson.setCode(2);
			resultJson.setMessage("添加失败");
			return resultJson;
		}
	}
	
	/**
	 * 查询用户信息
	 * @param userId 用户Id
	 * @return 操作成功：返回用户信息，失败：返回null
	 */
	public UserDO queryUserByUserId(int userId){
		return userDao.queryUserByUserId(userId);
	}
	
	/**
	 * 查询用户信息
	 * @param userAccount 用户Id
	 * @return 操作成功：返回用户信息，失败：返回null
	 */
	public UserDO queryUserByUserAccount(String userAccount){
		return userDao.queryUserByUserAccount(userAccount);
	}
	
	/**
	 * 验证用户帐号是否已存在
	 * @param userAccount 用户帐号
	 * @return 已存在：返回true，不存在：返回false
	 */
	public boolean validataUserAccount(String userAccount){
		return userDao.validataUserAccount(userAccount);
	}
	
	/**
	 * 验证用户登录
	 * @param userAccount 用户账号
	 * @param password 账号密码
	 * @return 操作成功：返回用户信息，失败：返回null
	 */
	public UserDO validateUserLogin(String userAccount, String password){
		return userDao.validateUserLogin(userAccount, password);
	}
	
	/**
	 * 增加钱包金额
	 * @param userId 用户Id
	 * @param addAmount 增加的钱包金额，单位：元
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean addWalletAmount(int userId, double addAmount){
		return userDao.addWalletAmount(userId, addAmount);
	}
	
	/**
	 * 减少钱包金额
	 * @param userId 用户Id
	 * @param reduceAmount 减少的钱包金额，单位：元
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean reduceWalletAmount(int userId, double reduceAmount){
		return userDao.reduceWalletAmount(userId, reduceAmount);
	}
	
	/**
	 * 增加红包金额
	 * @param userId 用户Id
	 * @param addAmount 增加的红包金额，单位：元
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean addRedPacketAmount(int userId, double addAmount){
		return userDao.addRedPacketAmount(userId, addAmount);
	}
	
	/**
	 * 减少红包金额
	 * @param userId 用户Id
	 * @param reduceAmount 减少的红包金额，单位：元
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean reduceRedPacketAmount(int userId, double reduceAmount){
		return userDao.reduceRedPacketAmount(userId, reduceAmount);
	}
	
	/**
	 * 增加钱包金额（后台操作）
	 * @param userId 用户Id
	 * @param addAmount 增加的钱包金额，单位：元
	 * @param remarks 操作备注
	 * @param backerId 操作的管理员id
	 * @param backerAccount 操作的管理员帐号
	 * @param ipAddress 操作时的ip地址
	 * @return 操作成功：返回true，失败：返回false
	 */
	@Transactional
	public boolean addWalletAmountOfBack(int userId, double addAmount, String remarks,
			int backerId, String backerAccount, String ipAddress){
		Timestamp curTime = DateUtil.getCurrentTime();
		
		UserDO userDO = userDao.queryUserByUserId(userId);
		if(userDO == null){
			return false;
		}
		
		//业务执行状态
		boolean executeSuccess = false;
		
		boolean addWalletAmount = userDao.addWalletAmount(userId, addAmount);
		if(addWalletAmount){
			//添加后台操作记录
			BackerRecordWalletAmountDO backerRecordWalletAmount = new BackerRecordWalletAmountDO();
			backerRecordWalletAmount.setUserId(userDO.getUserId());
			backerRecordWalletAmount.setUserAccount(userDO.getUserAccount());
			backerRecordWalletAmount.setHandleType(1);
			backerRecordWalletAmount.setHandleAmount(addAmount);
			backerRecordWalletAmount.setRemarks(remarks);
			backerRecordWalletAmount.setBackerId(backerId);
			backerRecordWalletAmount.setBackerAccount(backerAccount);
			backerRecordWalletAmount.setIpAddress(ipAddress);
			backerRecordWalletAmount.setAddTime(curTime);
			
			boolean addWalletAmountRecord = backerRecordWalletAmountService.insertRecord(backerRecordWalletAmount);
			if(addWalletAmountRecord){
				//添加用户账户记录
				UserRecordAmountDO userRecordAmount = new UserRecordAmountDO();
				userRecordAmount.setUserId(userId);
				userRecordAmount.setInOutType(1);
				userRecordAmount.setAmount(addAmount);
				userRecordAmount.setServiceType("系统操作");
				userRecordAmount.setRemarks(remarks);
				userRecordAmount.setAddTime(curTime);
				
				boolean addUserAmountRecord = userRecordAmountService.insertRecord(userRecordAmount);
				if(addUserAmountRecord){
					executeSuccess = true;
				}
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
	/**
	 * 减少钱包金额（后台操作）
	 * @param userId 用户Id
	 * @param reduceAmount 减少的钱包金额，单位：元
	 * @param remarks 操作备注
	 * @param backerId 操作的管理员id
	 * @param backerAccount 操作的管理员帐号
	 * @param ipAddress 操作时的ip地址
	 * @return 操作成功：返回true，失败：返回false
	 */
	@Transactional
	public boolean reduceWalletAmountOfBack(int userId, double reduceAmount, String remarks,
			int backerId, String backerAccount, String ipAddress){
		Timestamp curTime = DateUtil.getCurrentTime();
		
		UserDO userDO = userDao.queryUserByUserId(userId);
		if(userDO == null){
			return false;
		}
		
		//业务执行状态
		boolean executeSuccess = false;
		
		boolean reduceWalletAmount = userDao.reduceWalletAmount(userId, reduceAmount);
		if(reduceWalletAmount){
			BackerRecordWalletAmountDO backerRecordWalletAmount = new BackerRecordWalletAmountDO();
			backerRecordWalletAmount.setUserId(userDO.getUserId());
			backerRecordWalletAmount.setUserAccount(userDO.getUserAccount());
			backerRecordWalletAmount.setHandleType(2);
			backerRecordWalletAmount.setHandleAmount(reduceAmount);
			backerRecordWalletAmount.setRemarks(remarks);
			backerRecordWalletAmount.setBackerId(backerId);
			backerRecordWalletAmount.setBackerAccount(backerAccount);
			backerRecordWalletAmount.setIpAddress(ipAddress);
			backerRecordWalletAmount.setAddTime(curTime);
			
			boolean reduceWalletAmountRecord = backerRecordWalletAmountService.insertRecord(backerRecordWalletAmount);
			if(reduceWalletAmountRecord){
				//添加用户账户记录
				UserRecordAmountDO userRecordAmount = new UserRecordAmountDO();
				userRecordAmount.setUserId(userId);
				userRecordAmount.setInOutType(2);
				userRecordAmount.setAmount(reduceAmount);
				userRecordAmount.setServiceType("系统操作");
				userRecordAmount.setRemarks(remarks);
				userRecordAmount.setAddTime(curTime);
				
				boolean reduceUserAmountRecord = userRecordAmountService.insertRecord(userRecordAmount);
				if(reduceUserAmountRecord){
					executeSuccess = true;
				}
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
	/**
	 * 修改用户密码
	 * @param userId 用户Id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateUserPassword(int userId, String oldPassword, String newPassword){
		return userDao.updateUserPassword(userId, oldPassword, newPassword);
	}
	
	/**
	 * 修改用户密码
	 * @param userId 用户Id
	 * @param newPassword 新密码
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean updateUserPasswordModify(int userId, String newPassword){
		return userDao.updateUserPasswordModify(userId, newPassword);
	}
	
	/**
	 * 修改认证状态
	 * @param userId 用户Id
	 * @param authenStatus 认证状态，0：待认证，1：认证通过，2：认证失败
	 * @param updateTime 最后修改时间
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateAuthenStatus(int userId, int authenStatus, Timestamp updateTime){
		return userDao.updateAuthenStatus(userId, authenStatus, updateTime);
	}
	
	/**
	 * 修改会员身份
	 * @param userId 用户Id
	 * @param vipIdentity 会员身份，0：普通会员，1：VIP会员
	 * @param vipOutTime 会员到期时间
	 * @param updateTime 最后修改时间
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	@Transactional
	public boolean updateVipIdentity(int userId, int vipIdentity, Timestamp vipOutTime, Timestamp updateTime){
		UserAuthenDO userAuthenDO = userAuthenService.queryLastAuthened(userId);
		if(userAuthenDO == null){
			return false;
		}
		
		double receiptRate = 0;
		if(vipIdentity == 1){
			receiptRate = SystemConfig.pay_receiptRate_VIP;
		}else{
			receiptRate = SystemConfig.pay_receiptRate_init;
		}
		
		//业务执行状态
		boolean executeSuccess = false;
		
		boolean updateVipIdentity = userDao.updateVipIdentity(userId, vipIdentity, vipOutTime, receiptRate, updateTime);
		if(updateVipIdentity){
			CodePayUtil codePayUtil = new CodePayUtil();
			String custId = codePayUtil.businessRegister(userAuthenDO.getUserAccount(), userAuthenDO.getContactsName(),
					userAuthenDO.getIdCard(), userAuthenDO.getBankCard(), userAuthenDO.getBankNo(), receiptRate);
			if(StringUtil.isNotNull(custId)){
				executeSuccess = true;
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
	/**
	 * 修改交易信息
	 * @param userId 用户Id
	 * @param singleLimitAmount 单笔限额，单位：元
	 * @param receiptRate 收款费率
	 * @param updateTime 最后修改时间
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	@Transactional
	public boolean updatePayInfo(int userId, double singleLimitAmount, double receiptRate, Timestamp updateTime){
		//业务执行状态
		boolean executeSuccess = false;
		
		UserDO userDO = userDao.queryUserByUserId(userId);
		if(userDO == null){
			return false;
		}
		UserAuthenDO userAuthenDO = userAuthenService.queryLastAuthened(userDO.getUserId());
		if(userAuthenDO == null){
			return false;
		}
		
		boolean updatePayInfo = userDao.updatePayInfo(userId, singleLimitAmount, receiptRate, updateTime);
		if(updatePayInfo){
			CodePayUtil codePayUtil = new CodePayUtil();
			String custId = codePayUtil.businessRegister(userDO.getUserAccount(), userAuthenDO.getContactsName(),
					userAuthenDO.getIdCard(), userAuthenDO.getBankCard(), userAuthenDO.getBankNo(), receiptRate);
			if(StringUtil.isNotNull(custId)){
				executeSuccess = true;
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
	/**
	 * 查询用户总数（后台操作）
	 * @param userAccount 用户帐号
	 * @param inviteUserAccount 邀请人账号
	 * @param idCard 身份证号
	 * @param bankCard 结算银行卡号
	 * @param authenStatus 认证状态，0：待认证，1：认证通过，2：认证失败，查询全部填-1
	 * @param vipIdentity 会员身份，0：普通会员，1：VIP会员，查询全部填-1
	 * @param startTime 注册时间的开始时间
	 * @param endTime 注册时间的结束时间
	 * @return 操作成功：返回用户总数，操作失败：返回0
	 */
	public int queryUserTotalOfBack(String userAccount, String inviteUserAccount, String idCard, String bankCard, int authenStatus, int vipIdentity,
			Timestamp startTime, Timestamp endTime){
		return userDao.queryUserTotalOfBack(userAccount, inviteUserAccount, idCard, bankCard, authenStatus, vipIdentity, startTime, endTime);
	}
	
	/**
	 * 查询用户信息列表（后台操作）
	 * @param userAccount 用户帐号
	 * @param inviteUserAccount 邀请人账号
	 * @param idCard 身份证号
	 * @param bankCard 结算银行卡号
	 * @param authenStatus 认证状态，0：待认证，1：认证通过，2：认证失败，查询全部填-1
	 * @param vipIdentity 会员身份，0：普通会员，1：VIP会员，查询全部填-1
	 * @param startTime 注册时间的开始时间
	 * @param endTime 注册时间的结束时间
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回用户信息列表，操作失败：返回null
	 */
	public List<UserBackVO> queryUserListOfBack(String userAccount, String inviteUserAccount, String idCard, String bankCard, int authenStatus, int vipIdentity,
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize){
		return userDao.queryUserListOfBack(userAccount, inviteUserAccount, idCard, bankCard, authenStatus, vipIdentity,
				startTime, endTime, pageNumber, pageSize);
	}
	
	/**
	 * 查询VIP过期的用户列表（定时器操作，要将数据未过期但实际已过期的变成已过期）
	 * @param pageSize 每次查询的数量
	 * @return 操作成功：返回用户信息列表，操作失败：返回null
	 */
	public List<UserDO> queryOutVipList(int pageSize){
		return userDao.queryOutVipList(pageSize);
	}
	
}
