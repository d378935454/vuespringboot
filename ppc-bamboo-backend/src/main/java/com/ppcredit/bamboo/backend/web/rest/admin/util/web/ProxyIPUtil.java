package com.ppcredit.bamboo.backend.web.rest.admin.util.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ProxyIPUtil {
	
	
	private static Logger log = LoggerFactory.getLogger(ProxyIPUtil.class);
	
	private static LinkedList<String> ipList = new LinkedList<String>();
	
	
	public static void main(String[] args) {
		
		
		try {
			getMobileinfoIp(new HttpClient());
			System.out.println(ipList.size());
		} catch (HttpException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(true){
			return;
		}
		
		LinkedList<String> ipList = new LinkedList<String>();
		ipList.add("115.119.123.196:34002");
		ipList.add("201.173.56.246:10000");
		ipList.add("59.42.251.215:80");
		ipList.add("190.15.192.120:3128");
		ipList.add("86.57.137.163:3128");
		ipList.add("177.184.8.123:80");
		ipList.add("168.63.24.174:8123");
		ipList.add("36.250.69.4:80");
		ipList.add("219.150.130.158:3128");
		ipList.add("185.64.204.174:34002");
		ipList.add("107.167.111.52:80");
		ipList.add("97.77.104.22:80");
		ipList.add("120.52.72.39:80");
		ipList.add("183.234.48.201:8080");
		ipList.add("180.250.77.220:8080");
		ipList.add("121.31.21.138:8000");
		ipList.add("58.67.159.50:80");
		ipList.add("89.36.220.210:3128");
		ipList.add("60.191.164.83:3128");
		ipList.add("41.169.8.2:8080");
		
		int sucNum = 0;
		
		while(ipList.size() > 0){
			
			String strIp = ipList.pop();
			
			String[] ipAndPort = strIp.split(":");
			String ip = ipAndPort[0];
			String strPort = ipAndPort[1];
			int port =  Integer.parseInt(strPort);
			
			boolean result = false;
			
			String url = "https://passport.jd.com/new/login.aspx";
			
			System.out.println("testting IP >>>> " + strIp);
			
			HttpClient httpClient = new HttpClient();
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
			httpClient.getHostConfiguration().setProxy(ip, port);
			
			try {
				
				GetMethod getMethod = new GetMethod(url);
				getMethod = ClientUtil.getMethod(getMethod, getHost(url));
	
				int resultCode = httpClient.executeMethod(getMethod);
				
				log.info("resultCode >>>>" + resultCode);
				
				if (resultCode == HttpStatus.SC_OK) {
					result = true;
					sucNum ++;
				}
				getMethod.releaseConnection();
			} catch (Exception e) {
				log.info("Timeout For IP >>" +ip+":"+port);
			}
			
			
			System.out.println("result >>>> " + result);
		}
		
		System.out.println("sucNum >>>>>>> " + sucNum);
	}
	
	
	public static Map<String,String> getIP(String url) throws HttpException, IOException{
		
		Map<String,String> ipMap= null;
		
		if(ipList.size() == 0){
			getMobileinfoIp(new HttpClient());
		}
		
		//如果取不到代理IP则不走代理IP
		if(ipList.size() > 0){
		
			String strIp = ipList.pop();
			
			String[] ipAndPort = strIp.split(":");
			String ip = ipAndPort[0];
			String strPort = ipAndPort[1];
			int port =  Integer.parseInt(strPort);
			
			boolean testResult = isRequestPageSuccess(url, ip, port);
			
			log.info("Use Proxy IP >>>> " + strIp + " result >>>> " + testResult);
			
			while(!testResult && ipList.size() > 0){
				strIp = ipList.pop();
				
				ipAndPort = strIp.split(":");
				ip = ipAndPort[0];
				strPort = ipAndPort[1];
				port =  Integer.parseInt(strPort);
				
				log.info("Use Proxy IP >>>> " + strIp + " result >>>> " + testResult);
				
				testResult = isRequestPageSuccess(url, ip, port);
			}
			
			ipMap = new HashMap<String, String>();
			ipMap.put("ip", ip);
			ipMap.put("port", strPort);
		}
		
		return ipMap;
	}
	
	
	/**
	 * 代理云IP,空格分割
	 */
	private static void getDlyIp(HttpClient httpClient) throws HttpException, IOException{
		

		String url = "http://dly.134t.com/query.txt?key=NP18F47E42&word=&count=100";
		
		GetMethod getMethod = new GetMethod(url);
		getMethod = ClientUtil.getMethod(getMethod, "dly.134t.com");
		getMethod.setRequestHeader("User-Agent", "");
		
		int resultCode = httpClient.executeMethod(getMethod);
		if (resultCode == HttpStatus.SC_OK) {
			
			String result = getMethod.getResponseBodyAsString();
			
			log.info("getDlyIp result >>>> " + result);
			
			if(result != null && result.length() > 0){
				
				String[] arrayIp = result.split("\\s+");
				
				for(String str : arrayIp){
					ipList.add(str);
				}
				
			}
		}
		getMethod.releaseConnection();
		
	}
	
	/**
	 * 自定义代理IP
	 */
	private static void getMobileinfoIp(HttpClient httpClient) throws HttpException, IOException{

		String url = "http://mobileinfo.ppdai.com/proxy/count";
		
		GetMethod getMethod = new GetMethod(url);
		getMethod = ClientUtil.getMethod(getMethod, "mobileinfo.ppdai.com");
		getMethod.setRequestHeader("User-Agent", "");
		
		int resultCode = httpClient.executeMethod(getMethod);
		if (resultCode == HttpStatus.SC_OK) {
			
			String result = getMethod.getResponseBodyAsString();
			
			log.info("getMobileinfoIp result >>>> " + result);
			
			if(result != null && result.length() > 10 ){
				
				if(result.indexOf("[") > -1){
					
					result = result.substring(result.indexOf("["));
					JSONArray jsons = JSONObject.parseArray(result);
					for(int i=0; i<jsons.size(); i++){
						JSONObject json = jsons.getJSONObject(i);
						String strIp = json.getString("Ip") + ":" + json.getString("Port");
						ipList.add(strIp);
					}
					
				}
				
			}
		}
		getMethod.releaseConnection();
		
	}
	
	
	private static boolean isRequestPageSuccess(String url, String ip, int port) {

		boolean result = false;
		
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
		httpClient.getHostConfiguration().setProxy(ip, port);
		
		try {
			
			GetMethod getMethod = new GetMethod(url);
			getMethod = ClientUtil.getMethod(getMethod, getHost(url));

			int resultCode = httpClient.executeMethod(getMethod);
			
			log.info("resultCode >>>>" + resultCode);
			
			if (resultCode == HttpStatus.SC_OK) {
				result = true;
			}
			getMethod.releaseConnection();
		} catch (Exception e) {
			log.info("Timeout For IP >>" +ip+":"+port);
		}

		return result;

	}
	
	private static boolean isRequestPageSuccess(String url, String ip, int port, String userName, String password) {

		boolean result = false;
		
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
		httpClient.getHostConfiguration().setProxy(ip, port);

		httpClient.getParams().setAuthenticationPreemptive(true);

		//"zhouguoxuan","zppd2007"
		Credentials upcreds = new UsernamePasswordCredentials(userName, password);
		httpClient.getState().setProxyCredentials(AuthScope.ANY,upcreds);
		
		try {
			
			GetMethod getMethod = new GetMethod(url);
			getMethod = ClientUtil.getMethod(getMethod, getHost(url));

			int resultCode = httpClient.executeMethod(getMethod);
			
			log.info("resultCode >>>>" + resultCode);
			
			if (resultCode == HttpStatus.SC_OK) {
				result = true;
			}
			getMethod.releaseConnection();
		} catch (Exception e) {
			log.info("Timeout For IP >>" +ip+":"+port);
		}

		return result;

	}
	
	
	public static String getHost(String url) {
		String host = url;
		int j = StringUtils.ordinalIndexOf(url, "/", 2);
		int i = StringUtils.ordinalIndexOf(url, "/", 3);
		if (i > 0 && j>0) {
			host = StringUtils.substring(url, j+1, i);
		}
		return host;
	}
	
	
}
