package com.pandapay.controller.wap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iqmkj.utils.StringUtil;
import com.pandapay.service.ISystemBankService;
import com.pandapay.service.IUserAuthenService;

/**
 * 实名认证
 * @author XEX
 *
 */
@Controller
@RequestMapping("/userWap/wapUserAuthen")
@Scope(value="prototype")
public class WapUserAuthenCotroller {
	
	/** 用户认证信息 */
	@Autowired
	private IUserAuthenService userAuthenService;
	
	/** 银行对照表 */
	@Autowired
	private ISystemBankService systemBankService;
	
	/** 显示主页 */
	@RequestMapping(value = "/show.htm")
	public String show(HttpServletRequest request){
		return "page/wap/MyJsp";
	}
	
	/** 实名认证 */
	@RequestMapping(value = "/userAuthen")
	public String userAuthen(HttpServletRequest request, @RequestParam("images") MultipartFile[] images, @RequestParam("data") String requestJsonString){
		String sessionId = StringUtil.stringNullHandle(request.getParameter("sessionId"));
		String storeName = StringUtil.stringNullHandle(request.getParameter("storeName"));
		String contactsName = StringUtil.stringNullHandle(request.getParameter("contactsName"));
		String address = StringUtil.stringNullHandle(request.getParameter("address"));
		String idCard = StringUtil.stringNullHandle(request.getParameter("idCard"));
		String bankCard = StringUtil.stringNullHandle(request.getParameter("bankCard"));
		String bankName = StringUtil.stringNullHandle(request.getParameter("bankName"));
		
    	if(!StringUtil.isNotNull(sessionId) || !StringUtil.isNotNull(storeName)
    			|| !StringUtil.isNotNull(contactsName)
    			|| !StringUtil.isNotNull(address)
    			|| !StringUtil.isNotNull(idCard)
    			|| !StringUtil.isNotNull(bankCard)
    			|| !StringUtil.isNotNull(bankName)){
    		request.setAttribute("code", 2);
    		request.setAttribute("message", "参数错误");
    		return "page/wap/MyJsp";
    	}
    	
    	return "page/wap/MyJsp";
	}
}
