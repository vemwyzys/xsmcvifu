package com.pandapay.utils;

import com.alibaba.fastjson.JSONObject;
import com.iqmkj.utils.LogUtil;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import config.SendMessageConfig;

/**
 * 发送短信
 * @author gql
 *
 */
public class SendMessageUtil {
	
	/**
	 * 发送验证码短信
	 * @param code 验证码
	 * @param phoneNumber 手机号
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public boolean sendCode(String code, String phoneNumber){
		JSONObject contentJson = new JSONObject();
		contentJson.put("code", code);
		contentJson.put("product", "熊猫支付");
		
//		String messageContent = "{\"code\":\"" + code +"\"}";
//		String json="{\"code\":\"1234\",\"product\":\"某某电子商务验证\"}";  
		
		String messageContent = contentJson.toString();
		return sendMessage(messageContent, phoneNumber,
				SendMessageConfig.extend, SendMessageConfig.smsType, SendMessageConfig.smsFreeSignName, SendMessageConfig.smsTemplateCode);
	}
	
	/**
	 * 调用短信发送接口
	 * @param messageContent 短信内容
	 * @param phoneNumberList 发送号码，多个号码之间用英文逗号分隔，一次最多发送200个号码
	 * @param extend 扩展参数
	 * @param smsType 短信类型
	 * @param smsFreeSignName 短信签名
	 * @param smsTemplateCode 短信模版编码
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	private boolean sendMessage(String messageContent, String phoneNumberList,
			String extend, String smsType, String smsFreeSignName, String smsTemplateCode){
		TaobaoClient client = new DefaultTaobaoClient(SendMessageConfig.url, SendMessageConfig.appkey, SendMessageConfig.secret);
		
		//短信参数
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(extend);
		req.setSmsType(smsType);
		req.setSmsFreeSignName(smsFreeSignName);
		req.setSmsTemplateCode(smsTemplateCode);
		req.setSmsParamString(messageContent);
		req.setRecNum(phoneNumberList);
		
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			String resultStr = rsp.getBody();
			JSONObject resultJson = JSONObject.parseObject(resultStr);
			
			if(resultJson.containsKey("alibaba_aliqin_fc_sms_num_send_response")){
				//发送成功
				JSONObject responseJson = resultJson.getJSONObject("alibaba_aliqin_fc_sms_num_send_response");
				JSONObject result = responseJson.getJSONObject("result");
				boolean success = result.getBooleanValue("success");
				
				if(success){
					LogUtil.printInfoLog("短信发送成功");
					return true;
				}else{
					LogUtil.printInfoLog("短信发送失败");
					return false;
				}
			}else if(resultJson.containsKey("error_response")){
				//发送失败
				JSONObject responseJson = resultJson.getJSONObject("error_response");
				int code = responseJson.getIntValue("code");
				String msg = responseJson.getString("sub_msg");
				
				LogUtil.printInfoLog("短信发送失败，失败编码：" + code + "，失败原因：" + msg);
				return false;
			}else{
				LogUtil.printInfoLog("短信发送失败，失败原因：接口返回参数解析错误。接口参数：" + resultJson.toString());
				return false;
			}
		} catch (Exception e) {
			LogUtil.printErrorLog(e);
			return false;
		}
	}
	
}
