package com.pandapay.dao;

import java.util.List;

import com.pandapay.entity.DO.SystemAdsDO;

/**
 * 首页广告
 * @author 豆豆
 *
 */
public interface ISystemAdsDao {

	/**
	 * 新增广告
	 * @param systemAds 广告信息
	 * @return 操作成功：返回新增后的广告信息，操作失败：返回null
	 */
	public SystemAdsDO insertAds(SystemAdsDO systemAds);
	
	/**
	 * 查询全部的广告信息
	 * @return 操作成功：返回广告列表，操作失败：返回null
	 */
	public List<SystemAdsDO> queryAdsList();
	
	/**
	 * 查询广告信息
	 * @param adsId 广告id
	 * @return 操作成功：返回广告信息，操作失败：返回null
	 */
	public SystemAdsDO queryAdsByAdsId(int adsId);
	
	/**
	 * 查询最前面的广告
	 * @param showPosition 广告显示位置,0:顶部广告,1:底部广告
	 * @return 操作成功：返回广告信息，操作失败：返回null
	 */
	public SystemAdsDO queryPreAds(int showPosition);
	
	/**
	 * 查询后链广告
	 * @param showPosition 广告显示位置,0:顶部广告,1:底部广告
	 * @param preAdsId 前一位置广告id
	 * @return 操作成功：返回广告信息，操作失败：返回null
	 */
	public SystemAdsDO queryNextAds(int showPosition, int preAdsId);
	
	/**
	 * 修改广告信息
	 * @param systemAds 广告信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateAds(SystemAdsDO systemAds);
	
	/**
	 * 修改广告的前一位置广告id
	 * @param adsId 广告id
	 * @param preAdsId 前一位置的广告id
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean updateAdsPreAdsId(int adsId, int preAdsId);
	
	/**
	 * 删除广告
	 * @param adsId 广告id
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean deleteAds(int adsId);
	
}
