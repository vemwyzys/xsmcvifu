package com.pandapay.quartz;

import java.sql.Timestamp;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.FileWriteLocalUtil;
import com.pandapay.interceptor.BackerWebInterceptor;
import com.pandapay.interceptor.UserWapInterceptor;

/**
 * 本机任务定时器
 * @author gql
 *
 */
@Component
public class LocalTaskTimer {
	
	/** 删除缓存中失效session(每5分钟执行一次) */
	@Scheduled(cron = "0 0/5 * * * ? ")
	public void deleteSession(){
		BackerWebInterceptor.deleteSession();
		UserWapInterceptor.deleteSession();
	}
	
	/** 删除本地无效文件 */
	@Scheduled(cron = "0 0 0/2 * * ? ")
	public void deleteOutTimeFile(){
		long curTime = DateUtil.getCurrentTimeMillis();
		long outTime = curTime - 2*60*60*1000;  //保留2小时
		
		/*List<String> ignoreDirList = new ArrayList<String>();
		ignoreDirList.add("170423");*/
		
		FileWriteLocalUtil.deleteOutTimeFile(new Timestamp(outTime), null);
	}
	
}
