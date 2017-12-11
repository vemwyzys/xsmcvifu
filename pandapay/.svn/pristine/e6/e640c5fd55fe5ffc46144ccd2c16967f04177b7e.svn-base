package com.pandapay.quartz;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iqmkj.utils.DateUtil;
import com.pandapay.entity.DO.UserDO;
import com.pandapay.service.IUserService;

/**
 * 系统任务定时器
 * @author gql
 *
 */
@Component
public class SystemTaskTimer {
	
	/** 用户信息 */
	@Autowired
	private IUserService userService;
	
	/** 将数据未过期但实际已过期的会员VIP变成已过期(每分钟执行一次) */
	@Scheduled(cron = "0 0/1 * * * ? ")
	public void deleteSession(){
		Timestamp curTime = DateUtil.getCurrentTime();
		
		List<UserDO> userList = userService.queryOutVipList(10);
		if(userList != null && userList.size() > 0){
			for (UserDO userDO : userList) {
				userService.updateVipIdentity(userDO.getUserId(), 0, userDO.getVipOutTime(), curTime);
			}
		}
	}
	
}
