package com.pandapay.controller.pay;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iqmkj.utils.LogUtil;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.DO.UserOrderPayDO;
import com.pandapay.service.IUserOrderPayService;
import com.pandapay.utils.CodePayUtil;

/**
 * 支付回调
 * @author gql
 *
 */
@Controller
@RequestMapping("/codePay/notify")
@Scope(value="prototype")
public class CodePayNotifyCotroller {
	
	/** 用户支付订单 */
	@Autowired
	private IUserOrderPayService userOrderPayService;
	
	/** 支付回调 */
	@RequestMapping(value = "/notify.htm")
	public @ResponseBody String show(HttpServletRequest request){
		String params = StringUtil.stringNullHandle(request.getParameter("params"));
		String sign = StringUtil.stringNullHandle(request.getParameter("sign"));
		
		LogUtil.printInfoLog("订单结果回调，接口返回参数params：" + params + "，签名sign：" + sign);
		
		//参数错误
		if (!StringUtil.isNotNull(params) || !StringUtil.isNotNull(sign)) {
			return "";
		}
		
		CodePayUtil codePayUtil = new CodePayUtil();
		JsonObjectBO jsonObjectBO = codePayUtil.receiveOrder(params, sign);
		if(jsonObjectBO.getCode() == 1){
			String orderNo = jsonObjectBO.getData().getString("custOrderNo");
			String custId = jsonObjectBO.getData().getString("custId");
			
			if(!StringUtil.isNotNull(orderNo) || !StringUtil.isNotNull(custId)){
				return "";
			}
			
			//处理订单成功和失败的，其他状态不处理
			String orderStatus = codePayUtil.queryOrder(orderNo, custId);
			if(!StringUtil.isNotNull(orderStatus) || !orderStatus.equals("01") || !orderStatus.equals("02")){
				return "";
			}
			
			UserOrderPayDO userOrderPayDO = userOrderPayService.queryOrder(orderNo);
			if(userOrderPayDO == null){
				return "";
			}
			
			//业务执行状态
			boolean executeSuccess = false;
			
			if(orderStatus.equals("01")){
				//交易成功
				if(userOrderPayDO.getServiceType() == 1){
					//商户下单
					executeSuccess = userOrderPayService.orderPaySuccess(userOrderPayDO);
				} else if(userOrderPayDO.getServiceType() == 2){
					//会员充值
					executeSuccess = userOrderPayService.rechargePaySuccess(userOrderPayDO);
				}
			} else if(orderStatus.equals("02")){
				//交易失败
				if(userOrderPayDO.getServiceType() == 1){
					//商户下单
					executeSuccess = userOrderPayService.orderPayFail(userOrderPayDO);
				} else if(userOrderPayDO.getServiceType() == 2){
					//会员充值
					executeSuccess = userOrderPayService.rechargePayFail(userOrderPayDO);
				}
			}
			
			if(executeSuccess){
				return "SC000000";
			}else{
				return "";
			}
		}
		return "";
	}
	
}
