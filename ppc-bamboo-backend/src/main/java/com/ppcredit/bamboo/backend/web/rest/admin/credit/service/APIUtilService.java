package com.ppcredit.bamboo.backend.web.rest.admin.credit.service;

import java.util.List;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.APIKeySetDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;

public interface APIUtilService {

	public List<APIKeySetDTO> findAPIKeySetList(SSOUserDTO sessionUser);
	
}

