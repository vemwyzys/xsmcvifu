package com.pandapay.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.IUserOrderTakeCashDao;
import com.pandapay.entity.DO.UserOrderTakeCashDO;

/**
 * 用户提现订单
 * @author 豆豆
 *
 */
@Repository
public class UserOrderTakeCashDaoImpl implements IUserOrderTakeCashDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增提现记录
	 * @param userOrderTakeCash 提现记录
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertTakeCash(UserOrderTakeCashDO userOrderTakeCash){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("UserOrderTakeCash_insertTakeCash", userOrderTakeCash);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 修改提现状态
	 * @param orderNo 订单号
	 * @param takeCashStatus 提现状态,0:待打款,1:打款成功,2:打款失败
	 * @param remarks 提现备注
	 * @param updateTime 更新时间
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateTakeCashStatus(String orderNo, int takeCashStatus, String remarks, Timestamp updateTime){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderNo", orderNo);
		map.put("takeCashStatus", takeCashStatus);
		map.put("remarks", remarks);
		map.put("updateTime", updateTime);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("UserOrderTakeCash_updateTakeCashStatus", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 查询用户提现记录总数（用户操作）
	 * @param userId 用户id
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryTakeCashTotalOfUser(int userId){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("UserOrderTakeCash_queryTakeCashTotalOfUser", userId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
	/**
	 * 查询用户提现记录列表（用户操作）
	 * @param userId 用户id
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回记录列表，操作失败：返回null
	 */
	public List<UserOrderTakeCashDO> queryTakeCashListOfUser(int userId, int pageNumber, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		
		map.put("startNumber", pageNumber*pageSize);
		map.put("pageSize", pageSize);
		
		List<UserOrderTakeCashDO> resultList = null;
		try{
			resultList = sqlSessionTemplate.selectList("UserOrderTakeCash_queryTakeCashListOfUser", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultList;
	}
	
	/**
	 * 查询用户提现记录总数（后台操作）
	 * @param orderNo 订单号
	 * @param userAccount 用户帐号
	 * @param takeCashWay 收款方式,1:支付宝, 2:微信,查询全部填0
	 * @param takeCashStatus 提现状态,0:待打款,1:打款成功,2:打款失败,查询全部填-1
	 * @param startTime 提现申请时间的开始时间
	 * @param endTime 提现申请时间的结束时间
	 * @return 操作成功：返回记录总数，操作失败：返回0
	 */
	public int queryTakeCashTotalOfBack(String orderNo, String userAccount, int takeCashWay, int takeCashStatus, Timestamp startTime, Timestamp endTime){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderNo", orderNo);
		map.put("userAccount", userAccount);
		map.put("takeCashWay", takeCashWay);
		map.put("takeCashStatus", takeCashStatus);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("UserOrderTakeCash_queryTakeCashTotalOfBack", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
	/**
	 * 查询用户提现记录列表（后台操作）
	 * @param orderNo 订单号
	 * @param userAccount 用户帐号
	 * @param takeCashWay 收款方式,1:支付宝, 2:微信,查询全部填0
	 * @param takeCashStatus 提现状态,0:待打款,1:打款成功,2:打款失败,查询全部填-1
	 * @param startTime 提现申请时间的开始时间
	 * @param endTime 提现申请时间的结束时间
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回记录列表，操作失败：返回null
	 */
	public List<UserOrderTakeCashDO> queryTakeCashListOfBack(String orderNo, String userAccount, int takeCashWay, int takeCashStatus,
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderNo", orderNo);
		map.put("userAccount", userAccount);
		map.put("takeCashWay", takeCashWay);
		map.put("takeCashStatus", takeCashStatus);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		map.put("startNumber", pageNumber*pageSize);
		map.put("pageSize", pageSize);
		
		List<UserOrderTakeCashDO> resultList = null;
		try{
			resultList = sqlSessionTemplate.selectList("UserOrderTakeCash_queryTakeCashListOfBack", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultList;
	}
	
}
