package com.pandapay.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.IUserRelationWeixinDao;
import com.pandapay.entity.DO.UserRelationWeixinDO;

/**
 * 用户账号关联微信
 * @author 豆豆
 *
 */
@Repository
public class UserRelationWeixinDaoImpl implements IUserRelationWeixinDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增用户账号关联微信
	 * @param userRelationWeixin 用户账号关联微信信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertRelation(UserRelationWeixinDO userRelationWeixin){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("UserRelationWeixin_insertRelation", userRelationWeixin);
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
	 * 查询关联信息
	 * @param userId 用户Id
	 * @return 操作成功：返回关联信息，操作失败：返回null
	 */
	public UserRelationWeixinDO queryRelationByUserId(int userId){
		UserRelationWeixinDO userRelationWeixinDO = null;
		try{
			userRelationWeixinDO = sqlSessionTemplate.selectOne("UserRelationWeixin_queryRelationByUserId", userId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return userRelationWeixinDO;
	}
	
	/**
	 * 查询关联信息
	 * @param openId 微信openId
	 * @return 操作成功：返回关联信息，操作失败：返回null
	 */
	public UserRelationWeixinDO queryRelationByOpenId(String openId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("openId", openId);
		
		UserRelationWeixinDO userRelationWeixinDO = null;
		try{
			userRelationWeixinDO = sqlSessionTemplate.selectOne("UserRelationWeixin_queryRelationByOpenId", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return userRelationWeixinDO;
	}
	
}
