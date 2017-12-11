package com.pandapay.service;

import java.sql.Timestamp;
import java.util.List;

import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.DO.UserOrderPayDO;

/**
 * 用户支付订单
 * @author 豆豆
 *
 */
public interface IUserOrderPayService {
	
	/**
	 * 创建支付订单（商家收款）
	 * @param userId 商家用户Id
	 * @param payAmount 支付金额,单位:元
	 * @param payWay 支付渠道,1：支付宝，2：微信
	 * @return 操作成功：返回code=1，操作失败：返回code!=1
	 */
	public JsonObjectBO addPayOrder(int userId, double payAmount, int payWay);
	
	/**
	 * 创建充值订单（用户充值）
	 * @param userId 用户Id
	 * @param payAmount 充值金额,单位:元
	 * @param payWay 支付渠道,1：支付宝，2：微信
	 * @return 操作成功：返回code=1，操作失败：返回code!=1
	 */
	public JsonObjectBO addRechargeOrder(int userId, double payAmount, int payWay);
	
	/**
	 * 修改订单状态
	 * @param orderNo 订单号
	 * @param payStatus 支付状态,0：未知，1：支付成功, 2:支付失败, 3:其他
	 * @param remarks 支付备注
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateOrderPayStatus(String orderNo, int payStatus, String remarks);
	
	/**
	 * 订单交易成功（用户下单）
	 * @param userOrderPayDO 订单信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean orderPaySuccess(UserOrderPayDO userOrderPayDO);
	
	/**
	 * 订单交易失败（用户下单）
	 * @param userOrderPayDO 订单信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean orderPayFail(UserOrderPayDO userOrderPayDO);
	
	/**
	 * 充值交易成功（用户充值）
	 * @param userOrderPayDO 订单信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean rechargePaySuccess(UserOrderPayDO userOrderPayDO);
	
	/**
	 * 充值交易失败（用户充值）
	 * @param userOrderPayDO 订单信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean rechargePayFail(UserOrderPayDO userOrderPayDO);
	
	/**
	 * 查询订单
	 * @param orderNo 订单号
	 * @return 操作成功：返回订单信息，操作失败：返回null
	 */
	public UserOrderPayDO queryOrder(String orderNo);
	
	/**
	 * 查询订单总数（用户操作）
	 * @param userId 用户id
	 * @return 操作成功：返回订单总数，操作失败：返回0
	 */
	public int queryOrderTotalOfUser(int userId);
	
	/**
	 * 查询订单列表（用户操作）
	 * @param userId 用户id
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回订单列表，操作失败：返回null
	 */
	public List<UserOrderPayDO> queryOrderListOfUser(int userId, int pageNumber, int pageSize);
	
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
			Timestamp startTime, Timestamp endTime);
	
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
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize);
	
}
