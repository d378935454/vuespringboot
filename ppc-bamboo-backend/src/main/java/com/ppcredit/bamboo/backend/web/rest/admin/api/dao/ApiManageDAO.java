package com.ppcredit.bamboo.backend.web.rest.admin.api.dao;

import java.util.List;

import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiManageConfigDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiManageInterfaceDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiParam;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Component;

@Component
public interface ApiManageDAO {

	public Pager query(String appKey, String type, String api, int page, int pageSize);

	public void addApi(ApiManageConfigDTO apiConfig) throws Exception;

	public List<ApiParam> queryApi(String appKey, String type);
	
	public List<ApiParam> queryApiList(String appKey, String type);

	public void update(String appKey,String api) throws Exception;

	public List<ApiManageInterfaceDTO> queryInterface(String apiType, String api);

}
