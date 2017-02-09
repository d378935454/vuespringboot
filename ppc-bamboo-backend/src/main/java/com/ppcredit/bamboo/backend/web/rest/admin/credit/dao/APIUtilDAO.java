package com.ppcredit.bamboo.backend.web.rest.admin.credit.dao;

import java.util.List;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.APIKeySetDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;

public interface APIUtilDAO  {

	public List<APIKeySetDTO> findAPIKeySetList(SSOUserDTO sessionUser);
	
}

