package com.pandapay.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.ISystemAdsDao;
import com.pandapay.entity.DO.SystemAdsDO;

/**
 * 首页广告
 * @author 豆豆
 *
 */
@Repository
public class SystemAdsDaoImpl implements ISystemAdsDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增广告
	 * @param systemAds 广告信息
	 * @return 操作成功：返回新增后的广告信息，操作失败：返回null
	 */
	public SystemAdsDO insertAds(SystemAdsDO systemAds){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("SystemAds_insertAds", systemAds);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return systemAds;
		}else{
			return null;
		}
	}
	
	/**
	 * 查询全部的广告信息
	 * @return 操作成功：返回广告列表，操作失败：返回null
	 */
	public List<SystemAdsDO> queryAdsList(){
		List<SystemAdsDO> resultList = null;
		try{
			resultList = sqlSessionTemplate.selectList("SystemAds_queryAdsList");
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultList;
	}
	
	/**
	 * 查询广告信息
	 * @param adsId 广告id
	 * @return 操作成功：返回广告信息，操作失败：返回null
	 */
	public SystemAdsDO queryAdsByAdsId(int adsId){
		SystemAdsDO systemAdsDO = null;
		try{
			systemAdsDO = sqlSessionTemplate.selectOne("SystemAds_queryAdsByAdsId", adsId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return systemAdsDO;
	}
	
	/**
	 * 查询最前面的广告
	 * @param showPosition 广告显示位置,0:顶部广告,1:底部广告
	 * @return 操作成功：返回广告信息，操作失败：返回null
	 */
	public SystemAdsDO queryPreAds(int showPosition){
		SystemAdsDO systemAdsDO = null;
		try{
			systemAdsDO = sqlSessionTemplate.selectOne("SystemAds_queryPreAds", showPosition);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return systemAdsDO;
	}
	
	/**
	 * 查询后链广告
	 * @param showPosition 广告显示位置,0:顶部广告,1:底部广告
	 * @param preAdsId 前一位置广告id
	 * @return 操作成功：返回广告信息，操作失败：返回null
	 */
	public SystemAdsDO queryNextAds(int showPosition, int preAdsId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("showPosition", showPosition);
		map.put("preAdsId", preAdsId);
		
		SystemAdsDO systemAdsDO = null;
		try{
			systemAdsDO = sqlSessionTemplate.selectOne("SystemAds_queryNextAds", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return systemAdsDO;
	}
	
	/**
	 * 修改广告信息
	 * @param systemAds 广告信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateAds(SystemAdsDO systemAds){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("SystemAds_updateAds", systemAds);
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
	 * 修改广告的前一位置广告id
	 * @param adsId 广告id
	 * @param preAdsId 前一位置的广告id
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateAdsPreAdsId(int adsId, int preAdsId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("adsId", adsId);
		map.put("preAdsId", preAdsId);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.update("SystemAds_updateAdsPreAdsId", map);
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
	 * 删除广告
	 * @param adsId 广告id
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean deleteAds(int adsId){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.delete("SystemAds_deleteAds", adsId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		
		if(resultNumber > 0){
			return true;
		}else{
			return false;
		}
	}
	
}
