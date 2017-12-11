package com.pandapay.dao;

import java.sql.Timestamp;
import java.util.List;

import com.pandapay.entity.DO.BackerSessionDO;

/**
 * 后台管理员登录记录
 * @author whx
 *
 */
public interface IBackerSessionDao {

	/**
	 * 新增后台管理员登录记录
	 * @param backerSession 后台管理员登录记录信息
	 * @return 操作成功：返回true，失败：返回false
	 */
	public boolean insertBackerSession(BackerSessionDO backerSession);
	
	/**
	 * 查询后台管理员登录记录信息
	 * @param sessionId sessionId
	 * @return 操作成功：返回后台管理员登录记录信息，失败：返回null
	 */
	public BackerSessionDO queryBackerSessionBySessionId(String sessionId);
	
	/**
	 * 查询后台管理员登录记录总数
	 * @param backerAccount 后台管理员账号，查询全部填null
	 * @param startTime 登录时间的开始时间，查询全部填null
	 * @param endTime 登录时间的结束时间，查询全部填null
	 * @return 操作成功：返回后台管理员登录记录总数，失败：返回0
	 */
	public int queryBackerSesionTotalOfBack(String backerAccount, Timestamp startTime, Timestamp endTime);
	
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
			int pageNumber, int pageSize);
	
}
