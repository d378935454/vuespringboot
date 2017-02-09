package com.ppcredit.bamboo.backend.web.rest.admin.request.service;

import java.util.List;

import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Component;

import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiParam;
import com.ppcredit.bamboo.backend.web.rest.admin.request.dto.ProcessConfigManageDTO;

@Component
public interface RequestManageService {
	
	List<ApiParam> queryApiList(String appKey,String type);

	Pager query(String appKey, String type, String api, int offset, int pagesize);

	List<ApiParam> queryConfigApiList(String appKey, String type);
	
	Pager queryConfig(String appKey, String type, String api, int offset, int pagesize);

	List<ApiParam> queryConfigList(String appKey, String type);

	void save(String appKey, String apiType, String api) throws Exception;

	ProcessConfigManageDTO updateQuery(String appKey, String id);
	
	void updateConfig(String appKey, String id, String orders, String alias, int isActive) throws Exception;

	void delete(String appKey, String id, String apiType, String api, int isActive)  throws Exception;

	void init(String appKey) throws Exception;

	void reset(String appKey) throws Exception;

	void submit(String appKey) throws Exception;

}
