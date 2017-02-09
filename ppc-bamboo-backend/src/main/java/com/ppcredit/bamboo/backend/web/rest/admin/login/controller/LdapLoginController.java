package com.ppcredit.bamboo.backend.web.rest.admin.login.controller;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.service.SSOLoginService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.AdminUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.util.redis.BaseRedisSupport;
import com.ppcredit.bamboo.backend.web.rest.admin.util.response.ResponseBody;
import com.ppcredit.bamboo.backend.web.rest.admin.util.text.StringUtils;
import com.ppcredit.bamboo.backend.web.rest.admin.util.web.WebUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserRoleDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.service.SSOResFuncService;
import com.ppcredit.bamboo.backend.web.rest.admin.login.service.SSOUserRoleService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.ConstantsUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LdapLoginController  {
	
	private static Logger log = LoggerFactory.getLogger(LdapLoginController.class); // 日志
	
	@Inject
	private BaseRedisSupport myBaseRedisSupport;

//	@Inject
//	private UserLdapServiceImpl myUserLoginService;

	@Inject
	private SSOLoginService mySSOLoginService;

	@Inject
	private SSOResFuncService mySSOResFuncService;
	
	@Inject
	private SSOUserRoleService mySSOUserRoleService;

	@RequestMapping("go_login")
	public String goLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("session时长：" + request.getSession().getMaxInactiveInterval());
		System.out.println("欢迎使用ldap登录");

		return "login";
	}

	/**
	 * @discription 用户登录
	 * AJAX提交
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("ldapsubmit")
	public void ldapsubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ldapName = request.getParameter("ldap_name")== null ? "":request.getParameter("ldap_name").trim();
		String ldapPassWord = request.getParameter("ldap_password")== null ? "":request.getParameter("ldap_password").trim();
		String ldapCaptcha = request.getParameter("ldap_captcha")== null ? "":request.getParameter("ldap_captcha").trim();
		log.info(">>>>>>姓名>>>>>>>" + ldapName);
		log.info(">>>>>>密码>>>>>>>" + ldapPassWord);
		log.info(">>>>>>页面验证码>>>>>>>" + ldapCaptcha)   ;
		ResponseBody body = new ResponseBody();
		if (!StringUtils.isNotEmpty(ldapName, ldapPassWord)) {
			log.info("用户名或密码不能为空>>>>{}", ldapName,ldapPassWord);
			body.setRespCode("error");
			body.setRespMsg("用户名或密码不能为空");
			WebUtil.write(response, body.getBody(), ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
			return;
		}
		/*CaptchaServiceFactory.CaptchaServiceType cst = CaptchaServiceFactory.CaptchaServiceType.DEFAULT_CAPTCHASERVICE;
		DefaultCaptchaService captchaService = (DefaultCaptchaService) CaptchaServiceFactory.getCaptchaService(cst);
		captchaService.setReq(request.getSession());
		try {
			if (!captchaService.verifyCaptcha(ldapCaptcha)) {
				body.setRespCode("error");
				body.setRespMsg("验证码错误");
				WebUtil.write(response, body.getBody(), ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
				return;
			}
		} catch (CaptchaTimeoutException e) {
			body.setRespCode("error");
			body.setRespMsg("验证码已过期");
			WebUtil.write(response, body.getBody(), ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
			return;
		}*/
		try {
			
//			String errorLength = myBaseRedisSupport.get(ldapName);
			/*boolean isLoginSuccess = myUserLoginService.Auth(ldapName, ldapPassWord);
			if (isLoginSuccess == false) {
				if (errorLength == null) {
					errorLength = "0";
				}
				int count = Integer.parseInt(errorLength) + 1;
				myBaseRedisSupport.set(ldapName, count + "");
				body.setRespCode("error");
				if (count >= 3 && count < 6) {
					int left = 6 - count;
					log.info("您已失败" + count + "次，剩余" + left + "次登录机会>>>>{}", ldapName);
					body.setRespMsg("您已失败" + count + "次，剩余" + left + "次登录机会");
				} else if (count == 6) {
					log.info("账号已被冻结>>>>{},半小时后再重试", ldapName);
					body.setRespMsg("您的账号已被冻结");
					myBaseRedisSupport.set(ldapName, count + "", 30 * 60L);
				} else {
					log.info("用户名或密码错误>>>>{}", ldapName);
					body.setRespMsg("用户名或密码错误");
				}
				WebUtil.write(response, body.getBody(), ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
				return;
			}*/
			/**
			 * 新增用户的代码
			 * String roleid = request.getParameter("role");
			 * SSORoleDTO role = new SSORoleDTO();
			 * role.setId(roleid);
			 * //密码加密
			 * dto.setUserLoginPassword(DigestUtils.md5Hex(dto.getUserLoginPassword()
			 * .getBytes(ConstantsUtil.SYSTEM_DEFAULT_ENCODING)));
			 * SSOUserRoleDTO ur = new SSOUserRoleDTO();
			 * ur.setRole(role);
			 * ur.setUser(dto);
			 * if (StringUtils.equals(dto.getIsAllcustomer(), "1")) {
			 * mySSOLoginService.saveUser(ur);
			 */
			// 新增用户
			SSOUserDTO u = mySSOLoginService.findByuserLdapName(ldapName);
			if (u == null) {
				List<SSORoleDTO> roleList = mySSOResFuncService.findByName("征信部人员");
				if (roleList != null && roleList.size() > 0) {
					SSORoleDTO role = new SSORoleDTO();
					role.setId(roleList.get(0).getId());

					u = new SSOUserDTO();
					// u.setId(UUID.getUUID());
					u.setUserDesc(ldapName);
					u.setUserRealName(ldapName);
					u.setUserLoginName(ldapName + "@ppcredit.com");
					u.setUserLoginPassword(DigestUtils.md5Hex(ldapPassWord.getBytes(ConstantsUtil.SYSTEM_DEFAULT_ENCODING)));
					u.setUserEmail(ldapName + "@ppcredit.com");
					u.setUserStatus(ConstantsUtil.STATUS_SUCESS);
					u.setUserLdapName(ldapName);
					u.setIsAllcustomer("1");
					SSOUserRoleDTO ur = new SSOUserRoleDTO();
					ur.setRole(role);
					ur.setUser(u);
//					mySSOLoginService.save(ur);
					mySSOUserRoleService.save(ur);
				}
			} else if (StringUtils.isBlank(u.getUserLdapName())) {
				u.setUserLdapName(ldapName);
				mySSOLoginService.save(u);
			}
			// 存入session
			AdminUtil.setUserSession(request, u);
//			myBaseRedisSupport.delete(ldapName);
			body.setRespCode("success");
			System.out.println(request.getContextPath());
			body.setRespMsg("../../");
			body.setRespBody("登录成功");
			WebUtil.write(response, body.getBody(), ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
			return ;
		} catch (Exception e) {
			body.setRespCode("error");
			body.setRespMsg("系统错误，请与管理员联系");
			WebUtil.write(response, body.getBody(), ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
			return;
		}
	}
	
	/**
	 * 注销
	 * ajax
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("login_out")
	public void loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminUtil.removeUserAllSession(request, response);
		ResponseBody body = new ResponseBody();
		body.setRespCode("success");
		body.setRespMsg(request.getContextPath() + "/login/go_login");
		body.setRespBody("注销成功");
		WebUtil.write(response, body.getBody(), ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
		return;
	}
	
}
