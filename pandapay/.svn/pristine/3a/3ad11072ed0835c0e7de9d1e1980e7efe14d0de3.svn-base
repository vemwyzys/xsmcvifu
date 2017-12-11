package com.pandapay.service;

import com.pandapay.entity.BO.JsonObjectBO;

/**
 * 用户短信验证码
 * @author 豆豆
 *
 */
public interface IUserMessageCodeService {

	/**
	 * 新增短信验证码
	 * @param phoneNo 手机号码
	 * @return 操作成功：返回code=1，操作失败：返回code!=1
	 */
	public JsonObjectBO addMessageCode(String phoneNo);
	
	/**
	 * 验证短信验证码
	 * @param phoneNo 手机号码
	 * @param messageCode 验证码
	 * @return 验证成功：返回code=1，验证失败：返回code!=1
	 */
	public JsonObjectBO validateMessageCode(String phoneNo, String messageCode);
	
}
