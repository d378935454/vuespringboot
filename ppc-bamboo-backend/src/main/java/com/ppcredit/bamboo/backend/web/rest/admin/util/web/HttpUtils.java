package com.ppcredit.bamboo.backend.web.rest.admin.util.web;

import com.ppcredit.bamboo.backend.web.rest.admin.util.date.DateUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.util.security.Base64;
import com.ppcredit.bamboo.backend.web.rest.admin.util.RandomUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.util.security.Sha;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @ClassName: HttpUtils
 * @Description: HTTP通讯组件
 * @author yusonghai
 * @date 2015-1-30 下午04:54:46
 * 
 */
public class HttpUtils {

	private static Log log = LogFactory.getLog(HttpUtils.class);

	/**
	 * 客户端用Post方法向服务器提交请求,并获取服务器响应信息
	 * 
	 */
	public static String reqForPost(String postURL, String context, String enc) throws Exception{
		try {
			URL url = new URL(postURL);
			URLConnection urlConn = url.openConnection();
			HttpURLConnection httpUrlConn = (HttpURLConnection) urlConn;
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false;
			httpUrlConn.setDoOutput(true);
			// 设置是否从httpUrlConnection读入，默认情况下是true
			httpUrlConn.setDoInput(true);
			// Post 请求不能使用缓存
			httpUrlConn.setUseCaches(false);
			// 设定请求的方法为"POST"，默认是GET
			httpUrlConn.setRequestMethod("POST");
			// 设定请求延迟时间
			httpUrlConn.setConnectTimeout(100000);
			// 设置从主机读取数据超时
			httpUrlConn.setReadTimeout(100000);
			OutputStreamWriter wr = new OutputStreamWriter(
					httpUrlConn.getOutputStream());
			if(context != null){
				wr.write(context);
				wr.flush();
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpUrlConn.getInputStream(),enc));
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			wr.close();
			in.close();
			httpUrlConn.disconnect();
			return StringUtils.trimToEmpty(sb.toString());
		} catch (Exception e) {
			log.error("连接出错:" + postURL + ",失败原因:" + e.getMessage());
			throw e;
		}
	}

	/**
	 * 客户端用Get方法向服务器提交请求,并获取服务器响应信息
	 * @param getURL
	 * @param env
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public static String reqForGet(String getURL,String env,int timeout) throws Exception {
		try {
			URL url = new URL(getURL);
			URLConnection urlConn = url.openConnection();
			HttpURLConnection httpUrlConn = (HttpURLConnection) urlConn;
			// 默认情况下是false;
			httpUrlConn.setDoOutput(false);
			// 设置是否从httpUrlConnection读入，默认情况下是true
			httpUrlConn.setDoInput(true);
			// Get 请求不能使用缓存
			httpUrlConn.setUseCaches(false);
			// 设定请求的方法为"GET"，默认是GET
			httpUrlConn.setRequestMethod("GET");
			// 设定请求延迟时间
			httpUrlConn.setConnectTimeout(timeout);
			// 设置从主机读取数据超时
			httpUrlConn.setReadTimeout(timeout);
			BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(),env));

			int resCode = httpUrlConn.getResponseCode();
			String resMsg = httpUrlConn.getResponseMessage();

			log.info("通知请求resCode==>" + resCode + "|resMsg==>" + resMsg);
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
			httpUrlConn.disconnect();
			return StringUtils.trimToEmpty(sb.toString());
		} catch (Exception e) {
			log.error("连接出错:" + getURL + ",失败原因:" + e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 客户端用Get方法向服务器提交请求,并获取服务器响应信息
	 * 
	 * @throws Exception
	 */
	public static String reqForGet(String getURL,String env) throws Exception {
		try {
			URL url = new URL(getURL);
			URLConnection urlConn = url.openConnection();
			HttpURLConnection httpUrlConn = (HttpURLConnection) urlConn;
			// 默认情况下是false;
			httpUrlConn.setDoOutput(false);
			// 设置是否从httpUrlConnection读入，默认情况下是true
			httpUrlConn.setDoInput(true);
			// Get 请求不能使用缓存
			httpUrlConn.setUseCaches(false);
			// 设定请求的方法为"GET"，默认是GET
			httpUrlConn.setRequestMethod("GET");
			// 设定请求延迟时间
			httpUrlConn.setConnectTimeout(100000);
			// 设置从主机读取数据超时
			httpUrlConn.setReadTimeout(100000);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpUrlConn.getInputStream(),env));
			
			int resCode = httpUrlConn.getResponseCode();
			String resMsg = httpUrlConn.getResponseMessage();
			
			log.info("通知请求resCode==>" + resCode + "|resMsg==>" + resMsg);
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
			httpUrlConn.disconnect();
			return StringUtils.trimToEmpty(sb.toString());
		} catch (Exception e) {
			log.error("连接出错:" + getURL + ",失败原因:" + e.getMessage());
			throw e;
		}
	}

	/**
	 * 客户端用Get方法向服务器提交请求,并获取HTTP结果码
	 * 
	 */
	public static int getResCodeWithReqForGet(String getURL) {
		int resCode = -1;
		try {
			URL url = new URL(getURL);
			URLConnection urlConn = url.openConnection();
			HttpURLConnection httpUrlConn = (HttpURLConnection) urlConn;
			// 默认情况下是false;
			httpUrlConn.setDoOutput(false);
			// 设置是否从httpUrlConnection读入，默认情况下是true
			httpUrlConn.setDoInput(true);
			// Get 请求不能使用缓存
			httpUrlConn.setUseCaches(false);
			// 设定请求的方法为"GET"，默认是GET
			httpUrlConn.setRequestMethod("GET");
			// 设定请求等待响应时间
			httpUrlConn.setConnectTimeout(3000);
			httpUrlConn.getInputStream();
			resCode = httpUrlConn.getResponseCode();
			httpUrlConn.disconnect();
		} catch (Exception e) {
			log.error("连接出错:" + getURL + ",失败原因:" + e.getMessage(), e);
		}
		return resCode;
	}

	/**
	 * 从输入流中读入数据
	 * 
	 */
	public static String readBuffer(InputStreamReader in) throws Exception {
		try {
			BufferedReader reader = new BufferedReader(in);
			String line = "";
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			return StringUtils.trimToEmpty(sb.toString());
		} catch (Exception e) {
			log.error("从输入流中读入数据出错,失败原因:" + e.getMessage());
			throw e;
		}
	}

	/**
	 * 响应调用者
	 * 
	 * @param req
	 * @param res
	 * @param s
	 */
	public static void res2Client(HttpServletRequest req,
			HttpServletResponse res, String s) throws Exception{
		try {
			res.setContentType("text/plain;charset=gb2312");
			PrintWriter out = res.getWriter();
			out.print(s);
			out.flush();
			out.close();
		} catch (Exception e) {
			log.error("响应调用者出错,调用参数:" + s + ",失败原因:" + e.getMessage());
			throw e;
		}
	}

	/**
	 * 获取请求客户端IP(包括穿透nginx集群代理)
	 */
	public static String getRemoteAddr(HttpServletRequest req) {
		String ip = req.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getRemoteAddr();
		}
		if (ip != null && ip.length() > 0) {
			String[] ips = ip.split(",");
			for (int i = 0; i < ips.length; i++) {
				if (ips[i].trim().length() > 0
						&& !"unknown".equalsIgnoreCase(ips[i].trim())) {
					ip = ips[i].trim();
					break;
				}
			}
		}
		return ip;
	} 

	public static String prepareParam(Map<String, Object> paramMap) {
		StringBuffer sb = new StringBuffer();
		if (paramMap.isEmpty()) {
			return "";
		} else {
			for (String key : paramMap.keySet()) {
				String value = (String) paramMap.get(key);
				if (sb.length() < 1) {
					sb.append(key).append("=").append(value);
				} else {
					sb.append("&").append(key).append("=").append(value);
				}
			}
			return sb.toString();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String subscribeProductNoConfirm(){
		try {
//			String postURL = "http://aep.sdp.com:1100/OnlineChannel/SubscribeBackProdect";//请求链接
			String postURL = "http://aep.sdp.com:1100/ToolBarMessages/SubscribeProductNoConfirm";//请求链接
			
			StringBuffer authorization_head = new StringBuffer();
			StringBuffer xwsse_head = new StringBuffer();
//			Authorization : WSSE realm="SDP", profile="UsernameToken", type="AppKey"
//				X-WSSE : UsernameToken Username="XXXX", PasswordDigest="Qd0QnQn0eaAHpOiuk/0QhV+Bzdc=", Nonce="eUZZZXpSczFycXJCNVhCWU1mS3ZScldOYg==", Created="2013-09-05T02:12:21Z"
			authorization_head.append("WSSE realm=\"SDP\",profile=\"UsernameToken\",type=\"AppKey\"");
//			authorization_head.append("WSSE realm=SDP,profile=UsernameToken,type=AppKey");
			
//			用户安全子系统标识
//			根据鉴权认证头“Authorization”中的不同类型“type”，本参数填不同的值：
//			type是Username时，填写AEP的用户ID;
//			当type是AppKey或AndroidSDK时，填写应用的Appkey。
			String username = "3a9c9df372034abf8cf537cb4850ac86";
//			String username = "24e0f0c0d4fb414dbac81a73d681d671";
			//随机数.具体参见OASIS WS-Security standard
			String nonce = getUUID();
			String created = DateUtil.getUTCTimeStr(new Date(), DateUtil.DATE_FORMAT_YMDTHMSZ);
			String password = "f24d04ad7752c17a";
//			String password = "20bbceeb7acc43e0";
//			密码摘要
//			摘要算法如下
//			PasswordDigest = Base64 (SHA-1 (nonce + created + password)).
//			根据鉴权认证头“Authorization”中的不同类型“type”，password取不同的值：
//			type是Username时，password为AEP中API消费者的API调用密码;
//			当type是AppKey时，password为App Secret；
//			当Type是AndroidSDK时，password为SDK的安全指纹。
			String passwordDigest = Base64.encodeBase64(Sha.sha1Byte(nonce + created + password));
			xwsse_head.append("UsernameToken Username=\"" + username + "\",PasswordDigest=\"" + passwordDigest + "\",Nonce=\"" + nonce + "\",Created=\"" + created + "\"");
//			xwsse_head.append("UsernameToken Username=" + username + ",PasswordDigest=" + passwordDigest + ",Nonce=" + nonce + ",created=" + created);
			log.info("Authorization:" + authorization_head + ";X-WSSE:" + xwsse_head);
			
			String chanelId = "31b0fa3";//操作员所在部门编码（渠道编码）
			String userCode = "ZXTGH07";//操作员工号（员工工号）
			
//			电信业务类型:
//				0000         移动业务
//				0100         固网电话业务
//				0200         宽带业务
//				0300         专线业务
//				0400         800业务
//				0500         400业务			
			String serviceType = "0000";
//			外部受理订单号。
//			最长30位，建议格式为时间(精确到秒)+渠道编码（ChanelId）+六位序列号。
//			YYYYMMDDHHmmss+ChanelId+XXXXXX如：201410212359591234567000001。
			String orderId = DateUtil.dateFormat(new Date(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS) + chanelId + RandomUtil.next(100000, 999999);
//			用户服务号码。
//			用户手机号码，不带国家码，
//			如：18600000000
			String serialNumber = "18621802288";
			//需要订购的产品对象
			String productId = "13750";
//			产品类型:
//				2:子产品（流量包、短信包等）
//				3:增值业务产品（天气预报等）
			String productType = "2";
//			生效标识：
//			0：立即生效
//			1：预约生效
			String startEnable = "1";
			Map<String, Object> paramsMap = new LinkedHashMap<String, Object>();
			paramsMap.put("ChanelId", chanelId);
			paramsMap.put("UserCode", userCode);
			paramsMap.put("ServiceType", serviceType);
			paramsMap.put("OrderId", orderId);
			paramsMap.put("SerialNumber", serialNumber);
			paramsMap.put("ProductId", productId);
			paramsMap.put("ProductType", productType);
			paramsMap.put("StartEnable", startEnable);
			paramsMap.put("OrderMonth", "1");
			String params = prepareParam(paramsMap);//参数
//			log.info("params:" +params);
//			params = URLEncoder.encode(params);//请求参数
			log.info("params:" + params);
			
			
//		    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.zj.chinamobile.com", 8080));  
//		    URL url = new URL(postURL);  
//		    HttpURLConnection httpUrlConn = (HttpURLConnection)url.openConnection(proxy);  
			URL url = new URL(postURL);
			URLConnection urlConn = url.openConnection();
			HttpURLConnection httpUrlConn = (HttpURLConnection) urlConn;
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false;
			httpUrlConn.setDoOutput(true);
			// 设置是否从httpUrlConnection读入，默认情况下是true
			httpUrlConn.setDoInput(true);
			// Post 请求不能使用缓存
			httpUrlConn.setUseCaches(false);
			// 设定请求的方法为"POST"，默认是GET
			httpUrlConn.setRequestMethod("POST");
			//设置http请求头
			httpUrlConn.setRequestProperty("Host", "aep.sdp.com");
			httpUrlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpUrlConn.setRequestProperty("Authorization", authorization_head.toString());
			log.info("Authorization:" + authorization_head.toString());
			httpUrlConn.setRequestProperty("X-WSSE", xwsse_head.toString());
			log.info("X-WSSE:" + xwsse_head.toString());
			OutputStreamWriter wr = new OutputStreamWriter(
					httpUrlConn.getOutputStream());
			wr.write(params);
			wr.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpUrlConn.getInputStream()));
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			wr.close();
			in.close();

			return StringUtils.trimToEmpty(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	
	/**
	 * @Title: getUUID
	 * @Description: 
	 * 获取唯一编码UUID
	 * @return String
	 */ 
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String uuid_str = uuid.toString();
		// 去掉"-"符号
		uuid_str = uuid_str.substring(0, 8) + uuid_str.substring(9, 13)
				+ uuid_str.substring(14, 18) + uuid_str.substring(19, 23)
				+ uuid_str.substring(24);
		log.info("获取UUID:" + uuid_str + "::::::::::::::::::::::::::::::");
		return uuid_str;
	}
	
	public static void main(String[] args) {
		log.info(subscribeProductNoConfirm());
		
//		TRedSubscribeConfig config = new TRedSubscribeConfig();
//		System.out.println(config.getSubscribeAmount().longValue());
	}

}