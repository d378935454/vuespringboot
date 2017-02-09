/*
 * @(#)HttpRequestUtil.java 2014-3-27下午10:51:18
 * Copyright 2013 sinovatech, Inc. All rights reserved.
 */
package com.ppcredit.bamboo.backend.web.rest.admin.util.web;

import com.ppcredit.bamboo.backend.web.rest.admin.util.ConstantsUtil;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Http请求工具类
 * <ul>
 * <li>
 * <b>修改历史：</b><br/>
 * <p>
 * [2014-3-27下午10:51:18]yang_hx<br/>
 * TODO
 * </p>
 * </li>
 * </ul>
 */

public class HttpRequestUtil {
	/**
	 * 获得请求地址，参数经过URLEncoder处理，避免产生乱码以及转义字符
	 * @author yang_hx
	 * @creationDate. 2012-12-18 上午11:19:27 
	 * @param serverURL 服务URL
	 * @param inParams 请求输入参数集合
	 * @param enc 编码，用于参数URL编码
	 * @return 请求地址
	 * @throws Exception
	 */
	public static String getRequestURL(String serverURL, Map<String, String> inParams, String enc) throws Exception {
		if (StringUtils.isEmpty(serverURL)) return null;
        if (inParams != null && inParams.size() > 0) {
        	StringBuilder params = new StringBuilder();
        	Set<Entry<String, String>> entrys = inParams.entrySet();
			for (Entry<String, String> param : entrys) {
				params.append(param.getKey()).append("=")
						.append((param.getValue() != null) ? URLEncoder.encode(
								param.getValue(), enc) : "").append("&");
			}

            if (params.length() > 0) {
                params = params.deleteCharAt(params.length() - 1);
                serverURL += ((serverURL.indexOf("?") == -1) ? "?" : "&") + params.toString();
            }
        }
        return serverURL;
	}
	/**
	 * get请求
	 * @author yang_hx
	 * @creationDate. 2012-12-18 上午11:22:16 
	 * @param requestURL 请求地址
	 * @param enc 编码，用于设置请求的编码
	 * @return 响应内容
	 * @throws Exception
	 */
	public static String doGet(String requestURL, String enc) throws Exception {
		if (StringUtils.isEmpty(requestURL)) return null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        BufferedReader reader = null;
        
        try {
            URL url = new URL(requestURL);
            
            if (ConstantsUtil.HTTP_PROXY) { // 代理上网
        		java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP,
        				new java.net.InetSocketAddress(ConstantsUtil.HTTP_PROXY_HOSTNAME, ConstantsUtil.HTTP_PROXY_PORT));
        		
        		conn = (HttpURLConnection) url.openConnection(proxy);
            } else {
            	conn = (HttpURLConnection) url.openConnection();
            }
            conn.setRequestMethod("GET");
            
            /*
             * 超时设置
             */
            conn.setConnectTimeout(ConstantsUtil.HTTP_CONNECTTIMEOUT * 1000); // 连接超时
            conn.setReadTimeout(ConstantsUtil.HTTP_READTIMEOUT * 1000); // 读取超时

            inputStream = conn.getInputStream();
            if (inputStream != null) {
				String line = ""; // 行字符串
				StringBuilder result = new StringBuilder();
				reader = new BufferedReader(new InputStreamReader(inputStream, enc));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				return result.toString();
			}
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
        	// 释放资源
        	if (reader != null) reader.close();
        	if (inputStream != null) inputStream.close();
            if (conn != null) conn.disconnect();
        }
        return null;
	}
	/**
	 * https请求
	 * @author yang_hx
	 * @creationDate. 2014-3-27 下午10:51:58 
	 * @param requestURL 请求地址
	 * @param requestMethod 请求方法（POST/GET）
	 * @param outputStr 提交数据字符串
	 * @param encoding 编码
	 * @return 响应字符串
	 * @throws Exception
	 */
	public static String doHttpsRequest(String requestURL,
			String requestMethod, String outputStr, String encoding) throws Exception {
		return HttpClientUtil.doHttpPost(requestURL, outputStr, ConstantsUtil.HTTP_CONNECTTIMEOUT, encoding);
	}
}
