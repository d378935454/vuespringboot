package com.ppcredit.bamboo.backend.web.rest.admin.response.controller;

import com.ppcredit.bamboo.backend.web.rest.admin.backend.controller.BackEndController;
import com.ppcredit.bamboo.backend.web.rest.admin.request.dto.ProcessManageDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.response.service.ResponseManageService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.AdminUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import com.ppcredit.bamboo.backend.web.rest.admin.util.response.ResponseBody;
import com.ppcredit.bamboo.backend.web.rest.admin.util.text.StringUtils;
import com.ppcredit.bamboo.backend.web.rest.admin.util.web.WebUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.response.dto.APIReturnInfo;
import com.ppcredit.bamboo.backend.web.rest.admin.util.ConstantsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务配置（接口配置）
 * Date: 2016-11-24 14:50:36
 */
@Controller
@RequestMapping("/admin/response")
public class ResponseManageController {

	private static Logger logger = LoggerFactory.getLogger(BackEndController.class); // 日志

	@Inject
	private ResponseManageService myResponseManageService;

	/**
	 * 页面初始化
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("reback_manage")
	public String interModelManage(HttpServletRequest request, Map<String, Object> map) {
		logger.info("初始化服务/模型>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		List<ProcessManageDTO> apiList = myResponseManageService.queryApiList(appKey,"0");
		map.put("apiList", apiList);
		APIReturnInfo callback = myResponseManageService.getCallbackInfoByAppkey(appKey);
		map.put("callbackInfo", callback);
		return "reback_manage";
	}

	@RequestMapping("query_apiList")
	@org.springframework.web.bind.annotation.ResponseBody
	public Object queryApiList(HttpServletRequest request, HttpServletResponse response) {
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String type = request.getParameter("type");
		Map<String,Object> map = new HashMap<String,Object>();
		List<ProcessManageDTO> apiList = myResponseManageService.queryApiList(appKey,type);
		map.put("apiList", apiList);
		return map;
	}
	
	/**
	 * 根据接口名查询
	 * 
	 * @param request
	 */
	@RequestMapping("query")
	@org.springframework.web.bind.annotation.ResponseBody
	public Object query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("查询服务/模型>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String type = request.getParameter("type");
		String api = request.getParameter("api");
		int offset = Integer.parseInt(request.getParameter("iDisplayStart")); // 起始点
		int pagesize = Integer.parseInt(request.getParameter("iDisplayLength")); // 每页数

		Map<String, Object> map = new HashMap<>();
		Pager pager = myResponseManageService.query(appKey, type, api, offset, pagesize);
		map.put("aaData", pager.getResultList());
		map.put("iTotalRecords", pager.getTotalNum());
		map.put("iTotalDisplayRecords", pager.getTotalNum());
		return map;
//		String json = JSON.toJSONString(map);
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().print(json);
	}

	/**
	 * 保存服务和模型
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("addCallback")
	public void addApi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("保存服务/模型>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String callback_type = request.getParameter("callback_type"); // API类型
		String callback_url = request.getParameter("callback_url"); // API名称
		String notify_type = request.getParameter("notify_type"); // 参数
		String notify_url = request.getParameter("notify_url"); // 参数名字
		String id = request.getParameter("id");

		APIReturnInfo callbackInfo = new APIReturnInfo();
		// callbackInfo.setId(new BigInteger("1"));
		callbackInfo.setAppKey(appKey);
		callbackInfo.setCallback_type(callback_type);
		callbackInfo.setCallback_url(callback_url);
		callbackInfo.setNotify_type(notify_type);
		callbackInfo.setNotify_url(notify_url);
		if (StringUtils.isNotBlank(id)) {
			callbackInfo.setId(new BigInteger(id));
		}

		ResponseBody body = new ResponseBody();
		try {
			myResponseManageService.addCallback(callbackInfo);
			body = new ResponseBody("success", "保存成功", "保存成功");
		} catch (Exception e) {
			body = new ResponseBody("error", "保存失败", "保存失败");
			e.printStackTrace();
		}
		WebUtil.write(response, body.getBody(), ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
	}

}
