package com.ppcredit.bamboo.backend.web.rest.admin.request.controller;

import com.ppcredit.bamboo.backend.web.rest.admin.request.service.RequestManageService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.AdminUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import com.ppcredit.bamboo.backend.web.rest.admin.util.response.ResponseBody;
import com.ppcredit.bamboo.backend.web.rest.admin.util.web.WebUtil;
import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiParam;
import com.ppcredit.bamboo.backend.web.rest.admin.request.dto.ProcessConfigManageDTO;
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
 * 请求查询
 * Date: 2016-11-28 10:27:14
 */
@Controller
@RequestMapping("/admin/request")
public class RequestManageController {
	
	private static Logger logger = LoggerFactory.getLogger(RequestManageController.class); // 日志
	
	@Inject
	private RequestManageService myRequestManageService;
	
	/**
	 * 请求参数页面初始化
	 * @param request
	 * @return
	 */
	@RequestMapping("request_manage")
	public String requestManage(HttpServletRequest request, Map<String,List<ApiParam>> map) {
		logger.info("请求报告>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		List<ApiParam> apiList = myRequestManageService.queryApiList(appKey,"0"); 
		map.put("apiList", apiList);
		return "request_manage";
	}

	/**
	 * 请求参数 级联查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("query_apiList")
	@org.springframework.web.bind.annotation.ResponseBody
	public Object queryApiList(HttpServletRequest request, HttpServletResponse response) {
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String type = request.getParameter("type");
		Map<String,Object> map = new HashMap<String,Object>();
		List<ApiParam> apiList = myRequestManageService.queryApiList(appKey,type);
		map.put("apiList", apiList);
		return map;
	}
	
	/**
	 * 请求参数 条件，列表查询
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("query")
	@org.springframework.web.bind.annotation.ResponseBody
	public Object query(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("请求查询>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		int offset = Integer.parseInt(request.getParameter("iDisplayStart"));    //起始点
		int pagesize = Integer.parseInt(request.getParameter("iDisplayLength")); //每页数
		String type = request.getParameter("type");
		String api = request.getParameter("api");
		System.out.println(type + "---------" +api);
		
		Map<String,Object> map = new HashMap<String,Object>();
		Pager pager = myRequestManageService.query(appKey, type, api, offset, pagesize);
		map.put("aaData", pager.getResultList());
		map.put("iTotalRecords", pager.getTotalNum());
		map.put("iTotalDisplayRecords", pager.getTotalNum());
		return map;
//		String json  = JSON.toJSONString(map);
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().print(json);
	}
	
	/**
	 * 请求参数配置页面初始化
	 * @param request
	 * @return
	 */
	@RequestMapping("requestConfig_manage")
	public String requestConfigManage(HttpServletRequest request, Map<String,List<ApiParam>> map) {
		logger.info("请求参数报告>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		List<ApiParam> apiList = myRequestManageService.queryConfigApiList(appKey,"0"); 
		map.put("apiList", apiList);
		return "requestConfig_manage";
	}
	
	/**
	 * 请求参数配置 级联查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("queryConfig_apiList")
	@org.springframework.web.bind.annotation.ResponseBody
	public Object queryConfigApiList(HttpServletRequest request, HttpServletResponse response) {
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String type = request.getParameter("type");
		Map<String,Object> map = new HashMap<String,Object>();
		List<ApiParam> apiList = myRequestManageService.queryConfigApiList(appKey,type); 
		map.put("apiList", apiList);
		return map;
	}
	
	/**
	 * 请求参数配置 条件，列表查询
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("queryConfig")
	@org.springframework.web.bind.annotation.ResponseBody
	public Object queryConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("请求查询>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		int offset = Integer.parseInt(request.getParameter("iDisplayStart"));    //起始点
		int pagesize = Integer.parseInt(request.getParameter("iDisplayLength")); //每页数
		String type = request.getParameter("type");
		String api = request.getParameter("api");
		System.out.println(type + "---------" +api);
		
		Map<String,Object> map = new HashMap<String,Object>();
		Pager pager = myRequestManageService.queryConfig(appKey, type, api, offset, pagesize); 
		map.put("aaData", pager.getResultList());
		map.put("iTotalRecords", pager.getTotalNum());
		map.put("iTotalDisplayRecords", pager.getTotalNum());
		return map;
	}
	
	/**
	 * 请求参数 级联查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("queryConfig_list")
	@org.springframework.web.bind.annotation.ResponseBody
	public Object queryConfigList(HttpServletRequest request, HttpServletResponse response) {
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String type = request.getParameter("type");
		Map<String,Object> map = new HashMap<String,Object>();
		List<ApiParam> list = myRequestManageService.queryConfigList(appKey,type);
		map.put("list", list);
		return map;
	}
	
	/**
	 * 请求参数配置保存
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request, HttpServletResponse response)throws Exception {
		logger.info("请求参数配置保存>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String apiType = request.getParameter("apiType"); // API类型
		String api = request.getParameter("api"); // API名称
		
		ResponseBody body = new ResponseBody();
		try {
			myRequestManageService.save(appKey, apiType, api); 
			body = new ResponseBody("success", "保存成功", "保存成功");
		} catch (Exception e) {
			body = new ResponseBody("error", "保存失败", "保存失败");
			e.printStackTrace();
		}
		WebUtil.write(response, body.getBody(),	ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
	}
	
	/**
	 * 修改页面显示数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("updateQuery")
	@org.springframework.web.bind.annotation.ResponseBody
	public Object updateQuery(HttpServletRequest request, HttpServletResponse response) {
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String id = request.getParameter("id");
		Map<String,ProcessConfigManageDTO> map = new HashMap<String,ProcessConfigManageDTO>();
		ProcessConfigManageDTO processConfig = myRequestManageService.updateQuery(appKey,id); 
		map.put("processConfig", processConfig);
		return map;
	}
	
	/**
	 * 修改请求参数配置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("updateConfig")
	public void updateConfig(HttpServletRequest request, HttpServletResponse response)throws Exception {
		logger.info("修改请求参数配置>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String id = request.getParameter("id"); // API类型
		String orders = request.getParameter("orders"); // 
		String alias = request.getParameter("alias"); // 
		int isActive = 1;
		ResponseBody body = new ResponseBody();
		try {
			myRequestManageService.updateConfig(appKey,id,orders,alias,isActive); 
			body = new ResponseBody("success", "修改成功", "修改成功");
		} catch (Exception e) {
			body = new ResponseBody("error", "修改失败", "修改失败");
			e.printStackTrace();
		}
		WebUtil.write(response, body.getBody(),	ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
	}
	
	/**
	 * 删除请求参数配置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response)throws Exception{
		logger.info("删除请求参数配置>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		String id = request.getParameter("id"); // API类型
		String apiType = request.getParameter("apiType"); // 
		String api = request.getParameter("api"); // 
		int isActive = 0;
		ResponseBody body = new ResponseBody();
		try {
			myRequestManageService.delete(appKey,id,apiType,api,isActive); 
			body = new ResponseBody("success", "删除成功", "删除成功");
		} catch (Exception e) {
			body = new ResponseBody("error", "删除失败", "删除失败");
			e.printStackTrace();
		}
		WebUtil.write(response, body.getBody(),	ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
	}
	
	/**
	 * 初始化按钮
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("init")
	public void init(HttpServletRequest request, HttpServletResponse response)throws Exception{
		logger.info("初始化按钮>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		ResponseBody body = new ResponseBody();
		try {
			myRequestManageService.init(appKey); 
			body = new ResponseBody("success", "初始化成功", "初始化成功");
		} catch (Exception e) {
			body = new ResponseBody("error", "初始化失败", "初始化失败");
			e.printStackTrace();
		}
		WebUtil.write(response, body.getBody(),	ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
	}
	
	/**
	 * 重置按钮
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("reset")
	public void reset(HttpServletRequest request, HttpServletResponse response)throws Exception{
		logger.info("重置按钮>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		ResponseBody body = new ResponseBody();
		try {
			myRequestManageService.reset(appKey); 
			body = new ResponseBody("success", "重置成功", "重置成功");
		} catch (Exception e) {
			body = new ResponseBody("error", "重置失败", "重置失败");
			e.printStackTrace();
		}
		WebUtil.write(response, body.getBody(),	ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
	}
	
	/**
	 * 提交按钮
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("submit")
	public void submit(HttpServletRequest request, HttpServletResponse response)throws Exception{
		logger.info("提交按钮>>>>");
		// 获取session中的appKey
		String appKey = AdminUtil.getAppKey(request);
		ResponseBody body = new ResponseBody();
		try {
			myRequestManageService.submit(appKey); 
			body = new ResponseBody("success", "提交成功", "提交成功");
		} catch (Exception e) {
			body = new ResponseBody("error", "提交失败", "提交失败");
			e.printStackTrace();
		}
		WebUtil.write(response, body.getBody(),	ConstantsUtil.SYSTEM_DEFAULT_ENCODING);
	}
	
}
