package com.ppcredit.bamboo.backend.web.rest.admin.request.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ppcredit.bamboo.backend.web.rest.admin.api.dao.ApiManageDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiManageInterfaceDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiParam;
import com.ppcredit.bamboo.backend.web.rest.admin.request.dao.RequestManageDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.request.dto.ProcessConfigManageDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.request.service.RequestManageService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;

@Service("myRequestManageService")
public class RequestManageServiceImpl implements RequestManageService {

	@Inject
	private RequestManageDAO myRequestManageDAO;
	
	@Inject
	private ApiManageDAO myApiManageDAO;
	
	/**
	 * 请求参数
	 */
	@Override
	public List<ApiParam> queryApiList(String appKey,String type) {
		return myRequestManageDAO.queryApiList(appKey,type);
	}
	
	/**
	 * 请求参数查询列表
	 */
	@Override
	public Pager query(String appKey, String type, String api, int offset, int pagesize) {
		Pager pager = myRequestManageDAO.query(appKey, type, api, offset, pagesize);
		return pager;
	}

	@Override
	public List<ApiParam> queryConfigApiList(String appKey, String type) {
		return myRequestManageDAO.queryConfigApiList(appKey,type);
	}
	
	/**
	 * 请求参数配置查询列表
	 */
	@Override
	public Pager queryConfig(String appKey, String type, String api, int offset, int pagesize) {
		Pager pager = myRequestManageDAO.queryConfig(appKey, type, api, offset, pagesize);
		return pager;
	}

	/**
	 * 请求参数配置
	 */
	@Override
	public List<ApiParam> queryConfigList(String appKey, String type) {
		return myRequestManageDAO.queryConfigList(appKey,type);
	}

	/**
	 * 新增保存
	 */
	@Override
	public void save(String appKey, String apiType, String api) throws Exception {
		List<ApiManageInterfaceDTO> list = myApiManageDAO.queryInterface(apiType, api);
		ProcessConfigManageDTO pm = null;
		for(ApiManageInterfaceDTO ad : list ){
			pm = new ProcessConfigManageDTO();
			pm.setAppKey(appKey);
			pm.setApi(api);
			pm.setProcess(1);
			pm.setOrders(1); // 执行顺序默认为1
			pm.setApiType(apiType);
			String argument = ad.getArgument();
			pm.setArgument(argument);
			String argType = ad.getArgumentType();
			pm.setArgType(argType);
			String alias = api + "_" +argument;
			pm.setAlias(alias);
			myRequestManageDAO.save(pm);
		}
	}

	/**
	 * 修改查询
	 */
	@Override
	public ProcessConfigManageDTO updateQuery(String appKey, String id) {
		return myRequestManageDAO.updateQuery(appKey, id);
	}
	
	/**
	 * 修改保存
	 */
	@Override
	public void updateConfig(String appKey, String id, String orders, String alias, int isActive) throws Exception{
		myRequestManageDAO.updateConfig(appKey,id,orders,alias,isActive);		
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(String appKey, String id, String apiType, String api, int isActive)  throws Exception{
		myRequestManageDAO.delete(appKey,id,apiType,api,isActive);	
	}

	/**
	 * 初始化按钮
	 */
	@Override
	public void init(String appKey) throws Exception{
		myRequestManageDAO.init(appKey);		
	}

	/**
	 * 重置
	 */
	@Override
	public void reset(String appKey) throws Exception{
		myRequestManageDAO.reset(appKey);
	}

	/**
	 * 提交
	 */
	@Override
	public void submit(String appKey) throws Exception {
		myRequestManageDAO.submit(appKey);
	}

}
