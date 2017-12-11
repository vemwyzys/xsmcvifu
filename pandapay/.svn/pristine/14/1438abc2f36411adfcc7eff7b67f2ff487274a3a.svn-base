package com.iqmkj.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HTTP请求工具类
 * @author gql
 *
 */
public class HttpUtil {
	
	/**
	 * GET请求
	 * @param url 请求地址
	 * @param param 请求参数的键值，可以为空
	 * @return 请求成功：返回请求结果，请求失败：返回空字符串
	 */
	public String sendGet(String url, Map<String, String> params){
		String result = "";
		try {
			//参数组装
			StringBuffer paramUrl = new StringBuffer();
			if(params != null && params.size() > 0){
				Iterator<Entry<String, String>> iter = params.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
					String key = entry.getKey();
					String value = entry.getValue();
					
					if(paramUrl.length() <= 0){
						paramUrl.append("?" + key + "=" + value);
					}else{
						paramUrl.append("&" + key + "=" + value);
					}
				}
				paramUrl.deleteCharAt(paramUrl.length() - 1);
			}
			
			//请求完整路径
			String urlString = url + paramUrl.toString();
			// 根据地址获取请求
			HttpGet httpGet = new HttpGet(urlString);
			// 获取当前客户端对象
			HttpClient httpClient = new DefaultHttpClient();
			// 通过请求对象获取响应对象
			HttpResponse response = httpClient.execute(httpGet);

			// 判断网络连接状态码是否正常(0--200都属正常)
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result= EntityUtils.toString(response.getEntity(), "utf-8");
			} 
		} catch (Exception e) {
			LogUtil.printErrorLog(e);
		}
		return result;
	}
	
	/**
	 * POST请求
	 * @param url 请求地址
	 * @param param 请求参数的键值，可以为空
	 * @return 请求成功：返回请求结果，请求失败：返回空字符串
	 */
	public String sendPost(String url, Map<String, String> params){
		String result = "";
		try {
			//建立一个NameValuePair数组，用于存储欲传送的参数
			List<NameValuePair> paramList = new ArrayList<NameValuePair>();
			if(params != null && params.size() > 0){
				Iterator<Entry<String, String>> iter = params.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
					String key = entry.getKey();
					String value = entry.getValue();
					
					paramList.add(new BasicNameValuePair(key, value));
				}
			}
			
			// 根据地址获取请求
			HttpPost httpPost=new HttpPost(url);
			//添加参数
			httpPost.setEntity(new UrlEncodedFormEntity(paramList, "utf-8"));
			
			// 获取当前客户端对象
			HttpClient httpClient = new DefaultHttpClient();
			//发送Post，并返回一个HttpResponse对象
			HttpResponse response = httpClient.execute(httpPost);
			
			// 判断网络连接状态码是否正常(0--200都属正常)
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result= EntityUtils.toString(response.getEntity(), "utf-8");
			} 
		} catch (Exception e) {
			LogUtil.printErrorLog(e);
		}
		return result;
	}
	
}
