package com.ppcredit.bamboo.backend.web.rest.admin.api.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Service;

import com.ppcredit.bamboo.backend.web.rest.admin.api.dao.ApiManageDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiManageConfigDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiParam;
import com.ppcredit.bamboo.backend.web.rest.admin.api.service.ApiManageService;

@Service("myApiManageService")
public class ApiManageServiceImpl implements ApiManageService {
	
	@Inject
	private ApiManageDAO myApiManageDAO;
	
	@Override
	public Pager query(String appKey, String type, String api, int offset, int pagesize) {
		
		Pager pager = myApiManageDAO.query(appKey, type, api, offset, pagesize);
		return pager;
	}

	@Override
	public void addApi(String appKey, String apiType, String api) throws Exception {
		ApiManageConfigDTO apiConfig = new ApiManageConfigDTO();
		apiConfig.setAppKey(appKey);
		apiConfig.setApiType(apiType);
		apiConfig.setApi(api);
		myApiManageDAO.addApi(apiConfig);
	}

	@Override
	public List<ApiParam> queryApi(String appKey,String type) {
		return myApiManageDAO.queryApi(appKey,type);
	}
	
	@Override
	public List<ApiParam> queryApiList(String appKey,String type) {
		return myApiManageDAO.queryApiList(appKey,type);
	}

	@Override
	public void update(String appKey, String id, String apiType, String api, int isActive) throws Exception{
		myApiManageDAO.update(appKey,api);
	}

}
