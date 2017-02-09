
package com.ppcredit.bamboo.backend.web.rest.admin.util.date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类,提供时间转换、比较、格式化等各种常用方法
 * @modificationHistory.  
 * <ul>
 * <li>yang_hx 2010-8-27下午04:06:10 TODO</li>
 * <li>
 * yang_hx 2012-7-31 下午03:06:05 新增getDate方法，
 * 修改getNowDate方法以及其它几个使用了toLocaleString()的方法，解决Linux下时间错误问题
 * </li>
 * </ul>
 */

public class DateUtil {
	private static Logger LOG = LoggerFactory.getLogger(DateUtil.class);
	
	private  static Calendar calendar=Calendar.getInstance(); 
	
	/**
	 * 时间间隔：日
	 */
	public final static int DATE_INTERVAL_DAY = 1;
	/**
	 * 时间间隔：周
	 */
	public final static int DATE_INTERVAL_WEEK = 2;
	/**
	 * 时间间隔：月
	 */
	public final static int DATE_INTERVAL_MONTH = 3;
	/**
	 * 时间间隔：年
	 */
	public final static int DATE_INTERVAL_YEAR = 4;
	/**
	 * 时间间隔：小时
	 */
	public final static int DATE_INTERVAL_HOUR = 5;
	/**
	 * 时间间隔：分钟
	 */
	public final static int DATE_INTERVAL_MINUTE = 6;
	/**
	 * 时间间隔：秒
	 */
	public final static int DATE_INTERVAL_SECOND = 7;
	/**
	 * 时间格式：年月日
	 */
	public final static String DATE_FORMAT_YMD = "yyyy-MM-dd";
	/**
	 * 时间格式：年月日时分秒
	 */
	public final static String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 时间格式：年月日'T'时分秒'Z'
	 */
	public final static String DATE_FORMAT_YMDTHMSZ = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	/**
	 * 时间格式：yyyyMMddHHmmss
	 */
	public final static String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	
	public final static String yyyyMMddHHmm = "yyyyMMddHHmm";
	public final static String yyyyMMdd = "yyyyMMdd";
	public final static String yyyyMM = "yyyyMM";
	public final static String HHmmss = "HHmmss";
	public final static String HHmm = "HHmm";

	public final static String yyyyMMddHHmmSpt = "yyyy-MM-dd HH:mm";
	public final static String yyyyMMSpt = "yyyy-MM";
	public final static String HHmmssSpt = "HH:mm:ss";
	public final static String HHmmSpt = "HH:mm";
	
	/**
	 * 时间格式：年月日时分秒
	 */
	public final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	/**
	 * 时间格式：年月日时分秒毫秒
	 */
	public final static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");	
	
	public static String getFormatString(Date date){
		return SDF.format(date);
	}
	
	public static String getDateFormatString(Date date) {
		if (date == null) {
			return "";
		}
		return FORMAT.format(date);
	}
	
	/**
	 * 获取网络当前时间字符串(年月日时分秒毫秒) 同步日志表中的时间
	 * @return
	 */
	public static String getNowStringDateTime() {
		String datetimeStr = "";
		try {
	        NTPUDPClient timeClient = new NTPUDPClient();
	        Date date = new Date();
	        timeClient.setDefaultTimeout(300);
	        timeClient.open();
	        String timeServerUrl = "172.17.19.1";
	        InetAddress timeServerAddress = InetAddress.getByName(timeServerUrl);
	        TimeInfo timeInfo = timeClient.getTime(timeServerAddress);
	        date = TimeStamp.getNtpTime(timeInfo.getReturnTime()).getDate();
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	        datetimeStr = dateFormat.format(date);
	        timeClient.close();
	    } catch (Exception e) {   //抛出异常获取系统当前时间
	    	LOG.info("======NTP连接超时========");
	        datetimeStr = FORMAT.format(new Date());
	    }
		return datetimeStr;
	}
	
	public static String getStringCreateTime(String str){
		SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = ymdhms.parse(str);
			return ymd.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Deprecated
	/**
	 * 命名有问题，公共的类不需要带业务含义
	 * @param time 
	 * @see getTime
	 */
	public static Date getReportTime(Long time){
		String reportStr = SDF.format(new Date(time));
		Date date = null;
		try {
			date = SDF.parse(reportStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date getTime(Long time){
		String reportStr = SDF.format(new Date(time));
		Date date = null;
		try {
			date = SDF.parse(reportStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date nextYear() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);  //加一年
		Date date1 =  getDate(lastDate.getTime(), DATE_FORMAT_YMD);
		SimpleDateFormat format1 = new SimpleDateFormat(DATE_FORMAT_YMD);
		SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
		StringBuffer buffer = new StringBuffer();
		buffer.append(format1.format(date1)).append(" 23:59:59");	
		try {
			return format2.parse(buffer.toString());
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获得时间
	 * @author yang_hx
	 * @creationDate. 2012-7-31 下午03:06:05 
	 * @param date 时间
	 * @param dateFormat 时间格式
	 * @return 时间
	 */
	public static Date getDate(Date date, String dateFormat) {
		return dateFormat(dateFormat(date, dateFormat), dateFormat);
	}
	/**
	 * 获得当前日期(年月日)
	 * @author yang_hx
	 * @creationDate. 2010-8-27 下午05:11:23
	 * @return 当前时间（yyyy-MM-dd）
	 */
	public static Date getNowDate() {
		return dateFormat(dateFormat(new Date(), DATE_FORMAT_YMD), DATE_FORMAT_YMD);
	}
	/**
	 * 获取当前时间字符串(年月日)
	 * @author yang_hx
	 * @creationDate. 2011-5-4 下午08:22:34 
	 * @return 时间字符串
	 */
	public static String getNowStringDate() {
	    return dateFormat(new Date(), DATE_FORMAT_YMD);
	}
	/**
	 * 获得当前时间(年月日时分秒)
	 * @author yang_hx
	 * @creationDate. 2010-8-27 下午05:12:57
	 * @return 当前时间（yyyy-MM-dd HH:mm:ss）
	 */
	public static Date getNowTime() {
		return dateFormat(dateFormat(new Date(), DATE_FORMAT_YMDHMS), DATE_FORMAT_YMDHMS);
	}
	/**
	 * 获取当前时间字符串(年月日时分秒)
	 * @author yang_hx
	 * @creationDate. 2014-3-10 下午03:16:42 
	 * @return 时间字符串
	 */
	public static String getNowStringTime() {
		 return dateFormat(new Date(), DATE_FORMAT_YMDHMS);
	}
	/**
	 * 获得明天的日期字符串(格式年月日)
	 * @author yang_hx
	 * @creationDate. 2011-5-4 下午08:19:28 
	 * @return 明天的日期
	 */
	public static String getTomorrowStringDate() {
	    return dateFormat(getTomorrowTime(), DATE_FORMAT_YMD);
	}
	/**
	 * 获得明天的日期(年月日)
	 * @author yang_hx
	 * @creationDate. 2011-5-4 下午08:19:57 
	 * @return 明天的日期
	 */
	public static Date getTomorrowDate() {
		return dateAdd(DATE_INTERVAL_DAY, getNowDate(), 1);
	}
	/**
	 * 获得明天的时间(年月日时分秒)
	 * @author yang_hx
	 * @creationDate. 2011-5-4 下午08:20:19 
	 * @return 明天的时间
	 */
	public static Date getTomorrowTime() {
	    return dateAdd(DATE_INTERVAL_DAY, getNowTime(), 1);
	}
	/**
	 * 获得昨天的日期
	 * @author yang_hx
	 * @creationDate. 2013-10-22 下午03:54:48 
	 * @return 昨天的日期
	 */
	public static Date getYesterdayDate() {
		return dateAdd(DATE_INTERVAL_DAY, getNowDate(), -1);
	}
	/**
	 * 获取当月第一天   
	 * @author yang_hx
	 * @creationDate. 2013-10-22 下午03:45:53 
	 * @return 日期
	 */
    public static Date getMonthFirst() {
    	Calendar lastDate = Calendar.getInstance();
    	lastDate.set(Calendar.DATE, 1); // 设为当前月的1号
    	return getDate(lastDate.getTime(), DATE_FORMAT_YMD);
    }
    /**
     * 获得下个月第一天的日期
     * @author yang_hx
     * @creationDate. 2013-10-22 下午03:52:38 
     * @return 日期
     */
    public static Date getNextMonthFirst() {
    	Calendar lastDate = Calendar.getInstance();
    	lastDate.add(Calendar.MONTH, 1); // 加一个月
    	lastDate.set(Calendar.DATE, 1);  // 把日期设置为当月第一天
    	return getDate(lastDate.getTime(), DATE_FORMAT_YMD);
    }
	/**
	 * 取得当前星期几
	 * @author yang_hx
	 * @creationDate. 2010-9-20 下午05:34:36 
	 * @param date 时间
	 * @return 星期
	 */
	public static String getWeekOfDate(Date date) {
		if (date == null) return null;
	    String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"}; 
	    Calendar cal = Calendar.getInstance(); 
	    cal.setTime(date); 
	    int w = cal.get(Calendar.DAY_OF_WEEK) - 1; 
	    if (w < 0) w = 0; 
	    return weekDays[w]; 
	}
	/**
	 * 时间类型转换返回字符串
	 * @author yang_hx
	 * @creationDate. 2010-8-27 下午05:18:37
	 * @param date 时间
	 * @param dateFormat 时间格式
	 * @return 转换后的时间字符串
	 */
	public static String dateFormat(Date date, String dateFormat) {
		if (date == null) return null;
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.format(date);
	}
	/**
	 * 字符串时间类型转换返回Date类型
	 * @author yang_hx
	 * @creationDate. 2010-8-27 下午05:23:35
	 * @param date 字符串时间
	 * @param dateFormat 时间格式
	 * @return 转换后的时间
	 */
	public static Date dateFormat(String date, String dateFormat) {
		if (StringUtils.isEmpty(date)) return null;
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		try {
			return format.parse(date);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
	/**
	 * 加时间
	 * @author yang_hx
	 * @creationDate. 2010-8-27 下午05:28:06
	 * @param interval 增加项，可以是天数、月份、年数、时间、分钟、秒
	 * @param date 时间
	 * @param num 加的数目
	 * @return 时间加后的时间
	 */
	public static Date dateAdd(int interval, Date date, int num) {
		if (date == null) return null;
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);
		switch (interval) {
		case DATE_INTERVAL_DAY:
			calendar.add(Calendar.DATE, num);
			break;
		case DATE_INTERVAL_WEEK:
			calendar.add(Calendar.WEEK_OF_MONTH, num);
			break;
		case DATE_INTERVAL_MONTH:
			calendar.add(Calendar.MONTH, num);
			break;
		case DATE_INTERVAL_YEAR:
			calendar.add(Calendar.YEAR, num);
			break;
		case DATE_INTERVAL_HOUR:
			calendar.add(Calendar.HOUR, num);
			break;
		case DATE_INTERVAL_MINUTE:
			calendar.add(Calendar.MINUTE, num);
			break;
		case DATE_INTERVAL_SECOND:
			calendar.add(Calendar.SECOND, num);
			break;
		default:
		}
		return calendar.getTime();
	}
	/**
	 * 两个时间时间差[前面时间和比较时间比,小于比较时间返回负数]
	 * @author yang_hx
	 * @creationDate. 2010-8-27 下午05:26:13
	 * @param interval 比较项，可以是天数、月份、年数、时间、分钟、秒
	 * @param date 时间
	 * @param compare 比较的时间
	 * @return 时间差(保留两位小数点,小数点以后两位四舍五入)
	 */
	public static double getDateDiff(int interval, Date date, Date compare) {
		if (date == null || compare == null) return 0;
		double result = 0;
		double time = 0;
		Calendar calendar = null;
		switch (interval) {
		case DATE_INTERVAL_DAY:
			time = date.getTime() - compare.getTime();
			result = time / 1000d / 60d / 60d / 24d;
		    break;
		case DATE_INTERVAL_HOUR:
			time = date.getTime() - compare.getTime();
			result = time / 1000d / 60d / 60d;
			break;
		case DATE_INTERVAL_MINUTE:
			time = date.getTime() / 1000d / 60d;
			result = time - compare.getTime() / 1000d / 60d;
			break;
		case DATE_INTERVAL_MONTH:
			calendar = Calendar.getInstance();
			calendar.setTime(date);
		    time = calendar.get(Calendar.YEAR) * 12d;
		    calendar.setTime(compare);
		    time -= calendar.get(Calendar.YEAR) * 12d;
		    calendar.setTime(date);
		    time += calendar.get(Calendar.MONTH);
		    calendar.setTime(compare);
		    result = time - calendar.get(Calendar.MONTH);
			break;
		case DATE_INTERVAL_SECOND:
			time = date.getTime() - compare.getTime();
			result = time / 1000d;
			break;
		case DATE_INTERVAL_WEEK:
			calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    time = calendar.get(Calendar.YEAR) * 52d;
		    calendar.setTime(compare);
		    time -= calendar.get(Calendar.YEAR) * 52d;
		    calendar.setTime(date);
		    time += calendar.get(Calendar.WEEK_OF_YEAR);
		    calendar.setTime(compare);
		    result = time - calendar.get(Calendar.WEEK_OF_YEAR);
			break;
		case DATE_INTERVAL_YEAR:
			calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    time = calendar.get(Calendar.YEAR);
		    calendar.setTime(compare);
		    result = time - (double)calendar.get(Calendar.YEAR);
			break;
		default:
			break;
		}
		return new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 获取时间差[前面时间和比较时间比,小于比较时间返回负数]
	 * @author yang_hx
	 * @creationDate. 2010-9-1 下午04:36:07 
	 * @param level 返回时间等级(1:返回天;2:返回天-小时;3:返回天-小时-分4:返回天-小时-分-秒;)
	 * @param date 时间
	 * @param compare 比较的时间
	 * @return 时间差(保留两位小数点,小数点以后两位四舍五入)
	 */
	public static String getDateBetween(Integer level, Date date, Date compare) {
		if (date == null || compare == null) return null;
		long s = new BigDecimal(getDateDiff(DATE_INTERVAL_SECOND, date, compare)).setScale(2, BigDecimal.ROUND_HALF_UP).longValue();
		int ss = 1;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;
	   
		long day = s / dd;
		long hour = (s - day * dd) / hh;
		long minute = (s - day * dd - hour * hh) / mi;
		long second = (s - day * dd - hour * hh - minute * mi) / ss;
		String flag =(day < 0 || hour < 0 || minute < 0 || second < 0) ? "-" : "";
		day = Math.abs(day);
		hour = Math.abs(hour);
		minute = Math.abs(minute);
		second = Math.abs(second);
		StringBuilder result = new StringBuilder(flag);
		switch (level) {
		case 1:
			if (day != 0)result.append(day).append("天");
			break;
		case 2:
			if (day != 0)result.append(day).append("天");
			if (hour != 0)result.append(hour).append("小时");
			break;
		case 3:
			if (day != 0)result.append(day).append("天");
			if (hour != 0)result.append(hour).append("小时");
			if (minute != 0)result.append(minute).append("分");
			break;
		case 4:
			if (day != 0)result.append(day).append("天");
			if (hour != 0)result.append(hour).append("小时");
			if (minute != 0)result.append(minute).append("分");
			if (second != 0)result.append(second).append("秒");
			break;
		default:
			break; 
		}
		return result.toString();
	}
	/**
	 * 时间是否是今天
	 * @author yang_hx
	 * @creationDate. 2011-5-4 下午08:24:58 
	 * @param date 时间
	 * @return 布尔
	 */
	public static boolean isToday(Date date) {
		if (date == null) return false;
	    return getNowStringDate().equals(dateFormat(date, DATE_FORMAT_YMD));
	}
	/**
	 * 时间是否合法
	 * @author yang_hx
	 * @creationDate. 2012-6-19 下午01:07:59 
	 * @param date 时间
	 * @param dateFormat 时间格式
	 * @return
	 */
	public static boolean isValidDate(String date, String dateFormat) {
		try {
			new SimpleDateFormat(dateFormat).parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @Title: getUTCTimeStr
	 * @Description: TODO
	 * @param date
	 *            时间
	 * @param dateFormat
	 *            时间格式
	 * @return
	 */
	public static String getUTCTimeStr(Date date, String dateFormat) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
			StringBuffer UTCTimeBuffer = new StringBuffer();
			// 1、取得本地时间：
			Calendar cal = Calendar.getInstance();
			// 2、设置时间
			cal.setTime(date);
			// 3、取得时间偏移量：
			int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
			// 4、取得夏令时差：
			int dstOffset = cal.get(Calendar.DST_OFFSET);
			// 5、从本地时间里扣除这些差量，即可以取得UTC时间：
			cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int minute = cal.get(Calendar.MINUTE);
			int second = cal.get(Calendar.SECOND);
			UTCTimeBuffer.append(year).append("-").append(month).append("-")
					.append(day);
			UTCTimeBuffer.append(" ").append(hour).append(":").append(minute).append(":").append(second);
			Date parseDate = format.parse(UTCTimeBuffer.toString());
			format = new SimpleDateFormat(DATE_FORMAT_YMDTHMSZ);
			return format.format(parseDate);
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e);
		}
		return "";
	} 
	/**  
     * 得到当前的时间，时间格式yyyy-MM-dd  
     * @return  
     */  
    public static String getCurrentDate(){   
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
        return sdf.format(new Date());   
    }
    
    /**  
     * 得到当前的时间,自定义时间格式  
     * y 年 M 月 d 日 H 时 m 分 s 秒  
     * @param dateFormat 输出显示的时间格式  
     * @return  
     */  
    public static String getCurrentDate(String dateFormat){   
        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);   
        return sdf.format(new Date());   
    }   
       
    /**  
     * 日期格式化，默认日期格式yyyy-MM-dd  
     * @param date  
     * @return  
     */  
    public static String getFormatDate(Date date){ 
    	if(date==null)
    		return "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
        return sdf.format(date);   
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
     * 返回当前日期的前一个时间日期，amount为正数 当前时间后的时间 为负数 当前时间前的时间  
     * 默认日期格式yyyy-MM-dd  
     * @param field 日历字段  
     * y 年 M 月 d 日 H 时 m 分 s 秒  
     * @param amount 数量  
     * @return 一个日期  
     */  
    public static String getPreDate(String field,int amount){   
        calendar.setTime(new Date());   
        if(field!=null&&!field.equals("")){   
            if(field.equals("y")){   
                calendar.add(calendar.YEAR, amount);   
            }else if(field.equals("M")){   
                calendar.add(calendar.MONTH, amount);   
            }else if(field.equals("d")){   
                calendar.add(calendar.DAY_OF_MONTH, amount);   
            }else if(field.equals("H")){   
                calendar.add(calendar.HOUR, amount);   
            }   
        }else{   
            return null;   
        }          
        return getFormatDate(calendar.getTime());   
    }   
       
    /**  
     * 某一个日期的前一个日期  
     * @param d,某一个日期  
     * @param field 日历字段  
     * y 年 M 月 d 日 H 时 m 分 s 秒  
     * @param amount 数量  
     * @return 一个日期  
     */  
    public static String getPreDate(Date d,String field,int amount){   
        calendar.setTime(d);   
        if(field!=null&&!field.equals("")){   
            if(field.equals("y")){   
                calendar.add(calendar.YEAR, amount);   
            }else if(field.equals("M")){   
                calendar.add(calendar.MONTH, amount);   
            }else if(field.equals("d")){   
                calendar.add(calendar.DAY_OF_MONTH, amount);   
            }else if(field.equals("H")){   
                calendar.add(calendar.HOUR, amount);   
            }   
        }else{   
            return null;   
        }          
        return getFormatDate(calendar.getTime());   
    }   
       
    /**  
     * 某一个时间的前一个时间  
     * @param date  
     * @return  
     * @throws ParseException   
     */  
    public static String getPreDate(String date) throws ParseException{   
        Date d=new SimpleDateFormat().parse(date);   
        String preD=getPreDate(d,"d",1);   
        Date preDate=new SimpleDateFormat().parse(preD);   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
        return sdf.format(preDate);   
    }
    
    
    
    /**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取本年度的第一天</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @param format
	 * @return
	 */
	public static Date getFirstDayOfYear(String format)
	{
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_YEAR, 1);
		return ca.getTime();
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取一年的最后一个月</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @param format
	 * @return
	 */
	public static Date getLastMonth(String format)
	{
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) - 1);
		return ca.getTime();
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：获取本年的最后一天</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @param format
	 * @return
	 */
	public static Date getLastDayOfYear()
	{
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.MONTH, Calendar.DECEMBER);
		ca.set(Calendar.DAY_OF_MONTH, 30);
		return ca.getTime();
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：Comments</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @param i
	 * @return
	 */
	public static Date subDays(Date source, int i)
	{

		return new Date(source.getTime() - 86400000 * i);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：增加i小时</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @param source
	 * @param i
	 * @return
	 */
	public static Date subHours(Date source, int i)
	{
		return new Date(source.getTime() - 3600000 * i);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：增加分钟</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @param source
	 * @param i
	 * @return
	 */
	public static Date subMinius(Date source, int i)
	{
		return new Date(source.getTime() - 60000 * i);
	}


	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：为时间增加I秒</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @param source
	 * @param i
	 * @return
	 */
	public static Date subSeconds(Date source, int i)
	{
		return new Date(source.getTime() - 1000 * i);
	}
	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：Comments</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @param i
	 * @return
	 */
	public static Date addDays(Date source, int i)
	{

		return new Date(source.getTime() + 86400000 * i);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：增加i小时</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @param source
	 * @param i
	 * @return
	 */
	public static Date addHours(Date source, int i)
	{
		return new Date(source.getTime() + 3600000 * i);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：增加分钟</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子：
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @param source
	 * @param i
	 * @return
	 */
	public static Date addMinius(Date source, int i)
	{
		return new Date(source.getTime() + 60000 * i);
	}


	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：为时间增加I秒</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @param source
	 * @param i
	 * @return
	 */
	public static Date addSeconds(Date source, int i)
	{
		return new Date(source.getTime() + 1000 * i);
	}

	

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：返回指定格式的当前日期格式化字符串</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @param formart
	 * @return
	 */
	public static String format(String format)
	{
		return new SimpleDateFormat(format).format(Calendar.getInstance()
				.getTime());
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：返回指定格式的当前日期格式化字符串</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @param formart
	 * @return
	 */
	public static String format(Date date, String format)
	{
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>可见性原因：需要被其他应用调用</li>
	 * <li>目的：格式化字符串为日期</li>
	 * <li>适用的前提条件:</li>
	 * <li>后置条件：</li>
	 * <li>例外处理：无 </li>
	 * <li>已知问题：</li>
	 * <li>调用的例子： </li>
	 * </ul>
	 * </p>
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date parse(String date, String format)
	{
		try
		{
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * 获得与某个日期间隔多少天的日期
	 * @param date
	 * @param interval
	 * @return
	 */
    public static Date getDateInterval(Date date,int interval){
    	Calendar calendar=Calendar.getInstance(); 
		calendar.setTime(date); 
		int day=calendar.get(Calendar.DATE); 
		calendar.set(Calendar.DATE,day+interval); 
        return calendar.getTime();
    }

	/**
	 * 
	 * @discription 7天前
	 * @author yang_hx       
	 * @created 2015-9-23 上午9:58:16     
	 * @return
	 */
	public static String befroe7Fomat(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		
		return DateFormatUtils.format(cal, "yyyy-MM-dd");
	}

	/**
	 * 
	 * @discription 当前日期
	 * @author yang_hx       
	 * @created 2015-9-23 上午9:58:26     
	 * @return
	 */
	public static String nowFomat(){
		Calendar cal = Calendar.getInstance();
		
		return DateFormatUtils.format(cal, "yyyy-MM-dd");
	}
	
	/**
	 * 
	 * @discription 一个月之前
	 * @author yang_hx       
	 * @created 2015-9-23 上午9:58:16     
	 * @return
	 */
	public static String beforeMonthFomat(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		
		return DateFormatUtils.format(cal, "yyyy-MM-dd");
	}
	
	public static long getLongTime(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date.getTime();
	}
	public static long getLongTime(String str,String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = new Date();
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date.getTime();
	}
	
	public static void main(String[] args) {
//		System.out.println(getDateBetween(Integer.valueOf(4), dateFormat("2011-05-4 20:02:05", "yyyy-MM-dd HH:mm:ss"), new Date()));
// 		System.out.println(getDateDiff(DATE_INTERVAL_DAY, dateFormat("2010-11-16 10:20:38", DATE_FORMAT_YMDHMS), dateFormat("2010-11-12 23:32:53", DATE_FORMAT_YMDHMS)));
// 		System.out.println(getDateBetween(4, dateFormat("2010-11-16 10:20:38", DATE_FORMAT_YMDHMS), dateFormat("2010-11-12 23:32:53", DATE_FORMAT_YMDHMS)));
// 		System.out.println(getWeekOfDate(dateFormat("2011-1-10 12:37:52", DATE_FORMAT_YMDHMS)));
//		System.out.println(isValidDate("2012-05-08 12:00:00", "yyyy-MM-dd HH:mm:ss"));
//		Date timestamp = dateFormat("2012-06-19 13:33:02", "yyyy-MM-dd HH:mm:ss");;
//		double dateDiff = getDateDiff(DATE_INTERVAL_MINUTE, new Date(), timestamp);
//		System.out.println("dateDiff-->" + dateDiff);
//		if (dateDiff > 6) {
//			System.out.println("超时");
//		}
//		System.out.println(DateUtil.dateFormat(new Date(), "yyyy年MM月dd日HH时mm分"));
//		// 取到明天的今天
//		Date nextYear = DateUtil.dateAdd(DateUtil.DATE_INTERVAL_YEAR, new Date(), 1);
//		System.out.println(DateUtil.dateFormat(nextYear, DateUtil.DATE_FORMAT_YMDHMS));
//		System.out.println(getNowStringTime());
//		System.out.println(getYesterdayDate());
//		System.out.println(getMonthFirst());
//		System.out.println(getNextMonthFirst());
		LOG.info(getUTCTimeStr(new Date(), DATE_FORMAT_YMDTHMSZ));
	}
}