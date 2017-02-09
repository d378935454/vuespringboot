package com.ppcredit.bamboo.backend.web.rest.admin.credit.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.ppcredit.bamboo.backend.web.rest.admin.credit.service.APIUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author huyongjin
 * @created 2015-10-22 下午1:14:26
 */
@Controller
@RequestMapping("/system/creditreport")
public class ApiCreditReportController {
	private static Logger log = LoggerFactory.getLogger(ApiCreditReportController.class); // 日志                 
	
	@Inject
	private APIUtilService myAPIUtilService;

	/**
	 * 征信报告页面
	 * @author huyongjin
	 * @creationDate. 2015-10-23 下午10:03:51 
	 * @param request
	 * @param response
	 * @return 征信报告页面
	 * @throws Exception
	 */
	@RequestMapping("credit_report")
	public String creditReport(HttpServletRequest request) {
//		SSOUserDTO sessionUser = AdminUtil.getUserSession(request);		
//		List<APIKeySetDTO>  list = myAPIUtilService.findAPIKeySetList(sessionUser);
//		request.setAttribute("keyList", list);
		log.info("征信报告>>>>");
		return "api/credit_report";
	}
	
	//用户
	@RequestMapping("user_manage")
	public String userManage(HttpServletRequest request) {
		log.info("用户>>>>");
		return "system/user_manage";
	}
	
	//appkey
	@RequestMapping("appkey_manage")
	public String appkeyManage(HttpServletRequest request) {
		log.info("用户>>>>");
		return "system/user_manage";
	}
	
	//接口
	@RequestMapping("api_manage")
	public String apiManage(HttpServletRequest request) {
		log.info("接口报告>>>>");
		return "api/api_manage";
	}
	//接口(模型)
	@RequestMapping("interModel_manage")
	public String interModelManage(HttpServletRequest request) {
		log.info("接口报告>>>>");
		return "api/api_manage";
	}
	
	//菜单
	@RequestMapping("menu_manage")
	public String credit(HttpServletRequest request) {
		log.info("菜单报告>>>>");
		return "system/menu_manage";
	}




	
}