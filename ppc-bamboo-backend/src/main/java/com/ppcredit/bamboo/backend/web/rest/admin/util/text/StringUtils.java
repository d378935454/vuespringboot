/*
 * @(#)java 2014-3-21下午01:49:57
 * Copyright 2013 sinovatech, Inc. All rights reserved.
 */
package com.ppcredit.bamboo.backend.web.rest.admin.util.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类,提供常用字符串处理方法
 * <ul>
 * <li>
 * <b>修改历史：</b><br/>
 * <p>
 * [2014-3-21下午01:49:57]yang_hx<br/>
 * TODO
 * </p>
 * </li>
 * </ul>
 */

public class StringUtils extends org.apache.commons.lang.StringUtils{
	
	/***
	 * 把NULL转化为""
	 * @param str
	 * @return
	 */
	public static String isNullToString(String str) {
		return isEmpty(str) ? "" : str;
	}
	
	/**
	 * 验证字符串时候为空
	 * @author yang_hx
	 * @creationDate. 2010-12-3 下午04:47:52 
	 * @param str 字符串
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.equals("")) ? true : false;
	}

	/**
	 * 验证字符串非空
	 * @author yang_hx
	 * @creationDate. 2010-12-3 下午04:48:16 
	 * @param str 字符串
	 * @return boolean
	 */
	public static boolean isNotEmpty(String str) {
		return (str == null || str.equals("")) ? false : true;
	}

	/**
	 * 
	 * @discription 校验2个字符串都不为空
	 * @author yang_hx       
	 * @created 2015-9-1 下午3:46:36     
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isNotEmpty(String str1,String str2){
		if(org.apache.commons.lang.StringUtils.isNotBlank(str1) && org.apache.commons.lang.StringUtils.isNotBlank(str2)){
			return true;
		}
		return false;
	}
	/**
	 * 获得带中文的字符串长度
	 * @author yang_hx
	 * @creationDate. 2010-11-2 上午11:36:30 
	 * @param str 字符串
	 * @return 字符串长度
	 */
	public static long getChineseTextLen(String str) {
		if (isEmpty(str)) return 0;
		return str.replaceAll("[^\\x00-\\xff]", "00").length();
	}

	/**
	 * 截取带中文的文本长度
	 * @author yang_hx
	 * @creationDate. 2010-11-2 上午11:37:35 
	 * @param str 字符串
	 * @param len 长度
	 * @param ext 截断后添加的标识(一般传省略号)
	 * @return 字符串
	 */
	public static String subChineseText(String str, int len, String ext) {
		if (isEmpty(str)) return "";
		if (getChineseTextLen(str) <= len) return str;
		int m = (int) Math.floor(len / 2);
		int length = str.length();
		long subLen = 0;
		for(int i = m; i<length; i++) { 
			subLen = getChineseTextLen(str.substring(0, i));
			if (subLen >= len) {
				StringBuilder result = new StringBuilder(str.substring(0, (subLen>len) ? i - 1 : i));
				if (isNotEmpty(ext)) {
					result.append(ext);
				}
				return result.toString();
			}
		}
		return str; 
	}

	/**
	 * 文本转成全角字符串
	 * @author yang_hx
	 * @creationDate. 2010-11-2 下午05:29:16 
	 * @param str 待转换的字符串
	 * @return 全角字符串
	 */
	public static String text2sbcCase(String str) {
		if (isEmpty(str)) return "";
		char[] c = str.toCharArray();
		int len = c.length;
		for (int i = 0; i < len; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	/**
	 * 文本转成半角字符串
	 * @author yang_hx
	 * @creationDate. 2010-11-2 下午05:28:31 
	 * @param str 待转换的字符串
	 * @return 半角字符串
	 */
	public static String text2dbcCase(String str) {
		if (isEmpty(str)) return "";
		char[] c = str.toCharArray();
		int len = c.length;
		for (int i = 0; i < len; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	public static boolean isNumeric(String str){ 
		Pattern pattern = Pattern.compile("[0-9]*"); 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false; 
		} 
		return true; 
	}
	/**
	 * 判断多个字符串是否为空
	 * @author yang_hx
	 * @creationDate. 2010-11-2 下午05:38:31 
	 * @param strs 待判断字符串
	 * @return boolean
	 */
	public static boolean isEmpty(String... strs ){
		
		if(strs == null) return true;
		
		for (String str : strs) {
			if(str == null || str.equals("")){
				return true;
			}
		}
		return false;
	}
	
	public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuffer(str).reverse().toString();
    }
	
    public static boolean isContainCh(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
    
    /**
     * 不能全是相同的数字或者字母（如：000000、111111、aaaaaa）
     * @param numOrStr
     * @return
     */
  	public static boolean equalStr(String numOrStr){
  		boolean flag = true;
  		char str = numOrStr.charAt(0);
  		for (int i = 0; i < numOrStr.length(); i++) {
  			if (str != numOrStr.charAt(i)) {
  				flag = false;
  				break;
  			}
  		}
  		return flag;
  	}
  	/**
  	 * 不能是连续的数字--递增（如：123456、12345678）
  	 * @param numOrStr
  	 * @return
  	 */
  	public static boolean isNumericASC(String numOrStr){
  		boolean flag = true;//如果全是连续数字返回true
  		boolean isNumeric = true;//如果全是数字返回true
  		for (int i = 0; i < numOrStr.length(); i++) {
  			if (!Character.isDigit(numOrStr.charAt(i))) {
  				isNumeric = false;
  				break;
  			}
  		}
  		if (isNumeric) {//如果全是数字则执行是否连续数字判断
  			for (int i = 0; i < numOrStr.length(); i++) {
  				if (i > 0) {//判断如123456
  					int num = Integer.parseInt(numOrStr.charAt(i)+"");
  					int num_ = Integer.parseInt(numOrStr.charAt(i-1)+"")+1;
  					if (num != num_) {
  						flag = false;
  						break;
  					}
  				}
  			}
  		} else {
  			flag = false;
  		}
  		return flag;
  	}
  	/**
  	 * 不能是连续的数字--递减（如：987654、876543）
  	 * @param numOrStr
  	 * @return
  	 */
  	public static boolean isNumericDESC(String numOrStr) {
  		boolean flag = true;//如果全是连续数字返回true
  		boolean isNumeric = true;//如果全是数字返回true
  		for (int i = 0; i < numOrStr.length(); i++) {
  			if (!Character.isDigit(numOrStr.charAt(i))) {
  				isNumeric = false;
  				break;
  			}
  		}
  		if (isNumeric) {//如果全是数字则执行是否连续数字判断
  			for (int i = 0; i < numOrStr.length(); i++) {
  				if (i > 0) {//判断如654321
  					int num = Integer.parseInt(numOrStr.charAt(i)+"");
  					int num_ = Integer.parseInt(numOrStr.charAt(i-1)+"")-1;
  					if (num != num_) {
  						flag = false;
  						break;
  					}
  				}
  			}
  		} else {
  			flag = false;
  		}
  		return flag;
  	}
    
	/**
	 * 测试
	 * @author yang_hx
	 * @creationDate. 2010-12-3 下午04:51:12 
	 * @param args
	 */
	public static void main(String[] args) {
		// String str = "";
		// System.out.println(isEmpty(str));
		// System.out.println(isNotEmpty(str));
		// System.out.println(getChineseTextLen("123中文"));
		System.out.println(subChineseText("1我们答复的萨芬的", 0, "..."));
		// String sbcStr = text2sbcCase("123");
		// String sbcStr2 =
		// text2dbcCase("！＃＠＄＠＃＄％＾＆＊（）｛｝４５４８３８５８８９ＷＺＥＸＨＪＫＬ：＞？ｄｆｇｈｊｋｌ；／");
		// System.out.println(sbcStr);
		// System.out.println(sbcStr2);
		// System.out.println(text2dbcCase(sbcStr));
		// System.out.println(getRandomString(6));
	}
}
