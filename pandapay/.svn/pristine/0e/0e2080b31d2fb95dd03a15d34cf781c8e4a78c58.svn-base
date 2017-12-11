package com.pandapay.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iqmkj.utils.HttpUtil;
import com.iqmkj.utils.LogUtil;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.BO.JsonObjectBO;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
import config.PayConfig_code;

/**
 * 扫码支付
 * @author gql
 *
 */
public class CodePayUtil {
	
	/**
	 * 商户注册/修改接口
	 * 商户信息注册同步接口，以手机号为主(手机号必须在平台系统中唯一)，第一次提交会自动注册，后面的提交会自动修改除手机号以外的信息
	 * @param phone 商户手机号
	 * @param name 商户姓名，商户身份证上姓名
	 * @param idCardNo 身份证号，注册者身份证号码
	 * @param bankCardNo 银行卡号，商户身份证同名银行卡号
	 * @param bankNo 联行号，银行编号
	 * @param payRate 商户交易手续费，如：0.4
	 * @return 操作成功：返回支付平台返回的商户编号(custId)，操作失败：返回null
	 */
	public String businessRegister(String phone, String name, String idCardNo, String bankCardNo, String bankNo, double payRate){
		try {
			JSONArray rateArray = new JSONArray();
			JSONObject weixinRate = new JSONObject();
			weixinRate.put("rateType", "7");
			weixinRate.put("rate", payRate + "");
			rateArray.add(weixinRate);
			JSONObject alipayRate = new JSONObject();
			alipayRate.put("rateType", "8");
			alipayRate.put("rate", payRate + "");
			rateArray.add(alipayRate);
			
			//商户信息
			JSONObject businessData = new JSONObject();
			businessData.put("orgNo", PayConfig_code.orgNo);  //机构号，平台下发的机构编号
			businessData.put("custMobile", phone);  //商户手机号，必须是未在该平台登记的手机号
			businessData.put("custName", name);  //商户姓名，商户身份证上姓名
			businessData.put("certificateNo", idCardNo);  //身份证号，注册者身份证号码
			businessData.put("cardNo", bankCardNo);  //银行卡号，商户身份证同名银行卡号
			businessData.put("cnapsCode", bankNo);  //联行号，银行编号
			businessData.put("rateArray", rateArray);  //商户交易手续费配置信息
			
			BASE64Encoder encoder = new BASE64Encoder();
			String encoderStr = encoder.encode(businessData.toString().getBytes());
			
			//参数加密签名
			String secretStr = encoderStr + PayConfig_code.secret_private;
			String signature = DigestUtils.md5DigestAsHex(secretStr.getBytes());
			
			//组装请求参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("params", encoderStr);
			map.put("sign", signature);
			
			//请求
			HttpUtil httpUtil = new HttpUtil();
			String httpContent = httpUtil.sendPost(PayConfig_code.url_businessRegister, map);
			
			//获取请求返回数据
			JSONObject resultJson = JSONObject.parseObject(httpContent);
			String resultParams = resultJson.getString("params");
			
			//返回数据解码
			BASE64Decoder decoder = new BASE64Decoder();
			String jsonStr = new String(decoder.decodeBuffer(resultParams));
			JSONObject json = JSONObject.parseObject(jsonStr);
			
			//必有参数
//			String code = json.getString("code");  //返回码，返回000000表示请求成功
//			String msg = json.getString("msg");  //消息说明
//			String orgNo = json.getString("orgNo");  //平台下发的机构编号
			//非必有参数
//			String custId = json.getString("custId");  //平台提供的商户编号，注册成功时返回
			
			String code = json.getString("code");
			if(code.equals("000000") && json.containsKey("custId")){
				String orgNo = json.getString("orgNo");
				if(orgNo.equals(PayConfig_code.orgNo)){
					String custId = json.getString("custId");
					if(StringUtil.isNotNull(custId)){
						return custId;
					}
				}
			}
			
			LogUtil.printInfoLog("同步支付商户信息失败，接口返回数据：" + json.toString());
		} catch (Exception e) {
			LogUtil.printErrorLog(e);
		}
		return null;
	}
	
	/**
	 * 创建支付订单
	 * @param orderNo 订单号
	 * @param custId 平台返回的的商户编号
	 * @param payAmount 支付金额，单位：分
	 * @param backUrl 后台回调地址，支付完成时回调接收地址
	 * @param payType 支付类型，04：微信支付，05：支付宝
	 * @param goodsName 商品名称
	 * @return 操作成功：返回支付信息（需根据此信息生成支付二维码），操作失败：返回null
	 */
	public String createOrder(String orderNo, String custId, long payAmount, String backUrl, String payType, String goodsName){
		try {
			//订单数据
			JSONObject orderData = new JSONObject();
			orderData.put("orgNo", PayConfig_code.orgNo);  //机构号，平台下发的机构编号
			orderData.put("custId", custId);  //平台返回的的商户编号
			orderData.put("custOrderNo", orderNo);  //商户订单编号，客户方生成的订单编号,不能重复
			orderData.put("payAmt", payAmount);  //支付金额，单位：分
			orderData.put("backUrl", backUrl);  //后台回调地址，支付完成时回调客户方结果接收地址
//			orderData.put("frontUrl", frontUrl);  //前台回调地址
			orderData.put("payType", payType);  //支付类型，04：微信支付，05：支付宝
			orderData.put("fn", "create_wap");  //功能编码（扫码支付），由平台方提供的功能编码
			orderData.put("goodsName", goodsName);  //商品名称
			orderData.put("orderDesc", goodsName);  //交易订单描述
			
			BASE64Encoder encoder = new BASE64Encoder();
			String encoderStr = encoder.encode(orderData.toString().getBytes());
			
			//参数加密签名
			String secretStr = encoderStr + PayConfig_code.secret_private;
			String signature = DigestUtils.md5DigestAsHex(secretStr.getBytes());
			
			//组装请求参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("params", encoderStr);
			map.put("sign", signature);
			
			//请求
			HttpUtil httpUtil = new HttpUtil();
			String httpContent = httpUtil.sendPost(PayConfig_code.url_createOrder, map);
			
			//获取请求返回数据
			JSONObject resultJson = JSONObject.parseObject(httpContent);
			String resultParams = resultJson.getString("params");
			
			//返回数据解码
			BASE64Decoder decoder = new BASE64Decoder();
			String jsonStr = new String(decoder.decodeBuffer(resultParams));
			JSONObject json = JSONObject.parseObject(jsonStr);
			
			//必有参数
//			String code = json.getString("code");  //返回码，返回000000表示请求成功
//			String msg = json.getString("msg");  //消息说明
//			String orgNo = json.getString("orgNo");  //平台下发的机构编号
//			String custId = json.getString("custId");  //平台提供的商户编号
//			String custOrderNo = json.getString("custOrderNo");  //商户订单编号，是客户订单编号，原样返回
			
			//非必有参数
//			String prdOrdNo = json.getString("prdOrdNo");  //平台订单号，平台对应该笔订单的订单号，可作为查询依据，订单处理成功时返回
//			String chl = json.getString("chl");  //渠道编号，订单处理成功时返回，用来辨别业务内容解析格式(根据后台所选路由渠道的不同,可能存在业务数据格式差异,请根据平台要求处理)
//			String busContent = json.getString("busContent");  //业务内容，订单处理成功时返回的业务内容.chl=MS且fn=create_app时返回业务订单号,供app sdk调用;chl=MS且fn=create_wap返回支付表单内容文本
//			String orderStatus = json.getString("orderStatus");  //订单状态，订单操作成功时返回. 00:未交易 01:成功 02:失败 03:可疑 04:处理中 05:已取消 06:未支付 07:已退货 08:退货中 09:部分退货
//			String orderDes = json.getString("orderDes");  //状态描述，订单状态存在时,描述状态代表的含义
			
			String code = json.getString("code");
			if(code.equals("000000") && json.containsKey("busContent")){
				String orgNo = json.getString("orgNo");
				if(orgNo.equals(PayConfig_code.orgNo)){
					String busContent = json.getString("busContent");
					if(StringUtil.isNotNull(busContent)){
						return busContent;
					}
				}
			}
			
			LogUtil.printInfoLog("创建支付订单失败，接口返回数据：" + json.toString());
		} catch (Exception e) {
			LogUtil.printErrorLog(e);
		}
		return null;
	}
	
	/**
	 * 订单结果回调解析
	 * @param params 回调接收的参数
	 * @param sign 回调参数的加密签名
	 * @return 操作成功：返回业务数据(code=1)，操作失败：返回(code!=1)
	 */
	public JsonObjectBO receiveOrder(String params, String sign) {
		JsonObjectBO resultJson = new JsonObjectBO();
		
		try {
			//参数解码验证
			String secretStr = params + PayConfig_code.secret_private;
			String signature = DigestUtils.md5DigestAsHex(secretStr.getBytes());
			
			if (!signature.equalsIgnoreCase(sign)) {
				LogUtil.printInfoLog("订单结果回调签名验证错误，接口返回参数：" + params + "，签名：" + sign);
				
				resultJson.setCode(2);
				resultJson.setMessage("签名验证失败");
				return resultJson;
			}
			
			//返回数据解码
			BASE64Decoder decoder = new BASE64Decoder();
			String jsonStr = new String(decoder.decodeBuffer(params));
			JSONObject json = JSONObject.parseObject(jsonStr);

			//必有参数
//			String orgNo = json.getString("orgNo");  //平台下发的机构编号
//			String custId = json.getString("custId");  //平台提供的商户编号
//			String custOrderNo = json.getString("custOrderNo");  //商户订单编号，是客户订单编号
//			String prdOrdNo = json.getString("prdOrdNo");  //平台订单号
//			String ordAmt = json.getString("ordAmt");  //订单金额，交易订单金额，单位：分
//			String ordTime = json.getString("ordTime");  //订单时间	，格式：yyyyMMddHHmmss
//			return successMsg("SC000000");
			
			String orgNo = json.getString("orgNo");
			if(orgNo.equals(PayConfig_code.orgNo)){
				LogUtil.printInfoLog("订单结果回调成功，接口返回参数：" + json.toString());
				
				resultJson.setCode(1);
				resultJson.setMessage("签名验证成功");
				resultJson.setData(json);
				return resultJson;
			}else{
				LogUtil.printInfoLog("订单结果回调机构验证错误，接口返回参数：" + params + "，签名：" + sign);
				
				resultJson.setCode(2);
				resultJson.setMessage("机构验证失败");
				return resultJson;
			}
		} catch (Exception e) {
			LogUtil.printErrorLog(e);
			
			resultJson.setCode(2);
			resultJson.setMessage("数据解析异常");
			return resultJson;
		}
	}
	
	/**
	 * 订单查询
	 * @param orderNo 订单号
	 * @param custId 平台返回的的商户编号
	 * @return 操作成功：返回订单状态，
	 * 订单操作成功时返回（00:未交易,01:成功,02:失败,03:可疑,04:处理中,05:已取消,06:未支付,07:已退货,08:退货中,09:部分退货）；
	 * 操作失败：返回null
	 */
	public String queryOrder(String orderNo, String custId){
		try {
			//订单数据
			JSONObject queryData = new JSONObject();
			queryData.put("orgNo", PayConfig_code.orgNo);  //机构号，平台下发的机构编号
			queryData.put("custOrderNo", orderNo);  //商户订单编号，客户方生成的订单编号,不能重复
			queryData.put("custId", custId);  //平台返回的的商户编号
			
			BASE64Encoder encoder = new BASE64Encoder();
			String encoderStr = encoder.encode(queryData.toString().getBytes());
			
			//参数加密签名
			String secretStr = encoderStr + PayConfig_code.secret_private;
			String signature = DigestUtils.md5DigestAsHex(secretStr.getBytes());
			
			//组装请求参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("params", encoderStr);
			map.put("sign", signature);
			
			//请求
			HttpUtil httpUtil = new HttpUtil();
			String httpContent = httpUtil.sendPost(PayConfig_code.url_queryOrder, map);
			
			//获取请求返回数据
			JSONObject resultJson = JSONObject.parseObject(httpContent);
			String resultParams = resultJson.getString("params");
			
			//返回数据解码
			BASE64Decoder decoder = new BASE64Decoder();
			String jsonStr = new String(decoder.decodeBuffer(resultParams));
			JSONObject json = JSONObject.parseObject(jsonStr);
			
			//必有参数
//			String code = json.getString("code");  //返回码，返回000000表示请求成功
//			String msg = json.getString("msg");  //消息说明
//			String orgNo = json.getString("orgNo");  //平台下发的机构编号
//			String custId = json.getString("custId");  //平台提供的商户编号
//			String custOrderNo = json.getString("custOrderNo");  //商户订单编号，是客户订单编号，原样返回
			
			//非必有参数
//			String prdOrdNo = json.getString("prdOrdNo");  //平台订单号，平台对应该笔订单的订单号，可作为查询依据，订单处理成功时返回
//			String orderStatus = json.getString("orderStatus");  //订单状态，订单操作成功时返回. 00:未交易 01:成功 02:失败 03:可疑 04:处理中 05:已取消 06:未支付 07:已退货 08:退货中 09:部分退货
//			String orderDes = json.getString("orderDes");  //状态描述，订单状态存在时,描述状态代表的含义
			
			String code = json.getString("code");
			if(code.equals("000000") && json.containsKey("orderStatus")){
				String orgNo = json.getString("orgNo");
				if(orgNo.equals(PayConfig_code.orgNo)){
					String orderStatus = json.getString("orderStatus");
					if(StringUtil.isNotNull(orderStatus)){
						return orderStatus;
					}
				}
			}
			
			LogUtil.printInfoLog("查询支付订单失败，接口返回数据：" + json.toString());
		} catch (Exception e) {
			LogUtil.printErrorLog(e);
		}
		return null;
	}
	
}
