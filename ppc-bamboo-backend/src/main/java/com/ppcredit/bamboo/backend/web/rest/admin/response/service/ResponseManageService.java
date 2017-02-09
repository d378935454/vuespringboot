package com.ppcredit.bamboo.backend.web.rest.admin.response.service;

import java.util.List;

import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Component;

import com.ppcredit.bamboo.backend.web.rest.admin.request.dto.ProcessManageDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.response.dto.APIReturnInfo;

@Component
public interface ResponseManageService {

	public Pager query(String appKey, String type, String api, int offset, int pagesize);

	public void addCallback(APIReturnInfo callbackInfo);

	public APIReturnInfo getCallbackInfoByAppkey(String appKey);

	public List<ProcessManageDTO> queryApiList(String appKey, String type);
}
