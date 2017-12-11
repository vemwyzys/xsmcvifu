package com.pandapay.controller.wap;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.iqmkj.utils.StringUtil;
import com.pandapay.entity.BO.JsonObjectBO;
import com.pandapay.entity.BO.UserSessionBO;
import com.pandapay.entity.DO.UserRecordAmountDO;
import com.pandapay.interceptor.UserWapInterceptor;
import com.pandapay.service.IUserRecordAmountService;

/**
 * 账户记录
 * @author XEX
 *
 */
@Controller
@RequestMapping("/userWap/wapAccountRecord")
@Scope(value="prototype")
public class WapAccountRecordCotroller {
	
	/** 用户账户金额记录 */
	@Autowired
	private IUserRecordAmountService userRecordAmountService;
	
	/** 显示主页 */
	@RequestMapping(value = "/show.htm")
	public String show(HttpServletRequest request){
		UserSessionBO userSessionBO = UserWapInterceptor.getUser(request);
		String pageNumberStr = StringUtil.stringNullHandle(request.getParameter("pageNumber"));
		
		int pageNumber = 0;
		if(StringUtil.isNotNull(pageNumberStr)){
			pageNumber = Integer.parseInt(pageNumberStr);
		}
		
		int pageSize = 20;
        int totalNumber = userRecordAmountService.queryRecordTotalOfUser(userSessionBO.getUserId());
        //总页码数
        int totalPageNumber = (int) (totalNumber/1.0 /pageSize);
        //如果总数刚好是分页的倍数值，需要减1
        if(totalPageNumber > 0 && Math.ceil(totalNumber/1.0/pageSize) == totalPageNumber){
            totalPageNumber --;
        }
        
        List<UserRecordAmountDO> userRecordAmountList = null;
        if(totalNumber > 0){
        	userRecordAmountList = userRecordAmountService.queryRecordListOfUser(userSessionBO.getUserId(), totalPageNumber, pageSize);
        }
        
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("totalNumber", totalNumber);
        request.setAttribute("totalPageNumber", totalPageNumber);
        
        request.setAttribute("userRecordAmountList", userRecordAmountList);
        
        request.setAttribute("code", 0);
		request.setAttribute("message", "查询成功");
        
		return "page/wap/MyJsp";
	}
	
	/** 分页查询  */
	@RequestMapping(value = "/list.htm")
	public @ResponseBody JsonObjectBO list(HttpServletRequest request, @RequestBody String requestJsonString){
		JsonObjectBO responseJson = new JsonObjectBO();
		
		JSONObject requestJson = null;
		try {
			requestJson = JSONObject.parseObject(requestJsonString);
		} catch (Exception e) {
			responseJson.setCode(-1);
        	responseJson.setMessage("JSON格式错误");
			return responseJson;
		}
		
		if (requestJson == null){
			responseJson.setCode(-1);
        	responseJson.setMessage("JSON格式错误");
			return responseJson;
		}
		
		UserSessionBO userSessionBO = UserWapInterceptor.getUser(request);
		String pageNumberStr = StringUtil.stringNullHandle(requestJson.getString("pageNumber"));
		
		int pageNumber = 0;
		if(StringUtil.isNotNull(pageNumberStr)){
			pageNumber = Integer.parseInt(pageNumberStr);
		}
		
		int pageSize = 20;
        int totalNumber = userRecordAmountService.queryRecordTotalOfUser(userSessionBO.getUserId());
        //总页码数
        int totalPageNumber = (int) (totalNumber/1.0 /pageSize);
        //如果总数刚好是分页的倍数值，需要减1
        if(totalPageNumber > 0 && Math.ceil(totalNumber/1.0/pageSize) == totalPageNumber){
            totalPageNumber --;
        }
        
        List<UserRecordAmountDO> userRecordAmountList = null;
        if(totalNumber > 0){
        	userRecordAmountList = userRecordAmountService.queryRecordListOfUser(userSessionBO.getUserId(), totalPageNumber, pageSize);
        }
        
        JSONObject resultJSON = new JSONObject();
        resultJSON.put("pageNumber", pageNumber);
        resultJSON.put("totalNumber", totalNumber);
        resultJSON.put("totalPageNumber", totalPageNumber);
        
        resultJSON.put("userRecordAmountList", userRecordAmountList);
		
        responseJson.setData(resultJSON);
		return responseJson;
	}
	
}
