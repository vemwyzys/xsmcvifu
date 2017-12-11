package com.pandapay.service;

import com.pandapay.entity.DO.UserRelationWeixinDO;

/**
 * 用户账号关联微信
 * @author 豆豆
 *
 */
public interface IUserRelationWeixinService {

	/**
	 * 新增用户账号关联微信
	 * @param userRelationWeixin 用户账号关联微信信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertRelation(UserRelationWeixinDO userRelationWeixin);
	
	/**
	 * 查询关联信息
	 * @param userId 用户Id
	 * @return 操作成功：返回关联信息，操作失败：返回null
	 */
	public UserRelationWeixinDO queryRelationByUserId(int userId);
	
	/**
	 * 查询关联信息
	 * @param openId 微信openId
	 * @return 操作成功：返回关联信息，操作失败：返回null
	 */
	public UserRelationWeixinDO queryRelationByOpenId(String openId);
	
}
