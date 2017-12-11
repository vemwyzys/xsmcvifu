package com.pandapay.controller.wap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iqmkj.utils.MD5Util;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.DO.UserDO;
import com.pandapay.service.IUserMessageCodeService;
import com.pandapay.service.IUserService;

/**
 * 忘记密码
 * @author 豆豆
 *
 */
@Controller
@RequestMapping("/userWap/Forgetpassword")
@Scope(value="prototype")
public class WapForgetPasswordController {

	/**用户信息*/
	@Autowired
	private IUserService userService;
	
	/**用户短信验证码*/
	@Autowired
	private IUserMessageCodeService userMessageCodeService;
	
	/**打开忘记密码页面*/
	@RequestMapping("/show")
	public String show(HttpServletRequest request){
		return "page/wap/wapForget";
	}
	
	/**找回密码*/
	@RequestMapping(value="/getPassword", method=RequestMethod.POST)
	public String getPassword(HttpServletRequest request){
		String userAccount = StringUtil.stringNullHandle(request.getParameter("phone"));
		String vertifyCode = StringUtil.stringNullHandle(request.getParameter("vertifyCode"));
		String password = StringUtil.stringNullHandle(request.getParameter("password"));
		
		if (!StringUtil.isNotNull(userAccount) || !StringUtil.isNotNull(vertifyCode) || !StringUtil.isNotNull(password)) {
			request.setAttribute("code", -1);
			request.setAttribute("message", "参数错误");
			return "page/wap/wapForget";
		}
		
		password = MD5Util.toMd5(password);
		//验证用户是否存在
		boolean resultBoo = userService.validataUserAccount(userAccount);
		if (!resultBoo) {
			request.setAttribute("code", -1);
			request.setAttribute("message", "该用户不存在！");
			
			request.setAttribute("phone", userAccount);
			request.setAttribute("vertifyCode", vertifyCode);
			return "page/wap/wapForget";
		}
		//验证验证码
		JsonObjectBO codeJson = userMessageCodeService.validateMessageCode(userAccount, vertifyCode);
		if (codeJson.getCode() != 1) {
			request.setAttribute("code", -1);
			request.setAttribute("message", codeJson.getMessage());
			
			request.setAttribute("phone", userAccount);
			return "page/wap/wapForget";
		}
		
		UserDO user = userService.queryUserByUserAccount(userAccount);
		if (user == null) {
			request.setAttribute("code", -1);
			request.setAttribute("message", "该用户不存在！");
			
			request.setAttribute("phone", userAccount);
			request.setAttribute("vertifyCode", vertifyCode);
			return "page/wap/wapForget";
		}
		
		//修改密码
		boolean result = userService.updateUserPasswordModify(user.getUserId(), password);
		if (!result) {
			request.setAttribute("code", -1);
			request.setAttribute("message", "修改失败！");
			
			request.setAttribute("phone", userAccount);
			request.setAttribute("vertifyCode", vertifyCode);
			return "page/wap/wapForget";
		}
		
		request.setAttribute("code", -1);
		request.setAttribute("message", "修改成功！");
		return "page/wap/wapLogin";
	}
	
	/**发送验证码*/
	@RequestMapping(value="/sendCode", method=RequestMethod.POST)
	public @ResponseBody JsonObjectBO verifyCode(HttpServletRequest request){
		JsonObjectBO responseJson = new JsonObjectBO();
		String phone = StringUtil.stringNullHandle(request.getParameter("phone"));
		
		if (!StringUtil.isNotNull(phone)) {
			request.setAttribute("code", -1);
			request.setAttribute("message", "请填写正确的手机号！");
			return responseJson;
		}
		
		//发送验证码
		JsonObjectBO codeJson = userMessageCodeService.addMessageCode(phone);
		if (codeJson.getCode() != 1) {
			request.setAttribute("code", -1);
			request.setAttribute("message", codeJson.getMessage());
			return responseJson;
		}
		
		responseJson.setCode(codeJson.getCode());
		responseJson.setMessage(codeJson.getMessage());
		
		return responseJson;
	}
	
}
