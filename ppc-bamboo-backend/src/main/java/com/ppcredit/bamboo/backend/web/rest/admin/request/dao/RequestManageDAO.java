package com.ppcredit.bamboo.backend.web.rest.admin.request.dao;

import java.util.List;

import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Component;

import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiParam;
import com.ppcredit.bamboo.backend.web.rest.admin.request.dto.ProcessConfigManageDTO;

@Component
public interface RequestManageDAO {

	List<ApiParam> queryApiList(String appKey,String type);
	
	Pager query(String appKey, String type, String api, int page, int pageSize);

	List<ApiParam> queryConfigApiList(String appKey, String type);
	
	Pager queryConfig(String appKey, String type, String api, int page, int pageSize);

	List<ApiParam> queryConfigList(String appKey, String type);

	void save(ProcessConfigManageDTO pm)  throws Exception;

	ProcessConfigManageDTO updateQuery(String appKey, String id);

	void updateConfig(String appKey, String id, String orders, String alias, int isActive)  throws Exception;

	void delete(String appKey, String id, String apiType, String api, int isActive)  throws Exception;

	void init(String appKey)  throws Exception;

	void reset(String appKey) throws Exception;

	void submit(String appKey) throws Exception;

}
