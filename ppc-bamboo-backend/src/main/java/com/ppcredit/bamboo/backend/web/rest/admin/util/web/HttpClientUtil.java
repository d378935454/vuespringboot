/*
 * @(#)HttpClientUtil.java 2014-6-10下午12:51:48
 * Copyright 2013 sinovatech, Inc. All rights reserved.
 */
package com.ppcredit.bamboo.backend.web.rest.admin.util.web;

import com.ppcredit.bamboo.backend.web.rest.admin.util.ConstantsUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.methods.MultipartPostMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 
 * <ul>
 * <li>
 * <b>修改历史：</b><br/>
 * <p>
 * [2014-6-10下午12:51:48]tuxiaolian<br/>
 * TODO
 * </p>
 * </li>
 * </ul>
 */

@SuppressWarnings("deprecation")
public class HttpClientUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * 
	 * @author 涂小炼
	 * @creationDate. 2014-6-13 下午4:59:33 
	 * @param reqUrl  请求URL
	 * @param outputStr  post 内容  get方法传null
	 * @param outTime   超时时间  单位：秒
	 * @param charsets  字符集
	 * @return    返回响应结果
	 * @throws Exception
	 */
	public static String doHttpPost(String reqUrl, String outputStr, int outTime,String charsets) throws Exception {
		HttpClient httpClient = new HttpClient();

		if (ConstantsUtil.HTTP_PROXY) { // 代理上网
			// 设置代理服务器的ip地址和端口
			httpClient.getHostConfiguration().setProxy(ConstantsUtil.HTTP_PROXY_HOSTNAME, ConstantsUtil.HTTP_PROXY_PORT);
		}
		httpClient.setConnectionTimeout(new Integer(outTime * 1000));
		httpClient.getParams().setParameter("http.protocol.version",HttpVersion.HTTP_1_1);
		httpClient.getParams().setParameter("http.socket.timeout",new Integer(outTime * 1000));
		httpClient.getParams().setParameter("http.protocol.content-charset",charsets);

		PostMethod postMethod = new PostMethod(reqUrl);
		postMethod.setRequestBody(outputStr);

		try {
			httpClient.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				return postMethod.getResponseBodyAsString();
			} else {
				throw new Exception("Unexpected failure: "+ postMethod.getStatusLine().toString());
			}
		} catch (Exception e) {
			logger.error("Http协议post方法发送字符流时候出现异常：", e);
			throw new Exception("Http协议post方法发送字符流时候出现异常：", e);
		} finally {
			postMethod.releaseConnection();
			httpClient.getHttpConnectionManager().closeIdleConnections(0);
		}
	}
	
	/**
	 * 上传图片  
	 * @author 涂小炼
	 * @creationDate. 2014-6-25 下午5:48:26 
	 * @param reqUrl   上传URL
	 * @param file   需要上传的文件
	 * @param outTime
	 * @return
	 * @throws Exception
	 */
	public static String upLoadFile(String reqUrl,File file, int outTime) throws Exception {
		logger.info("reqUrl===" + reqUrl + "file.path====" + file.getAbsolutePath());
		HttpClient httpClient = new HttpClient();

		if (ConstantsUtil.HTTP_PROXY) { // 代理上网
			// 设置代理服务器的ip地址和端口
			httpClient.getHostConfiguration().setProxy(ConstantsUtil.HTTP_PROXY_HOSTNAME, ConstantsUtil.HTTP_PROXY_PORT);
		}
		httpClient.setConnectionTimeout(new Integer(outTime * 1000));
		httpClient.getParams().setParameter("http.protocol.version",HttpVersion.HTTP_1_1);
		httpClient.getParams().setParameter("http.socket.timeout",new Integer(outTime * 1000));
		
		MultipartPostMethod postMethod = new MultipartPostMethod(reqUrl);
		FilePart cbFile = new FilePart("media", file);
		postMethod.addPart(cbFile);
		try {
			httpClient.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				return postMethod.getResponseBodyAsString();
			} else {
				throw new Exception("Unexpected failure: "+ postMethod.getStatusLine().toString());
			}
		} catch (Exception e) {
			logger.error("Http协议post方法发送字符流时候出现异常：", e);
			throw new Exception("Http协议post方法发送字符流时候出现异常：", e);
		} finally {
			postMethod.releaseConnection();
			httpClient.getHttpConnectionManager().closeIdleConnections(0);
		}
	}
	
}
