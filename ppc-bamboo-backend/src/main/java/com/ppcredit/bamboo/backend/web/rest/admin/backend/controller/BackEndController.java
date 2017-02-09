package com.ppcredit.bamboo.backend.web.rest.admin.backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/backend")
public class BackEndController {

	private static Logger log = LoggerFactory.getLogger(BackEndController.class); // 日志       

	//菜单
	@RequestMapping("menu_manage")
	public String menuManage(HttpServletRequest request) {
		log.info("菜单报告>>>>");
		return "backend/menu_manage";
	}
	
}
