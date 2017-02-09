package com.ppcredit.bamboo.backend.web.rest.admin.util.web;

import com.ppcredit.bamboo.backend.web.rest.admin.util.security.Base64;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>
 *   <b>总部订单  订单管理 > 订单分配  公共类</b>
 *   <ul>
 *       <li>使用jericho实现抓取网页内容</li>
 *       <li>获取分页信息的URL（便于查询出所有待分配的订单）</li>
 *       <li>获取orderId List（便于查询订单详情）</li>
 *   </ul>
 * </p>
 * @author duanjun
 * @since 2012-10-22 14:35
 *
 */
public class ClientUtil {
	
	
	protected static Logger logger = LoggerFactory.getLogger(ClientUtil.class);
	
	//懒汉式单例类.在第一次调用的时候实例化 
    //单一模式实例 保证在Java应用程序中，一个类Class只有一个实例在
    private static HttpClient httpClient=null;
    
    //静态工厂方法 
    
    public synchronized  static HttpClient getInstance() {
	        if (httpClient == null) {  
	        	httpClient = new HttpClient(); 
	        }  
	        DefaultHttpParams.getDefaultParams().setParameter(
					"http.protocol.cookie-policy",
					CookiePolicy.BROWSER_COMPATIBILITY);
	        return httpClient;
     }
    
    /**
     * 
     * @discription 刷新Client
     * @author yang_hx       
     * @created 2015-8-3 下午6:16:01     
     * @return
     */
    public synchronized static HttpClient refreshClient(){
    	
    	httpClient = null;
    	httpClient = new HttpClient();
    	 DefaultHttpParams.getDefaultParams().setParameter(
					"http.protocol.cookie-policy",
					CookiePolicy.BROWSER_COMPATIBILITY);
    	 return httpClient;
    }
	
	
	/**  
     * 日期格式化，自定义输出日期格式  
     * @param date  
     * @return  
     */  
    public static String getFormatDate(Date date,String dateFormat){   
        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);   
        return sdf.format(date);   
    }   
	
    /**  
     * 格式化字符串为日期
     * @param source  
     * @return  
     */  
	public static Date fomatDate(String source,String dateFormat){
		SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);   
        try {
			return sdf.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean checkLogin(String message){
		if(message.equalsIgnoreCase("success") || message.equalsIgnoreCase("lslogin")|| message.equalsIgnoreCase("oldUser")|| message.equalsIgnoreCase("newUser") || message.equalsIgnoreCase("passwdchange")){
			return true;
		}else{
			return false;
		}
	}
	public static PostMethod getAjaxPostMethod(PostMethod post,String host) { 
		post.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36"); 
		post.setRequestHeader("Accept", "text/plain, */*; q=0.01"); 
		post.setRequestHeader("Host", host); 
		post.setRequestHeader("Accept-Encoding", "gzip,deflate"); 
		post.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8"); 
		post.setRequestHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3"); 
		post.setRequestHeader("Connection", "keep-alive"); 
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8"); 
		post.setRequestHeader("X-Requested-With", "XMLHttpRequest"); 
		return post; 
	}
	
	public static PostMethod getPostMethod(PostMethod post,String host) { 
		post.setRequestHeader("Host", host); 
		post.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.94 Safari/537.4"); 
		post.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"); 
		post.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8"); 
		post.setRequestHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3"); 
		post.setRequestHeader("Connection", "keep-alive"); 
		post.setRequestHeader("Cache-Control", "max-age=0"); 
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");  
		return post; 
	} 
	public static GetMethod getMethod(GetMethod getMethod2,String host) { 
		getMethod2.setRequestHeader("Host", host); 
		getMethod2.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.94 Safari/537.4"); 
		getMethod2.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"); 
		getMethod2.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8"); 
		getMethod2.setRequestHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3"); 
		getMethod2.setRequestHeader("Connection", "keep-alive"); 
		getMethod2.setRequestHeader("Cache-Control", "max-age=0"); 
		getMethod2.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
		return getMethod2; 
	} 
	
	public static HttpPost getHttpPost(HttpPost post,String host){
		
		post.addHeader("Host", host); 
		post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.94 Safari/537.4"); 
		post.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"); 
		post.addHeader("Accept-Language", "zh-CN,zh;q=0.8"); 
		post.addHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3"); 
		post.addHeader("Connection", "keep-alive"); 
		post.addHeader("Cache-Control", "max-age=0");
		post.addHeader("Accept-Encoding","deflate"); // //Accept-Encoding:"gzip, deflate"
		
		return post;
		
	}
	
	public static HttpGet getHttpGet(HttpGet get,String host) { 
		get.addHeader("Host", host); 
		get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.94 Safari/537.4"); 
		get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"); 
		get.addHeader("Accept-Language", "zh-CN,zh;q=0.8"); 
		get.addHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3"); 
		get.addHeader("Connection", "keep-alive"); 
		get.addHeader("Cache-Control", "max-age=0"); 
		get.addHeader("Content-Type", "text/html; charset=UTF-8");
		get.addHeader("Accept-Encoding","deflate"); 
		
		return get; 
	} 
	
	public static List<String> userAgentList = new ArrayList<String>();
	static{
		/**
		 * PC版
		 */
		//默认
		userAgentList.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.94 Safari/537.4");
		//safari 5.1 – MAC
		userAgentList.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50");
		//safari 5.1 – Windows
		userAgentList.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50");
		//IE 9.0
		userAgentList.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
		//IE 8.0
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)");
		//IE 7.0
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)");
		//Firefox 4.0.1 – MAC
		userAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1");
		//Firefox 4.0.1 – Windows
		userAgentList.add("Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1");
		//Opera 11.11 – MAC
		userAgentList.add("Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11");
		//Opera 11.11 – Windows
		userAgentList.add("Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11");
		//Chrome 17.0 – MAC
		userAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
		//傲游（Maxthon）
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)");
		//腾讯TT
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0)");
		//世界之窗（The World） 2.x
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		//世界之窗（The World） 3.x
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)");
		//搜狗浏览器 1.x
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)");
		//360浏览器
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)");
		//Avant
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Avant Browser)");
		//Green Browser
		userAgentList.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
	}
	
	public static List<String> mobileUserAgentList = new ArrayList<String>();
	static{
		/**
		 * 移动版
		 */
		//safari iOS 4.33 – iPhone
		mobileUserAgentList.add("Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5");
		//safari iOS 4.33 – iPod Touch
		mobileUserAgentList.add("Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5");
		//safari iOS 4.33 – iPad
		mobileUserAgentList.add("Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5");
		//Android N1
		mobileUserAgentList.add("Mozilla/5.0 (Linux; U; Android 2.3.7; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
		//Android QQ浏览器 For android
		mobileUserAgentList.add("MQQBrowser/26 Mozilla/5.0 (Linux; U; Android 2.3.7; zh-cn; MB200 Build/GRJ22; CyanogenMod-7) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
		//Android Opera Mobile
		mobileUserAgentList.add("Opera/9.80 (Android 2.3.4; Linux; Opera Mobi/build-1107180945; U; en-GB) Presto/2.8.149 Version/11.10");
		//Android Pad Moto Xoom
		mobileUserAgentList.add("Mozilla/5.0 (Linux; U; Android 3.0; en-us; Xoom Build/HRI39) AppleWebKit/534.13 (KHTML, like Gecko) Version/4.0 Safari/534.13");
		//BlackBerry
		mobileUserAgentList.add("Mozilla/5.0 (BlackBerry; U; BlackBerry 9800; en) AppleWebKit/534.1+ (KHTML, like Gecko) Version/6.0.0.337 Mobile Safari/534.1+");
		//WebOS HP Touchpad
		mobileUserAgentList.add("Mozilla/5.0 (hp-tablet; Linux; hpwOS/3.0.0; U; en-US) AppleWebKit/534.6 (KHTML, like Gecko) wOSBrowser/233.70 Safari/534.6 TouchPad/1.0");
		//Nokia N97
		mobileUserAgentList.add("Mozilla/5.0 (SymbianOS/9.4; Series60/5.0 NokiaN97-1/20.0.019; Profile/MIDP-2.1 Configuration/CLDC-1.1) AppleWebKit/525 (KHTML, like Gecko) BrowserNG/7.1.18124");
		//Windows Phone Mango
		mobileUserAgentList.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0; HTC; Titan)");
		//UC无
		mobileUserAgentList.add("UCWEB7.0.2.37/28/999");
		//iphone5
		mobileUserAgentList.add("Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
		//WP8.1
		mobileUserAgentList.add("Mozilla/5.0 (Windows Phone 8.1; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; NOKIA; Lumia 520) like Gecko");
	}
	
	public static String getRandomUserAgent(){
		
		int size = userAgentList.size();
		int index = (int)(Math.random() * size);
		String userAgent = userAgentList.get(index);
		logger.info("Get User-Agent >>>> " + userAgent);
		
		return userAgent;
	}
	
	public static String getMobileRandomUserAgent(){
		
		int size = mobileUserAgentList.size();
		int index = (int)(Math.random() * size);
		String userAgent = mobileUserAgentList.get(index);
		logger.info("Get Mobile User-Agent >>>> " + userAgent);
		
		return userAgent;
	}
	
    public static String getCookies(CookieStore cookieStore) {
        String cookies = "";
        for (org.apache.http.cookie.Cookie cookie : cookieStore.getCookies()) {
            cookies = cookies + cookie.getName() + "=" + cookie.getValue()
                    + ";";
        }
        if (cookies.length() != 0) {
            cookies = cookies.substring(0, cookies.length() - 1);
        }
        return cookies;

    }
	
	public static String getCookie(HttpClient httpClient){
		Cookie[] cookies = httpClient.getState().getCookies();
		StringBuffer sbf = new StringBuffer();
		for (Cookie cookie : cookies) { 
			sbf.append(cookie.getName()+"=").append(cookie.getValue()).append(";");
		}
		return sbf.toString();
	}
	
	/**
	 * 读文件
	 * @param pathAndName
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String pathAndName) throws Exception{
		
		FileInputStream fis = new FileInputStream(new File(pathAndName));
		Reader reader = new InputStreamReader(fis, "UTF-8");
		String txt = IOUtils.toString(reader);
		fis.close();
		reader.close();
		
		return txt;
			
		}
	/**
	 * base64 解码
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static String decode(String code) throws Exception{
		if(code == null){
			return null;
		}
		return IOUtils.toString(Base64.decode(code),"UTF-8");
	}
	
	
	/**
	 * base64 解码
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static String encode(String code) throws Exception{
		if(code == null){
			return null;
		}
		return Base64.encodeBase64(code);
	}
	
	
	/**
	 * 
	 * @param localPath 文件路径
	 * @param fileName 文件名
	 * @param txt 文件内容
	 * @throws Exception
	 */
	private void saveFile(String localPath,String fileName,String txt) throws Exception{
		StringBuffer sbf = new StringBuffer();
		sbf.append("\n").append(txt);
		
		// 开始读取
		if (null != txt) {
			File file = new File(localPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(localPath +File.separator+ fileName
					+ ".jsp");
			Writer os = new OutputStreamWriter(fos, "UTF-8");
			os.write(sbf.toString());
			os.close();
		}
	}
	/**
	 * 验证界面是否是home界面
	 * @param html
	 */
	public static boolean checkPage(String html,String id){
		if(html == null) return false;
		
		Document doc = Jsoup.parseBodyFragment(html);
		
		Element elements = doc.getElementById(id);
		
		if(elements != null){
			return true;
		}
		
		String title = doc.title();
		if(title.indexOf("京东")>-1){
			return true;
		}
		return false;
	}
	
	public static synchronized byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
//        inStream.close();  
        //把outStream里的数据写入内存  
        byte[] outByte = outStream.toByteArray();
        
        //关闭输出流
        outStream.close();
        
        return outByte;  
    }  
	
	
	
}
