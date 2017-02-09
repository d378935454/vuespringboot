package com.ppcredit.bamboo.backend.web.rest.admin.config.dto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * 接口参数
 * date: 2016-12-13 17:22:35
 */
public class Param {
	
	private String api; // 接口
	
	private String apiName; // 接口名称


	private List<ConcurrentMap<String,String>> argList; // 接口参数集合


	public List<ConcurrentMap<String, String>> getArgList() {
		return argList;
	}

	public void setArgList(List<ConcurrentMap<String, String>> argList) {
		this.argList = argList;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

}
