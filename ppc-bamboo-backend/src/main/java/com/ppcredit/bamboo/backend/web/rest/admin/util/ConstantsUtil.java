
package com.ppcredit.bamboo.backend.web.rest.admin.util;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 全局常量工具类
 * @modificationHistory.  
 * <ul>
 * <li>yang_hx 2013-11-12下午03:25:14 TODO</li>
 * </ul>
 */

public class ConstantsUtil {
	
	

	
	// 超时设置
	/**
	 * 超时时间（单位：秒）
	 */
	public static final int TIMEOUT = 3000;
	
	// HTTP设置
	/**
	 * 连接超时时间（单位：秒）
	 */
	public static final int HTTP_CONNECTTIMEOUT = 1;
	/**
	 * 读取超时时间（单位：秒）
	 */
	public static final int HTTP_READTIMEOUT = 1;
	/**
	 * 是否使用代理
	 */
	public static final boolean HTTP_PROXY = true;
	/**
	 * 代理地址
	 */
	public static final String HTTP_PROXY_HOSTNAME = "";
	/**
	 * 代理端口
	 */
	public static final int HTTP_PROXY_PORT = 1;
	
	// 配置
	/**
	 * 默认字符编码集
	 */
	public static final String SYSTEM_DEFAULT_ENCODING = "UTF-8";
	
	/**
	 * 网页后缀
	 */
	public static final String HTML_SUFFIX = ".html";
	
	// 来源
	/**
	 * 微信(浙江移动)
	 */
	public static final String SOURCE_WECHAT = "1";
	/**
	 * 支付宝(浙江移动)
	 */
	public static final String SOURCE_ALIPAY = "2";
	/**
	 * 微信(中国移动)
	 */
	public static final String SOURCE_WECHAT_CHINA = "3";
	/**
	 * 支付宝(中国移动)
	 */
	public static final String SOURCE_ALIPAY_CHINA = "4";
	/**
	 * 微信(金华移动)
	 */
	public static final String SOURCE_WECHAT_JINHUA = "5";
	
	// 日志
	// 日志-公众账号
	/**
	 * 微信(中国移动)
	 */
	public static final String LOGGER_WECHAT_CHINA = "wechatChina";
	/**
	 * 微信(浙江移动)
	 */
	public static final String LOGGER_WECHAT = "wechat";
	/**
	 * 支付宝(浙江移动)
	 */
	public static final String LOGGER_ALIPAY = "alipay";
	/**
	 * 支付宝(中国移动)
	 */
	public static final String LOGGER_ALIPAY_CHINA = "alipayChina";
	
	// 日志-支付
	/**
	 * 支付组件
	 */
	public static final String LOGGER_PAY = "pay";
	
	// API
	/**
	 * API
	 */
	public static final String LOGGER_API = "api";
	/**
	 * 微信API
	 */
	public static final String LOGGER_WECHAT_API = "wechatApi";
	/**
	 * 支付宝API
	 */
	public static final String LOGGER_ALIPAY_API = "alipayApi";
	
	// 日志-统一接口
	/**
	 * 统一接口
	 */
	public static final String LOGGER_TYJK = "tyjk";
	/**
	 * 路由接口
	 */
	public static final String LOGGER_ROUTER = "router";
	
	// 日志-定时任务
	/**
	 * 用户任务
	 */
	public static final String LOGGER_TASK_USER = "userTask";
	
	/**
	 * 用户任务
	 */
	public static final String LOGGER_TASK_USER_FOLLOW = "userFollowTask";
	
	/**
	 * 分组任务
	 */
	public static final String LOGGER_TASK_MOVE_GROUP = "moveGroup";
	
	
	public static final String  MJ_BILL_GATHER_SUB = "mj.bill.gather.sub";//手机详单提交采集请求
	public static final String  MJ_BILL_DATA_GET = "mj.bill.data.get"; //获取手机详单数据
	public static final String  MJ_BILL = "mj.bill"; //手机详单认证
	public static final String  MJ_BILL_DESCRIPTION = "手机详单认证"; //手机详单认证
	
	
	
	// 有效性
	/**
	 * 无效
	 */
	public static final int VALIDA_NO = 0;
	/**
	 * 有效
	 */
	public static final int VALID_YES = 1;
	/**
	 * 错误
	 */
	public static final int VALID_ERR = -1;
	
	/**
	 * 验证码错误
	 */
	public static final int VALID_ERR_CODE = -2;
	
	/**
	 * 正常
	 */
	public static final String STATUS_SUCESS = "S";
	/**
	 * 冻结
	 */
	public static final String STATUS_FAILURE = "F";
	/**
	 * 数据源
	 */
	public static final String DATA_SOURCE_ID = "100";
	/**
	 * 爬虫
	 */
	public static final String DATA_SOURCE_CRAWLER = "200";
	/**
	 * 查询失败
	 */
	public static final String QUERY_FAILURE = "查询失败";
	
	public static final String Refash = "请刷新页面后重新提交";
	public static final String Refash1 = "请您再次登录";
	public static final String Refash2 = "服务器繁忙，请稍后再试";
	public static final String Refash3 = "\u8bf7\u60a8\u542f\u7528\u6d4f\u89c8\u5668Cookie\u529f\u80fd\u6216\u66f4\u6362\u6d4f\u89c8\u5668\u3002<a class=\"flk13\" target=\"_blank\" href=\"http://help.360buy.com/help/question-135.html\">\u5982\u4f55\u542f\u7528Cookie\uff1f</a>";
	
	/**
	 * 初始化密码
	 */
	public static final String INITIALIZATION_PASSWORD = "123456";
	/**
	 * 冻结
	 */
	public static final String AUTH_TYPE = "ALL";
	/**
	 * WEB
	 */
	public static final String AGENT_WEB = "WEB";	
	
	/**
	 * 初始化每天购买次数
	 */
	public static final String BUYNUMS = "100000";
	public static final String BLACKBUYNUMS = "1000";
	
	/**
	 * 获得来源集合
	 * @author yang_hx
	 * @creationDate. 2014-2-24 下午03:04:17 
	 * @return map
	 */
	public static Map<String, String> getSources() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(SOURCE_WECHAT, "微信(浙江移动)");
		map.put(SOURCE_ALIPAY, "支付宝(浙江移动)");
		map.put(SOURCE_WECHAT_CHINA, "微信(中国移动)");
		map.put(SOURCE_ALIPAY_CHINA, "支付宝(中国移动)");
		return map;
	}
	
	/**
	 * 获得支付宝来源集合
	 * @author yang_hx
	 * @creationDate. 2014-2-28 下午03:19:29 
	 * @return map
	 */
	public static Map<String, String> getAlipaySources() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(SOURCE_ALIPAY, "支付宝(浙江移动)");
		map.put(SOURCE_ALIPAY_CHINA, "支付宝(中国移动)");
		return map;
	}
	
	/**
	 * 来源是否属于支付宝
	 * @author yang_hx
	 * @creationDate. 2014-3-11 下午11:44:47 
	 * @param source 来源
	 * @return 布尔
	 */
	public static boolean sourceBelongAlipay(String source) {
		return getAlipaySources().containsKey(source);
	}
	
	/**
	 * 获得微信来源集合
	 * @author yang_hx
	 * @creationDate. 2014-3-11 下午11:15:12 
	 * @return map
	 */
	public static Map<String, String> getWechatSources() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(SOURCE_WECHAT, "微信(浙江移动)");
		map.put(SOURCE_WECHAT_CHINA, "微信(中国移动)");
		return map;
	}
	
	/**
	 * 获得重复提交集合
	 * @author yang_hx
	 * @creationDate. 2015-8-11 下午11:15:12 
	 * @return map
	 */
	public static Map<String, String> getRepeatReason(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(Refash, "Refash");
		map.put(Refash1, "Refash");
		map.put(Refash2, "Refash");
		map.put(Refash3, "Refash");
		return map;
	}
	
	/**
	 * 来源是否属于微信
	 * @author yang_hx
	 * @creationDate. 2014-3-11 下午11:46:12 
	 * @param source 来源
	 * @return 布尔
	 */
	public static boolean sourceBelongWechat(String source) {
		return getWechatSources().containsKey(source);
	}
	
	/**
	 * 获得来源文本
	 * @author yang_hx
	 * @creationDate. 2014-2-24 下午03:05:24 
	 * @param source 来源
	 * @return 来源对应的文本
	 */
	public static String getSourceText(String source) {
		if (StringUtils.isEmpty(source)) return "";
		return getSources().get(source);
	}
	
	/**
	 * 平台ID
	 */
//	public final static String LOTTERY_MGYH_PlATFORMID = "8ac6890939de0b230139e1b1e3140005"; // 测试环境
	public final static String LOTTERY_MGYH_PlATFORMID = GlobalConfig.getProperty("wechat", "blank_code");
	
	/**
	 * 业务ID
	 */
	public final static String LOTTERY_MGYH_BUSINESSID =GlobalConfig.getProperty("wechat", "blank_businessid");
	
	/**
	 * 后台执行定时任务的实例名
	 */
	public final static String ADMIN_QUARZ_ALLOWINSTANCES =GlobalConfig.getProperty("wechat", "admin.quarz.allowInstances");
	
	/**
	 *  前台执行定时任务的实例名
	 */
	public final static String FRONT_QUARZ_ALLOWINSTANCES =GlobalConfig.getProperty("wechat", "front.quarz.allowInstances");
	

	
	/**
	 * 
	 * @author 涂小炼
	 * @creationDate. 2014-10-10 上午10:03:08 
	 * @param isAdmin  是否后台  true 后台  false 前台
	 * @return
	 */
	public static boolean isAllowRun(Boolean isAdmin) {
		String instance = System.getProperty("weblogic.Name"); // 获取实例名
		String allowInstance = isAdmin?ConstantsUtil.ADMIN_QUARZ_ALLOWINSTANCES:ConstantsUtil.FRONT_QUARZ_ALLOWINSTANCES;
		if (StringUtils.isEmpty(instance)) return true; // 实例名为空则不校验直接返回true
		return instance.equals(allowInstance); // 匹配当前实例与允许实例
	}
	
	
	
	// 供应商在拍拍贷的编号
		// 本地
		public final static String DATA_SOURCE_ID_LOCAL = "00";
		// 银联
		public final static String DATA_SOURCE_ID_YINLIAN = "01";
		// 爰金
		public final static String DATA_SOURCE_ID_YUANJIN = "02";
		// 国政通
		public final static String DATA_SOURCE_ID_GZT = "03";
		// 人人催
		public final static String DATA_SOURCE_ID_RRC = "07";//04 鹏元
		

		// 银联供应商 参数
		public final static String YINLIAN_BANK_CODE = "8888";
		public final static String YINLIAN_INST_CODE = "8888";
		public final static String YINLIAN_CHANNEL = "PPD";
		public final static String YINLIAN_ENDUCATION_DATA_TYPE = "1B020101";
		public final static String YINLIAN_SCHOOL_ROLL_DATA_TYPE = "3K010101";
		
		
		
		/**
		 * 学信网信息map
		 */
		public final static Map<String, String> MAP_EDUCATION_INFO = new HashMap<String, String>();
		static {  
			MAP_EDUCATION_INFO.put("毕结业结论：", "conclusion");  
			MAP_EDUCATION_INFO.put("性别：", "sex"); 
			MAP_EDUCATION_INFO.put("姓名：", "name");  
			MAP_EDUCATION_INFO.put("毕业院校：", "schoolName");  
			MAP_EDUCATION_INFO.put("学习形式：", "learningForm");  
			MAP_EDUCATION_INFO.put("出生日期：", "birthday");  
			MAP_EDUCATION_INFO.put("证书编号：", "certificateNumber");  
			MAP_EDUCATION_INFO.put("入学时间：", "entranceTime");  
			MAP_EDUCATION_INFO.put("专业名称：", "professionalName");  
			MAP_EDUCATION_INFO.put("学历类别：", "category");  
			MAP_EDUCATION_INFO.put("院校所在地：", "instituteAddress");  
			MAP_EDUCATION_INFO.put("学历层次：", "educationalLevel");  
			MAP_EDUCATION_INFO.put("入学日期：", "entranceTime");  
			MAP_EDUCATION_INFO.put("层次：", "educationalLevel");  
			MAP_EDUCATION_INFO.put("院校名称：", "schoolName");  
			MAP_EDUCATION_INFO.put("民族：", "nation");  
			MAP_EDUCATION_INFO.put("考生号：", "examineeNumber");
			MAP_EDUCATION_INFO.put("学号：", "studyNo");  
			MAP_EDUCATION_INFO.put("学籍状态：", "status");  
			MAP_EDUCATION_INFO.put("离校日期：", "departureDate");  
			MAP_EDUCATION_INFO.put("身份证号：", "idNumber");  
			MAP_EDUCATION_INFO.put("系(所、函授站)：", "department");  
			MAP_EDUCATION_INFO.put("分院：", "branch");  
			MAP_EDUCATION_INFO.put("班级：", "class");   
			MAP_EDUCATION_INFO.put("学制：", "educationalSystem"); 
			MAP_EDUCATION_INFO.put("毕业时间：", "graduationTime");  
		}  
		
		/**
		 * 淘宝网地址信息map
		 */
		public final static Map<String, String> MAP_TAOBAO_ADDRESS_INFO = new HashMap<String, String>();
		static {
			
			MAP_TAOBAO_ADDRESS_INFO.put("收货人", "receiver");  
			MAP_TAOBAO_ADDRESS_INFO.put("所在地区", "area");  
			MAP_TAOBAO_ADDRESS_INFO.put("详细地址", "detailAddress");  
			MAP_TAOBAO_ADDRESS_INFO.put("邮编", "zipCode");  
			MAP_TAOBAO_ADDRESS_INFO.put("电话/手机", "phone");  
		}
	
}
