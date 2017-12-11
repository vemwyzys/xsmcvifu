package com.pandapay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandapay.dao.IUserRelationWeixinDao;
import com.pandapay.entity.DO.UserRelationWeixinDO;
import com.pandapay.service.IUserRelationWeixinService;

/**
 * 用户账号关联微信
 * @author 豆豆
 *
 */
@Service("userRelationWeixinService")
public class UserRelationWeixinServiceImpl implements IUserRelationWeixinService {
	
	/**用户账号关联微信*/
	@Autowired
	private IUserRelationWeixinDao userRelationWeixinDao;
	
	/**
	 * 新增用户账号关联微信
	 * @param userRelationWeixin 用户账号关联微信信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertRelation(UserRelationWeixinDO userRelationWeixin){
		return userRelationWeixinDao.insertRelation(userRelationWeixin);
	}
	
	/**
	 * 查询关联信息
	 * @param userId 用户Id
	 * @return 操作成功：返回关联信息，操作失败：返回null
	 */
	public UserRelationWeixinDO queryRelationByUserId(int userId){
		return userRelationWeixinDao.queryRelationByUserId(userId);
	}
	
	/**
	 * 查询关联信息
	 * @param openId 微信openId
	 * @return 操作成功：返回关联信息，操作失败：返回null
	 */
	public UserRelationWeixinDO queryRelationByOpenId(String openId){
		return userRelationWeixinDao.queryRelationByOpenId(openId);
	}
	
}
