package com.ppcredit.bamboo.backend.web.rest.admin.credit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ppcredit.bamboo.backend.web.rest.admin.credit.service.APIUtilService;
import org.springframework.stereotype.Service;

import com.ppcredit.bamboo.backend.web.rest.admin.credit.dao.APIUtilDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.APIKeySetDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;
@Service("myAPIUtilService")
public class APIUtilServiceImpl implements APIUtilService {

	@Resource
	private APIUtilDAO myAPIUtilDAO;

	@Override
	public List<APIKeySetDTO> findAPIKeySetList(SSOUserDTO sessionUser) {
		return myAPIUtilDAO.findAPIKeySetList(sessionUser);
	}
	
}

