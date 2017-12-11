package com.pandapay.controller.wap;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iqmkj.utils.DateUtil;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.BO.UserSessionBO;
import com.pandapay.entity.DO.UserDO;
import com.pandapay.interceptor.UserWapInterceptor;
import com.pandapay.service.IUserService;

import ch.qos.logback.core.joran.conditional.ElseAction;

/**
 * 购买VIP会员
 * @author xex
 *
 */
@Controller
@RequestMapping("/userWap/wapBuyVip")
@Scope(value="prototype")
public class WapBuyVipCotroller {
	
	/** 用户信息 */
	@Autowired
	private IUserService userService;
	
	/** 显示主页 */
	@RequestMapping(value = "/show.htm")
	public String show(HttpServletRequest request){
		request.setAttribute("code", 0);
		request.setAttribute("message", "查询成功");
		return "page/wap/MyJsp";
	}

	/** 购买会员 */
	@RequestMapping(value = "/buyVip.htm")
	public String buyVip(HttpServletRequest request){
		UserSessionBO userSessionBO = UserWapInterceptor.getUser(request);
		String VipTypeStr = StringUtil.stringNullHandle(request.getParameter("VipType"));
		
		int VipType = 0;
		if(StringUtil.isNotNull(VipTypeStr)){
			VipType = Integer.parseInt(VipTypeStr);
		}
		
		double reduceAmount = 0;
		Timestamp vipOutTime = null;
		if (VipType == 1){//购买vip一个月
			reduceAmount = 50;
			vipOutTime = new Timestamp(System.currentTimeMillis() + 30 * 24 * 60 * 60 *1000);
		} else if (VipType == 2){//购买vip三个月
			reduceAmount = 100;
			vipOutTime = new Timestamp(System.currentTimeMillis() + 90 * 24 * 60 * 60 *1000);
		} else if (VipType == 3){//购买vip一年
			reduceAmount = 360;
			vipOutTime = new Timestamp(System.currentTimeMillis() + 365 * 24 * 60 * 60 *1000);
		}
		
		UserDO user = userService.queryUserByUserId(userSessionBO.getUserId());
		if (user == null){
			request.setAttribute("code", 1);
			request.setAttribute("message", "未查询到用户信息");
			return "page/wap/MyJsp";
		}
		
		userService.reduceWalletAmount(userSessionBO.getUserId(), reduceAmount);
		
		userService.updateVipIdentity(userSessionBO.getUserId(), 1, vipOutTime, DateUtil.getCurrentTime());
		
		//检查账户余额
		//检查当前是否是会员
		//记录当前会员时间
		//修改会员状态，修改会员时间
		//讲道理应该放在服务层，需要新开接口
		
		return "page/wap/MyJsp";
	}
	
}
