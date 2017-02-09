package com.ppcredit.bamboo.backend.web.rest.admin.response.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.ppcredit.bamboo.backend.web.rest.admin.request.dto.ProcessManageDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.response.service.ResponseManageService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Service;

import com.ppcredit.bamboo.backend.web.rest.admin.response.dao.ResponseManageDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.response.dto.APIReturnInfo;

@Service("myResponseManageService")
public class ResponseManageServiceImpl implements ResponseManageService {

	@Inject
	private ResponseManageDAO myResponseManageDAO;

	@Override
	public Pager query(String appKey, String type, String api, int offset, int pagesize) {

		Pager pager = myResponseManageDAO.query(appKey, type, api, offset, pagesize);
		return pager;
	}

	@Override
	public void addCallback(APIReturnInfo callbackInfo) {
		myResponseManageDAO.addCallbackInfo(callbackInfo);
	}

	@Override
	public APIReturnInfo getCallbackInfoByAppkey(String appKey) {
		return myResponseManageDAO.getCallbackInfoByAppkey(appKey);
	}

	@Override
	public List<ProcessManageDTO> queryApiList(String appKey, String type) {
		return myResponseManageDAO.queryApiList(appKey,type);
	}

}
