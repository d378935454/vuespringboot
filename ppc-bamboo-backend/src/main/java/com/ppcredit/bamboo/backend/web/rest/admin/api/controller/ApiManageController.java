package com.ppcredit.bamboo.backend.web.rest.admin.api.controller;

import com.ppcredit.bamboo.backend.web.rest.admin.api.service.ApiManageService;
import com.ppcredit.bamboo.backend.web.rest.admin.backend.controller.BackEndController;
import com.ppcredit.bamboo.backend.web.rest.admin.util.AdminUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import com.ppcredit.bamboo.backend.web.rest.admin.util.response.ResponseBody;
import com.ppcredit.bamboo.backend.web.rest.admin.util.web.WebUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiParam;
import com.ppcredit.bamboo.backend.web.rest.admin.util.ConstantsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务配置（接口配置）
 * Date: 2016-11-24 14:50:36
 */
@Controller
@RequestMapping("/admin/api")
public class ApiManageController {

	private static Logger logger = LoggerFactory.getLogger(BackEndController.class); // 日志
	
	@Inject
	private ApiManageService myApiManageService;
	/**
	 * 页面初始化
	 * @param request
	 * @return
	 */
	@RequestMapping("api_manage")
	public String interModelManage(HttpServletRequest request, Map<String, List<ApiParam>> map) {
		logger.info("初始化服务/模型>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		// 查询此appKey没有选中的服务模型
		List<ApiParam> list = myApiManageService.queryApi(appKey,"0"); 
		// 查询此appKey已经选中的服务模型
		List<ApiParam> apiList = myApiManageService.queryApiList(appKey,"0");  // 0 默认是接口
		map.put("list", list);
		map.put("apiList", apiList);
		return "api_manage";
	}
	
	@RequestMapping("query_api")
	@org.springframework.web.bind.annotation.ResponseBody
	public Object queryApi(HttpServletRequest request, HttpServletResponse response) {
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String type = request.getParameter("type");
		Map<String,Object> map = new HashMap<String,Object>();
		List<ApiParam> list = myApiManageService.queryApi(appKey,type);   
		map.put("list", list);
		return map;
	}
	
	@RequestMapping("query_apiList")
	@org.springframework.web.bind.annotation.ResponseBody
	public Object queryApiList(HttpServletRequest request, HttpServletResponse response) {
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String type = request.getParameter("type");
		Map<String,Object> map = new HashMap<String,Object>();
		List<ApiParam> apiList = myApiManageService.queryApiList(appKey,type);  
		map.put("apiList", apiList);
		return map;
	}
	
	/**
	 * 根据接口名查询
	 * @param request
	 */
	@RequestMapping("query")
	@org.springframework.web.bind.annotation.ResponseBody
	public Object query(HttpServletRequest request, HttpServletResponse response)throws IOException {
		logger.info("查询服务/模型>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String type = request.getParameter("type");
		String api = request.getParameter("api");
		int offset = Integer.parseInt(request.getParameter("iDisplayStart"));    //起始点
		int pagesize = Integer.parseInt(request.getParameter("iDisplayLength")); //每页数
		
		Map<String,Object> map = new HashMap<String,Object>();
		Pager pager = myApiManageService.query(appKey, type, api, offset, pagesize);
		map.put("aaData", pager.getResultList());
		map.put("iTotalRecords", pager.getTotalNum());
		map.put("iTotalDisplayRecords", pager.getTotalNum());
		return map;
	}
	
	/**
	 * 保存服务和模型
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping("addApi")
	public void addApi(HttpServletRequest request, HttpServletResponse response)throws Exception {
		logger.info("保存服务/模型>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String apiType = request.getParameter("apiType"); // API类型
		String api = request.getParameter("api"); // API名称
		
		ResponseBody body = new ResponseBody();
		try {
			myApiManageService.addApi(appKey, apiType, api); 
			body = new ResponseBody("success", "保存成功", "保存成功");
		} catch (Exception e) {
			body = new ResponseBody("error", "保存失败", "保存失败");
			e.printStackTrace();
		}
		WebUtil.write(response, body.getBody(),	ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
	}
	
	/**
	 * 修改服务和模型
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("update")
	public void update(HttpServletRequest request, HttpServletResponse response)throws Exception {
		logger.info("修改服务/模型>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String id = request.getParameter("id"); // API类型
		String apiType = request.getParameter("apiType"); // 
		String api = request.getParameter("api"); // 
		int isActive = 1;
		ResponseBody body = new ResponseBody();
		try {
			myApiManageService.update(appKey,id,apiType,api,isActive); 
			body = new ResponseBody("success", "修改成功", "修改成功");
		} catch (Exception e) {
			body = new ResponseBody("error", "修改失败", "修改失败");
			e.printStackTrace();
		}
		WebUtil.write(response, body.getBody(),	ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
	}
	
	/**
	 * 删除服务和模型
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response)throws Exception{
		logger.info("删除服务/模型>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String id = request.getParameter("id"); // API类型
		String apiType = request.getParameter("apiType"); // 
		String api = request.getParameter("api"); // 
		int isActive = 0;
		ResponseBody body = new ResponseBody();
		try {
			myApiManageService.update(appKey,id,apiType,api,isActive); 
			body = new ResponseBody("success", "删除成功", "删除成功");
		} catch (Exception e) {
			body = new ResponseBody("error", "删除失败", "删除失败");
			e.printStackTrace();
		}
		WebUtil.write(response, body.getBody(),	ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
	}
	
}
