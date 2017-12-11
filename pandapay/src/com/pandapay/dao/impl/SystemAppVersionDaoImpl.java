package com.pandapay.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iqmkj.utils.LogUtil;
import com.pandapay.dao.ISystemAppVersionDao;
import com.pandapay.entity.DO.SystemAppVersionDO;

/**
 * APP版本管理
 * @author 豆豆
 *
 */
@Repository
public class SystemAppVersionDaoImpl implements ISystemAppVersionDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 新增APP版本信息
	 * @param systemAppVersion APP版本信息
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean insertSystemAppVersion(SystemAppVersionDO systemAppVersion){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.insert("SystemAppVersion_insertSystemAppVersion", systemAppVersion);
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
	 * 查询APP版本信息
	 * @param versionId 版本Id
	 * @return 操作成功：返回APP版本信息，操作失败：返回null
	 */
	public SystemAppVersionDO queryVersionByVersionId(int versionId){
		SystemAppVersionDO systemAppVersionDO = null;
		try{
			systemAppVersionDO = sqlSessionTemplate.selectOne("SystemAppVersion_queryVersionByVersionId", versionId);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return systemAppVersionDO;
	}
	
	/**
	 * 查询最新的APP版本信息
	 * @param versionType 版本类型,1:Android,2:IOS
	 * @return 操作成功：返回APP版本信息，操作失败：返回null
	 */
	public SystemAppVersionDO queryNewByVersionType(int versionType){
		SystemAppVersionDO systemAppVersionDO = null;
		try{
			systemAppVersionDO = sqlSessionTemplate.selectOne("SystemAppVersion_queryNewByVersionType", versionType);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return systemAppVersionDO;
	}
	
	/**
	 * 验证APP版本是否已存在
	 * @param versionType 版本类型,1:Android,2:IOS
	 * @param versionNumber 版本号
	 * @return 已存在：返回true，不存在：返回false
	 */
	public boolean validateSystemAppVersion(int versionType, String versionNumber){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("versionType", versionType);
		map.put("versionNumber", versionNumber);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("SystemAppVersion_validateSystemAppVersion", map);
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
	 * 查询APP版本总数（后台操作）
	 * @param versionType 版本类型,1:Android,2:IOS,查询全部填0
	 * @param versionNumber 版本号
	 * @param startTime 添加时间的开始时间
	 * @param endTime 添加时间的结束时间
	 * @return 操作成功：返回APP版本总数，操作失败：返回0
	 */
	public int querySystemAppVersionTotalOfBack(int versionType, String versionNumber, Timestamp startTime, Timestamp endTime){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("versionType", versionType);
		map.put("versionNumber", versionNumber);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.selectOne("SystemAppVersion_querySystemAppVersionTotalOfBack", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultNumber;
	}
	
	/**
	 * 查询APP版本列表（后台操作）
	 * @param versionType 版本类型,1:Android,2:IOS,查询全部填0
	 * @param versionNumber 版本号
	 * @param startTime 添加时间的开始时间
	 * @param endTime 添加时间的结束时间
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回APP版本列表，操作失败：返回null
	 */
	public List<SystemAppVersionDO> querySystemAppVersionListOfBack(int versionType, String versionNumber,
			Timestamp startTime, Timestamp endTime, int pageNumber, int pageSize){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("versionType", versionType);
		map.put("versionNumber", versionNumber);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		
		map.put("startNumber", pageNumber*pageSize);
		map.put("pageSize", pageSize);
		
		List<SystemAppVersionDO> resultList = null;
		try{
			resultList = sqlSessionTemplate.selectList("SystemAppVersion_querySystemAppVersionListOfBack", map);
		}catch(Exception e){
			LogUtil.printErrorLog(e);
		}
		return resultList;
	}
	
	/**
	 * 删除APP版本信息
	 * @param versionId 版本Id
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean deleteSystemAppVersion(int versionId){
		int resultNumber = 0;
		try{
			resultNumber = sqlSessionTemplate.delete("SystemAppVersion_deleteSystemAppVersion", versionId);
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
