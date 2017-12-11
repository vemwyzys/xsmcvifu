package com.pandapay.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.NumberUtil;
import com.iqmkj.utils.StringUtil;
import com.pandapay.dao.IUserOrderPayDao;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.DO.UserAuthenDO;
import com.pandapay.entity.DO.UserDO;
import com.pandapay.entity.DO.UserOrderPayDO;
import com.pandapay.entity.DO.UserRecordAmountDO;
import com.pandapay.service.IUserAuthenService;
import com.pandapay.service.IUserOrderPayService;
import com.pandapay.service.IUserRecordAmountService;
import com.pandapay.service.IUserService;
import com.pandapay.utils.CodePayUtil;

import config.PayConfig_code;
import config.SystemConfig;

/**
 * 用户支付订单
 * @author 豆豆
 *
 */
@Service("userOrderPayService")
public class UserOrderPayServiceImpl implements IUserOrderPayService {

	/**用户支付订单*/
	@Autowired
	private IUserOrderPayDao userOrderPayDao;
	
	/** 用户信息 */
	@Autowired
	private IUserService userService;
	
	/** 用户认证信息 */
	@Autowired
	private IUserAuthenService userAuthenService;
	
	/** 用户账户金额记录 */
	@Autowired
	private IUserRecordAmountService userRecordAmountService;
	
	/**
	 * 创建支付订单（商家收款）
	 * @param userId 商家用户Id
	 * @param payAmount 支付金额,单位:元
	 * @param payWay 支付渠道,1：支付宝，2：微信
	 * @return 操作成功：返回code=1，操作失败：返回code!=1
	 */
	@Transactional
	public JsonObjectBO addPayOrder(int userId, double payAmount, int payWay){
		Timestamp curTime = DateUtil.getCurrentTime();
		JsonObjectBO jsonObjectBO = new JsonObjectBO();
		
		UserDO userDO = userService.queryUserByUserId(userId);
		if(userDO == null){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("未查询到下单人信息");
			return jsonObjectBO;
		}
		UserAuthenDO userAuthenDO = userAuthenService.queryLastAuthened(userDO.getUserId());
		if(userAuthenDO == null){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("下单人未经过实名认证");
			return jsonObjectBO;
		}
		
		double totalAmount = userDO.getWalletAmount() + userDO.getRedPacketAmount();
		if(totalAmount < SystemConfig.payOrderCost){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("账户余额不足" + SystemConfig.payOrderCost + "元无法创建收款订单，请充值后再操作");
			return jsonObjectBO;
		}
		
		String orderNo = DateUtil.longToTimeStr(curTime.getTime(), DateUtil.dateFormat6) + NumberUtil.createNumberStr(11);
		String payType;
		if(payWay == 1){
			//支付宝
			payType = "05";
		}else if(payWay == 2){
			//微信
			payType = "04";
		}else{
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("支付方式参数错误");
			return jsonObjectBO;
		}
		String goodsName = "商家收款";
		int serviceType = 1;
		
		CodePayUtil codePayUtil = new CodePayUtil();
		String payCodeInfo = codePayUtil.createOrder(orderNo, SystemConfig.systemUserPayId, (long) (payAmount*100), PayConfig_code.notifyUrl_pay_service, payType, goodsName);
		if(!StringUtil.isNotNull(payCodeInfo)){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("支付平台下单失败");
			return jsonObjectBO;
		}
		
		//优先使用红包，再使用钱包
		boolean reduceAmountBoo = false;
		if(userDO.getRedPacketAmount() >= SystemConfig.payOrderCost){
			reduceAmountBoo = userService.reduceRedPacketAmount(userDO.getUserId(), SystemConfig.payOrderCost);
		}else{
			if(userDO.getRedPacketAmount() > 0){
				reduceAmountBoo = userService.reduceRedPacketAmount(userDO.getUserId(), userDO.getRedPacketAmount());
				if(reduceAmountBoo){
					userService.reduceWalletAmount(userDO.getUserId(), SystemConfig.payOrderCost - userDO.getRedPacketAmount());
				}
			}else{
				reduceAmountBoo = userService.reduceWalletAmount(userDO.getUserId(), SystemConfig.payOrderCost);
			}
		}
		
		//数据回滚
		if(!reduceAmountBoo){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		//业务执行状态
		boolean executeSuccess = false;
		
		//订单返利模式,0：无返利，1：有返利
		int orderRebateModel = 0;
		if(userDO.getVipIdentity() == 0 && userDO.getInviteUserId() > 0){
			orderRebateModel = 1;
		}
		
		UserOrderPayDO userOrderPayDO = new UserOrderPayDO();
		userOrderPayDO.setOrderNo(orderNo);
		userOrderPayDO.setUserId(userDO.getUserId());
		userOrderPayDO.setUserAccount(userDO.getUserAccount());
		userOrderPayDO.setPayAmount(payAmount);
		userOrderPayDO.setServiceType(serviceType);
		userOrderPayDO.setPayWay(payWay);
		userOrderPayDO.setOrderRebateModel(orderRebateModel);
		userOrderPayDO.setAddTime(curTime);
		userOrderPayDO.setPayStatus(0);
		userOrderPayDO.setRemarks("收款订单创建成功");
		userOrderPayDO.setUpdateTime(curTime);
		
		boolean insertOrderPay = userOrderPayDao.insertOrderPay(userOrderPayDO);
		if(insertOrderPay){
			UserRecordAmountDO userRecordAmount = new UserRecordAmountDO();
			userRecordAmount.setUserId(userDO.getUserId());
			userRecordAmount.setInOutType(2);
			userRecordAmount.setAmount(SystemConfig.payOrderCost);
			userRecordAmount.setServiceType("我要收款手续费");
			userRecordAmount.setRemarks("交易订单号：" + userOrderPayDO.getOrderNo());
			userRecordAmount.setAddTime(curTime);
			
			executeSuccess = userRecordAmountService.insertRecord(userRecordAmount);
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		if(executeSuccess){
			JSONObject jsonData = new JSONObject();
			jsonData.put("orderNo", orderNo);
			jsonData.put("payAmount", payAmount);
			jsonData.put("serviceType", serviceType);
			jsonData.put("payWay", payWay);
			jsonData.put("payCodeInfo", payCodeInfo);
			
			jsonObjectBO.setCode(1);
			jsonObjectBO.setMessage("收款订单创建成功");
			jsonObjectBO.setData(jsonData);
			return jsonObjectBO;
		}else{
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("收款订单创建失败");
			return jsonObjectBO;
		}
	}
	
	/**
	 * 创建充值订单（用户充值）
	 * @param userId 用户Id
	 * @param payAmount 充值金额,单位:元
	 * @param payWay 支付渠道,1：支付宝，2：微信
	 * @return 操作成功：返回code=1，操作失败：返回code!=1
	 */
	public JsonObjectBO addRechargeOrder(int userId, double payAmount, int payWay){
		Timestamp curTime = DateUtil.getCurrentTime();
		JsonObjectBO jsonObjectBO = new JsonObjectBO();
		
		UserDO userDO = userService.queryUserByUserId(userId);
		if(userDO == null){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("未查询到下单人信息");
			return jsonObjectBO;
		}
		UserAuthenDO userAuthenDO = userAuthenService.queryLastAuthened(userDO.getUserId());
		if(userAuthenDO == null){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("下单人未经过实名认证");
			return jsonObjectBO;
		}
		
		String orderNo = DateUtil.longToTimeStr(curTime.getTime(), DateUtil.dateFormat6) + NumberUtil.createNumberStr(11);
		String payType;
		if(payWay == 1){
			//支付宝
			payType = "05";
		}else if(payWay == 2){
			//微信
			payType = "04";
		}else{
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("支付方式参数错误");
			return jsonObjectBO;
		}
		String goodsName = "会员充值";
		int serviceType = 2;
		
		CodePayUtil codePayUtil = new CodePayUtil();
		String payCodeInfo = codePayUtil.createOrder(orderNo, userAuthenDO.getUserPayId(), (long) (payAmount*100), PayConfig_code.notifyUrl_pay_service, payType, goodsName);
		if(!StringUtil.isNotNull(payCodeInfo)){
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("支付平台下单失败");
			return jsonObjectBO;
		}
		
		UserOrderPayDO userOrderPayDO = new UserOrderPayDO();
		userOrderPayDO.setOrderNo(orderNo);
		userOrderPayDO.setUserId(userDO.getUserId());
		userOrderPayDO.setUserAccount(userDO.getUserAccount());
		userOrderPayDO.setPayAmount(payAmount);
		userOrderPayDO.setServiceType(serviceType);
		userOrderPayDO.setPayWay(payWay);
		userOrderPayDO.setOrderRebateModel(0);
		userOrderPayDO.setAddTime(curTime);
		userOrderPayDO.setPayStatus(0);
		userOrderPayDO.setRemarks("充值订单创建成功");
		userOrderPayDO.setUpdateTime(curTime);
		
		boolean insertOrderPay = userOrderPayDao.insertOrderPay(userOrderPayDO);
		if(insertOrderPay){
			JSONObject jsonData = new JSONObject();
			jsonData.put("orderNo", orderNo);
			jsonData.put("payAmount", payAmount);
			jsonData.put("serviceType", serviceType);
			jsonData.put("payWay", payWay);
			jsonData.put("payCodeInfo", payCodeInfo);
			
			jsonObjectBO.setCode(1);
			jsonObjectBO.setMessage("充值订单创建成功");
			jsonObjectBO.setData(jsonData);
			return jsonObjectBO;
		}else{
			jsonObjectBO.setCode(2);
			jsonObjectBO.setMessage("充值订单创建失败");
			return jsonObjectBO;
		}
	}
	
	/**
	 * 修改订单状态
	 * @param orderNo 订单号
	 * @param payStatus 支付状态,0：未知，1：支付成功, 2:支付失败, 3:其他
	 * @param remarks 支付备注
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateOrderPayStatus(String orderNo, int payStatus, String remarks){
		Timestamp curTime = DateUtil.getCurrentTime();
		return userOrderPayDao.updateOrderPayStatus(orderNo, payStatus, remarks, curTime);
	}
	
	/**
	 * 订单交易成功（用户下单）
	 * @param userOrderPayDO 订单信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	@Transactional
	public boolean orderPaySuccess(UserOrderPayDO userOrderPayDO){
		Timestamp curTime = DateUtil.getCurrentTime();
		
		boolean updateOrderPayStatus = userOrderPayDao.updateOrderPayStatus(userOrderPayDO.getOrderNo(), 1, "交易成功", curTime);
		if(!updateOrderPayStatus){
			return updateOrderPayStatus;
		}
		
		//没有返利
		if(userOrderPayDO.getOrderRebateModel() != 1){
			return updateOrderPayStatus;
		}
		
		UserDO userDO = userService.queryUserByUserId(userOrderPayDO.getUserId());
		if(userDO == null || userDO.getInviteUserId() <= 0){
			return updateOrderPayStatus;
		}
		
		//返利金额
		double returnAmount = userOrderPayDO.getPayAmount() * SystemConfig.pay_return_rate;
		
		//上级
		UserDO uperUser = userService.queryUserByUserId(userDO.getInviteUserId());
		if(uperUser == null || returnAmount < 0.00001){
			return updateOrderPayStatus;
		}
		
		//上级返利
		boolean addUperAmount = userService.addRedPacketAmount(uperUser.getUserId(), returnAmount);
		if(addUperAmount){
			//设置事务回滚点
			Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
			
			UserRecordAmountDO userRecordAmount = new UserRecordAmountDO();
			userRecordAmount.setUserId(uperUser.getUserId());
			userRecordAmount.setInOutType(1);
			userRecordAmount.setAmount(returnAmount);
			userRecordAmount.setServiceType("交易返利");
			userRecordAmount.setRemarks("交易订单号：" + userOrderPayDO.getOrderNo());
			userRecordAmount.setAddTime(curTime);
			
			boolean addRecordAmount = userRecordAmountService.insertRecord(userRecordAmount);
			if(!addRecordAmount){
				//事务回滚
				TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
				return updateOrderPayStatus;
			}
		}
		
		//上上级
		if(uperUser.getInviteUserId() <= 0){
			return updateOrderPayStatus;
		}
		UserDO uperUperUser = userService.queryUserByUserId(uperUser.getInviteUserId());
		if(uperUperUser == null){
			return updateOrderPayStatus;
		}
		
		//上上级返利
		boolean addUperUperAmount = userService.addRedPacketAmount(uperUperUser.getUserId(), returnAmount);
		if(addUperUperAmount){
			//设置事务回滚点
			Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
			
			UserRecordAmountDO userRecordAmount = new UserRecordAmountDO();
			userRecordAmount.setUserId(uperUperUser.getUserId());
			userRecordAmount.setInOutType(1);
			userRecordAmount.setAmount(returnAmount);
			userRecordAmount.setServiceType("交易返利");
			userRecordAmount.setRemarks("交易订单号：" + userOrderPayDO.getOrderNo());
			userRecordAmount.setAddTime(curTime);

			boolean addRecordAmount = userRecordAmountService.insertRecord(userRecordAmount);
			if(!addRecordAmount){
				//事务回滚
				TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
				return updateOrderPayStatus;
			}
		}
		
		return updateOrderPayStatus;
	}
	
	/**
	 * 订单交易失败（用户下单）
	 * @param userOrderPayDO 订单信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	@Transactional
	public boolean orderPayFail(UserOrderPayDO userOrderPayDO){
		Timestamp curTime = DateUtil.getCurrentTime();
		
		//业务执行状态
		boolean executeSuccess = false;
		
		boolean updateOrderPayStatus = userOrderPayDao.updateOrderPayStatus(userOrderPayDO.getOrderNo(), 2, "交易失败", curTime);
		if(updateOrderPayStatus){
			boolean addRedPacketAmount = userService.addRedPacketAmount(userOrderPayDO.getUserId(), SystemConfig.payOrderCost);
			if(addRedPacketAmount){
				UserRecordAmountDO userRecordAmount = new UserRecordAmountDO();
				userRecordAmount.setUserId(userOrderPayDO.getUserId());
				userRecordAmount.setInOutType(1);
				userRecordAmount.setAmount(SystemConfig.payOrderCost);
				userRecordAmount.setServiceType("交易退款");
				userRecordAmount.setRemarks("交易失败，订单号：" + userOrderPayDO.getOrderNo());
				userRecordAmount.setAddTime(curTime);
				
				executeSuccess = userRecordAmountService.insertRecord(userRecordAmount);
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return executeSuccess;
	}
	
	/**
	 * 充值交易成功（用户充值）
	 * @param userOrderPayDO 订单信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	@Transactional
	public boolean rechargePaySuccess(UserOrderPayDO userOrderPayDO){
		Timestamp curTime = DateUtil.getCurrentTime();
		
		//业务执行状态
		boolean executeSuccess = false;
		
		boolean updateOrderPayStatus = userOrderPayDao.updateOrderPayStatus(userOrderPayDO.getOrderNo(), 1, "充值成功", curTime);
		if(updateOrderPayStatus){
			boolean addWalletAmount = userService.addWalletAmount(userOrderPayDO.getUserId(), userOrderPayDO.getPayAmount());
			if(addWalletAmount){
				UserRecordAmountDO userRecordAmount = new UserRecordAmountDO();
				userRecordAmount.setUserId(userOrderPayDO.getUserId());
				userRecordAmount.setInOutType(1);
				userRecordAmount.setAmount(userOrderPayDO.getPayAmount());
				userRecordAmount.setServiceType("在线充值");
				userRecordAmount.setRemarks("充值成功，订单号：" + userOrderPayDO.getOrderNo());
				userRecordAmount.setAddTime(curTime);
				
				executeSuccess = userRecordAmountService.insertRecord(userRecordAmount);
			}
		}
		
		//数据回滚
		if(!executeSuccess){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		//充VIP会员
		if(userOrderPayDO.getPayAmount() == 50 || userOrderPayDO.getPayAmount() == 100 || userOrderPayDO.getPayAmount() == 360){
			UserDO userDO = userService.queryUserByUserId(userOrderPayDO.getUserId());
			if(userDO != null){
				//添加的时间（毫秒）
				long addVipTimeLong = 0;
				if(userOrderPayDO.getPayAmount() == 50){
					addVipTimeLong = 30*24*60*60*1000;  //一个月
				}else if (userOrderPayDO.getPayAmount() == 100){
					addVipTimeLong = 90*24*60*60*1000;  //三个月
				}else if (userOrderPayDO.getPayAmount() == 360){
					addVipTimeLong = 90*24*60*60*1000;  //三个月
				}
				
				long vipNewOutTimeLong = 0;
				//当前身份是会员
				if(userDO.getVipIdentity() == 0 || (userDO.getVipIdentity() == 1 && userDO.getVipOutTime().getTime() <= curTime.getTime())){
					vipNewOutTimeLong = curTime.getTime() + addVipTimeLong;
				}else{
					vipNewOutTimeLong = userDO.getVipOutTime().getTime() + addVipTimeLong;
				}
				Timestamp vipOutTime = new Timestamp(vipNewOutTimeLong);
				
				userService.updateVipIdentity(userDO.getUserId(), 1, vipOutTime, curTime);
			}
		}
		
		return executeSuccess;
	}
	
	/**
	 * 充值交易失败（用户充值）
	 * @param userOrderPayDO 订单信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean rechargePayFail(UserOrderPayDO userOrderPayDO){
		return userOrderPayDao.updateOrderPayStatus(userOrderPayDO.getOrderNo(), 2, "充值失败", DateUtil.getCurrentTime());
	}
	
	/**
	 * 查询订单
	 * @param orderNo 订单号
	 * @return 操作成功：返回订单信息，操作失败：返回null
	 */
	public UserOrderPayDO queryOrder(String orderNo){
		return userOrderPayDao.queryOrder(orderNo);
	}
	
	/**
	 * 查询订单总数（用户操作）
	 * @param userId 用户id
	 * @return 操作成功：返回订单总数，操作失败：返回0
	 */
	public int queryOrderTotalOfUser(int userId){
		return userOrderPayDao.queryOrderTotalOfUser(userId);
	}
	
	/**
	 * 查询订单列表（用户操作）
	 * @param userId 用户id
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回订单列表，操作失败：返回null
	 */
	public List<UserOrderPayDO> queryOrderListOfUser(int userId, int pageNumber, int pageSize){
		return userOrderPayDao.queryOrderListOfUser(userId, pageNumber, pageSize);
	}
	
	/**
	 * 查询订单总数（后台操作）
	 * @param orderNo 订单号
	 * @param userAccount 用户账号
	 * @param serviceType 业务类型,1:商户创建订单（用户下单）,2:充值，查询全部填0
	 * @param payWay 支付渠道,1：支付宝，2：微信，查询全部填0
	 * @param payStatus 支付状态,0：未知，1：支付成功, 2:支付失败, 3:其他，查询全部填-1
	 * @param startTime 申请时间的开始时间
	 * @param endTime 申请时间的结束时间
	 * @return 操作成功：返回订单总数，操作失败：返回0
	 */
	public int queryOrderTotalOfBack(String orderNo, String userAccount, int serviceType, int payWay, int payStatus,
			Timestamp startTime, Timestamp endTime){
		return userOrderPayDao.queryOrderTotalOfBack(orderNo, userAccount, serviceType, payWay, payStatus, startTime, endTime);
	}
	
	/**
	 * 查询订单列表（后台操作）
	 * @param orderNo 订单号
	 * @param userAccount 用户账号
	 * @param serviceType 业务类型,1:商户创建订单（用户下单）,2:充值，查询全部填0
	 * @param payWay 支付渠道,1：支付宝，2：微信，查询全部填0
	 * @param payStatus 支付状态,0：未知，1：支付成功, 2:支付失败, 3:其他，查询全部填-1
	 * @param startTime 申请时间的开始时间
	 * @param endTime 申请时间的结束时间
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回订单列表，操作失败：返回null
	 */
	public List<UserOrderPayDO> queryOrderListOfBack(String orderNo, String userAccount, int serviceType, int payWay, int payStatus,
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize){
		return userOrderPayDao.queryOrderListOfBack(orderNo, userAccount, serviceType, payWay, payStatus,
				startTime, endTime, pageNumber, pageSize);
	}
	
}
