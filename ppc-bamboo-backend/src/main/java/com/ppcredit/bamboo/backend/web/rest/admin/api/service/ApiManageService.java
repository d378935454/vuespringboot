package com.ppcredit.bamboo.backend.web.rest.admin.api.service;

import java.util.List;

import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Component;

import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiParam;

@Component
public interface ApiManageService {

	public Pager query(String appKey, String type, String api, int offset, int pagesize);

	public void addApi(String appKey ,String apiType, String api)  throws Exception;

	public List<ApiParam> queryApi(String appKey, String type);
	
	public List<ApiParam> queryApiList(String appKey, String type);

	public void update(String appKey, String id, String apiType, String api, int isActive) throws Exception;

}
