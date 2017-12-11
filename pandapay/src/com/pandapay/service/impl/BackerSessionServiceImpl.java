package com.pandapay.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandapay.dao.IBackerSessionDao;
import com.pandapay.entity.DO.BackerSessionDO;
import com.pandapay.service.IBackerSessionService;

/**
 * 后台管理员登录记录
 * @author whx
 *
 */
@Service("backerSessionService")
public class BackerSessionServiceImpl implements IBackerSessionService{

	/***/
	@Autowired
	private IBackerSessionDao backerSessionDao;
	
	/**
	 * 新增后台管理员登录记录
	 * @param backerSession 后台管理员登录记录信息
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean insertBackerSession(BackerSessionDO backerSession){
		return backerSessionDao.insertBackerSession(backerSession);
	}
	
	/**
	 * 查询后台管理员登录记录信息
	 * @param sessionId sessionId
	 * @return 操作成功：返回后台管理员登录记录信息，失败：返回null
	 */
	public BackerSessionDO queryBackerSessionBySessionId(String sessionId){
		return backerSessionDao.queryBackerSessionBySessionId(sessionId);
	}
	
	/**
	 * 查询后台管理员登录记录总数
	 * @param backerAccount 后台管理员账号，查询全部填null
	 * @param startTime 登录时间的开始时间，查询全部填null
	 * @param endTime 登录时间的结束时间，查询全部填null
	 * @return 操作成功：返回后台管理员登录记录总数，失败：返回0
	 */
	public int queryBackerSesionTotalOfBack(String backerAccount, Timestamp startTime, Timestamp endTime){
		return backerSessionDao.queryBackerSesionTotalOfBack(backerAccount, startTime, endTime);
	}
	
	/**
	 * 查询后台管理员登录记录列表
	 * @param backerAccount 后台管理员账号，查询全部填null
	 * @param startTime 登录时间的开始时间，查询全部填null
	 * @param endTime 登录时间的结束时间，查询全部填null
	 * @param pageNumber 当前页码
	 * @param pageSize 每页大小
	 * @return 操作成功：返回后台管理员登录记录列表，失败：返回null
	 */
	public List<BackerSessionDO> queryBackerSessionListOfBack(String backerAccount, Timestamp startTime, Timestamp endTime,
			int pageNumber, int pageSize){
		return backerSessionDao.queryBackerSessionListOfBack(backerAccount, startTime, endTime, pageNumber, pageSize);
	}
	
}
