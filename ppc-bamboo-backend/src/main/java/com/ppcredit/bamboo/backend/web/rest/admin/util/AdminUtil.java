package com.ppcredit.bamboo.backend.web.rest.admin.util;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOResFuncDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleFuncDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleDTO;
import org.apache.commons.lang.time.DateFormatUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: AdminUtil.java
 * Description: 工具类
 * @author yang_hx
 * @created 2015-9-1 下午12:54:34
 */
public class AdminUtil {

	public static final String USER_SESSION = "ppc_user_session";// 用户session
	public static final String ROLE_SESSION = "ppc_role_session";// 用户session
	public static final String ROLE_FUNC_SESSION = "ppc_role_func_session";// 用户session
	public static final String FUNC_SESSION = "ppc_func_session";// 用户session
	public static final String USER_COOKIE_TOKEN = "u_c_t";// 用户cookie
	public static final int MAX_AGE = 60 * 15;// 秒
	public static final String SEVEN_FREE_LOGIN = "free_login_7";// 7天免登
	public static final String TREE_VIEW = "tree_view";// 树形菜单
	public static final String USER_HOME = "first";// 树形菜单
	public static final String REALNAME = "real_name"; // 选中用户名称
	public static final String APPKEY = "app_key"; // app_key

	/**
	 * 设置用户session
	 * 
	 * @discription 在此输入一句话描述作用
	 * @author yang_hx
	 * @created 2015-9-1 下午1:17:17
	 * @param request
	 * @param s
	 */
	public static void setUserSession(HttpServletRequest request, SSOUserDTO s) {
		request.getSession().setAttribute(USER_SESSION, s);
	}

	/**
	 * @discription 得到用户Session
	 * @author yang_hx
	 * @created 2015-9-1 下午1:01:41
	 * @param request
	 * @return
	 */
	public static SSOUserDTO getUserSession(HttpServletRequest request) {
		SSOUserDTO s = (SSOUserDTO) request.getSession().getAttribute(USER_SESSION);
		if (s != null)
			return s;
		return null;
	}

	/**
	 * @discription 删除用户session
	 * @author yang_hx
	 * @created 2015-9-1 下午1:23:55
	 * @param request
	 */
	public static void removeUserSession(HttpServletRequest request) {
		request.getSession().setAttribute(USER_SESSION, null);
	}

	/**
	 * @discription 得到树形菜单
	 * @author yang_hx
	 * @created 2015-9-1 下午1:51:33
	 * @param request
	 * @return
	 */
	public static String getTreeView(HttpServletRequest request) {
		String treeView = (String) request.getSession().getAttribute(TREE_VIEW);
		if (treeView == null)
			return null;
		return treeView;
	}

	/**
	 * @discription 树形菜单注入session
	 * @author yang_hx
	 * @created 2015-9-1 下午3:29:19
	 * @param request
	 * @param s
	 */
	public static void setTreeView(HttpServletRequest request, String s) {
		request.getSession().setAttribute(TREE_VIEW, s);
	}

	/**
	 * 移除树形菜单
	 * 
	 * @param request
	 */
	public static void removeTreeView(HttpServletRequest request) {
		request.getSession().setAttribute(TREE_VIEW, null);
	}

	/**
	 * @discription 设置用户cookie
	 * @author yang_hx
	 * @created 2015-9-1 下午1:09:17
	 * @param response
	 * @param u
	 * @param maxAge
	 */
	public static void setUserCookie(HttpServletResponse response, SSOUserDTO u, int maxAge) {
		if (maxAge < 0) {
			maxAge = MAX_AGE;
		}
		addCookie(response, USER_COOKIE_TOKEN, u.getId(), maxAge);
	}

	/**
	 * @discription 从Cookie中拿到用户的token
	 * @author yang_hx
	 * @created 2015-9-1 下午1:15:49
	 * @param request
	 * @return
	 */
	public static String getUserCookieToken(HttpServletRequest request) {
		Cookie cookie = getCookieByName(request, USER_COOKIE_TOKEN);
		if (cookie == null)
			return null;

		return cookie.getValue();
	}

	/**
	 * @discription 删除用户登录Cookie
	 * @author yang_hx
	 * @created 2015-9-1 下午1:22:08
	 * @param response
	 */
	public static void removeUserCookie(HttpServletResponse response) {
		addCookie(response, USER_COOKIE_TOKEN, null, 0);
	}

	/**
	 * 7天免登移除
	 * 
	 * @param response
	 */
	public static void removeFree7Login(HttpServletResponse response) {
		addCookie(response, SEVEN_FREE_LOGIN, null, 0);
	}

	/**
	 * @discription 是否勾选了7天免登录
	 * @author yang_hx
	 * @created 2015-9-1 下午1:27:04
	 * @param request
	 * @return
	 */
	public static boolean is7freeLogin(HttpServletRequest request) {
		Cookie cookie = getCookieByName(request, SEVEN_FREE_LOGIN);
		if (cookie == null)
			return false;

		if (cookie.getValue().equals("1"))
			return true;

		return false;
	}

	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param name
	 *            cookie名字
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            cookie生命周期 以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge >= 0)
			cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	/**
	 * 拿表单元素
	 * 
	 * @param request
	 * @return
	 */
	public static String getParams(HttpServletRequest request, String name) {
		return request.getParameter(name) == null ? "" : request.getParameter(name).trim();
	}

	/**
	 * 拿当前日期
	 * 
	 * @return
	 */
	public static String now() {
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  EEEE");

		return sdf.format(cal.getTime());
	}

	@SuppressWarnings("unchecked")
	public static List<SSORoleDTO> getRoleSession(HttpServletRequest request) {
		List<SSORoleDTO> s = (List<SSORoleDTO>) request.getSession().getAttribute(ROLE_SESSION);
		if (s != null)
			return s;
		return null;
	}

	public static void setRoleSession(HttpServletRequest request, List<SSORoleDTO> s) {
		request.getSession().setAttribute(ROLE_SESSION, s);
	}

	public static void removeRoleSession(HttpServletRequest request) {
		request.getSession().setAttribute(ROLE_SESSION, null);
	}

	@SuppressWarnings("unchecked")
	public static List<SSORoleFuncDTO> getRoleFuncsSession(HttpServletRequest request) {
		List<SSORoleFuncDTO> s = (List<SSORoleFuncDTO>) request.getSession().getAttribute(ROLE_FUNC_SESSION);
		if (s != null)
			return s;
		return null;
	}

	public static void setRoleFuncsSession(HttpServletRequest request, List<SSORoleFuncDTO> s) {
		request.getSession().setAttribute(ROLE_FUNC_SESSION, s);
	}

	public static void setFuncsSession(HttpServletRequest request, Map<String, SSOResFuncDTO> s) {
		request.getSession().setAttribute(FUNC_SESSION, s);
	}

	public static void removeFuncsSession(HttpServletRequest request) {
		request.getSession().setAttribute(FUNC_SESSION, null);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, SSOResFuncDTO> getFuncsSession(HttpServletRequest request) {
		Map<String, SSOResFuncDTO> s = (Map<String, SSOResFuncDTO>) request.getSession().getAttribute(FUNC_SESSION);
		if (s != null)
			return s;
		return null;
	}

	public static void removeRoleFuncsSession(HttpServletRequest request) {
		request.getSession().setAttribute(ROLE_FUNC_SESSION, null);
	}

	/**
	 * @discription 释放所有用户对象
	 * @author yang_hx
	 * @created 2015-9-23 上午9:57:49
	 * @param request
	 * @param response
	 */
	public static void removeUserAllSession(HttpServletRequest request, HttpServletResponse response) {
		AdminUtil.removeUserSession(request);
		AdminUtil.removeTreeView(request);
		AdminUtil.removeUserCookie(response);
		AdminUtil.removeFree7Login(response);
		AdminUtil.removeRoleSession(request);
		AdminUtil.removeRoleFuncsSession(request);
		AdminUtil.removeFuncsSession(request);
	}

	
	/**
	 * 设置用户appkey
	 */
	public static void setAppKey(HttpServletRequest request, String realName, String appKey) {
		request.getSession().setAttribute(REALNAME, realName);
		request.getSession().setAttribute(APPKEY, appKey);
	}

	/**
	 * 获取用户appkey
	 */
	public static String getAppKey(HttpServletRequest request) {
		String s = (String) request.getSession().getAttribute(APPKEY);
		if (s != null)
			return s;
		return "";
	}
	/**
	 * 获取选中用户名称
	 */
	public static String getRealName(HttpServletRequest request) {
		String s = (String) request.getSession().getAttribute(REALNAME);
		if (s != null)
			return s;
		return "";
	}

	/**
	 * 删除用户appkey
	 */
	public static void removeAppKey(HttpServletRequest request) {
		request.getSession().setAttribute(REALNAME, null);
		request.getSession().setAttribute(APPKEY, null);
	}

	/**
	 * @discription 7天前
	 * @author yang_hx
	 * @created 2015-9-23 上午9:58:16
	 * @return
	 */
	public static String befroe7Fomat() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);

		return DateFormatUtils.format(cal, "yyyy-MM-dd");
	}

	/**
	 * @discription 7天前
	 * @author yang_hx
	 * @created 2015-9-23 上午9:58:16
	 * @return
	 */
	public static String befroe7Fomat2() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);

		return DateFormatUtils.format(cal, "yyyy-MM-dd 00:00");
	}

	/**
	 * @discription 当前日期
	 * @author yang_hx
	 * @created 2015-9-23 上午9:58:26
	 * @return
	 */
	public static String nowFomat() {
		Calendar cal = Calendar.getInstance();

		return DateFormatUtils.format(cal, "yyyy-MM-dd");
	}

	/**
	 * @discription 一个月之前
	 * @author yang_hx
	 * @created 2015-9-23 上午9:58:16
	 * @return
	 */
	public static String beforeMonthFomat() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);

		return DateFormatUtils.format(cal, "yyyy-MM-dd");
	}

	public static String beforeMonthFomat2() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);

		return DateFormatUtils.format(cal, "yyyy-MM-dd 00:00");
	}

	public static String beforeMonthFomatV2() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);

		return DateFormatUtils.format(cal, "yyyy-MM-dd HH:mm");
	}

	public static String nowFomatV2() {
		Calendar cal = Calendar.getInstance();

		return DateFormatUtils.format(cal, "yyyy-MM-dd HH:mm");
	}

	public static String nowFomatV3() {
		Calendar cal = Calendar.getInstance();

		return DateFormatUtils.format(cal, "yyyy-MM-dd 00:00");
	}

	public static String beforeDayFomatV2() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);

		return DateFormatUtils.format(cal, "yyyy-MM-dd HH:mm");
	}

	public static String nowFomatV4() {
		Calendar cal = Calendar.getInstance();

		return DateFormatUtils.format(cal, "yyyy-MM-dd 23:59");
	}

	// sss
	public static String beforeDayStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);

		return DateFormatUtils.format(cal, "yyyy-MM-dd HH:mm");
	}

	public static String beforeDayEnd() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);

		return DateFormatUtils.format(cal, "yyyy-MM-dd HH:mm");
	}

	// 日志管理-京东认证
	public static String dayStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);

		return DateFormatUtils.format(cal, "yyyy-MM-dd HH:mm");
	}

	public static String dayEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);

		return DateFormatUtils.format(cal, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 获取前一天
	 * 
	 * @return
	 */
	public static String beforeDayFomat() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);

		return DateFormatUtils.format(cal, "yyyy-MM-dd");
	}

	/**
	 * 在今天的基础上，获取任意一天。
	 * 
	 * @return
	 */
	public static String addDayFomat(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);

		return DateFormatUtils.format(cal, "yyyy-MM-dd");
	}

	/**
	 * 获取当前时间前一天的一个月前
	 * 
	 * @return
	 */
	public static String yesterdayBeforeMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.add(Calendar.MONTH, -1);

		return DateFormatUtils.format(cal, "yyyy-MM-dd");
	}

	/**
	 * 获取当前时间后一个月
	 * 
	 * @return
	 */
	public static String afterOneMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);

		return DateFormatUtils.format(cal, "yyyy-MM-dd");
	}

	/**
	 * 获取当前开始时间，精确到分秒
	 * 
	 * @return
	 */
	public static String nowFomatTimeStart() {
		Calendar cal = Calendar.getInstance();

		return DateFormatUtils.format(cal, "yyyy-MM-dd 00:00:00");
	}

	/**
	 * 获取当前结束时间，精确到分秒
	 * 
	 * @return
	 */
	public static String nowFomatTimeEnd() {
		Calendar cal = Calendar.getInstance();

		return DateFormatUtils.format(cal, "yyyy-MM-dd 23:59:59");
	}

}
