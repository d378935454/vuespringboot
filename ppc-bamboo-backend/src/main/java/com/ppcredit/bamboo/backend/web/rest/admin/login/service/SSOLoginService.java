package com.ppcredit.bamboo.backend.web.rest.admin.login.service;

import org.springframework.stereotype.Component;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;

@Component
public interface SSOLoginService {
	
	SSOUserDTO findByuserLdapName(String ldapName);
	
	SSOUserDTO findByid(String id);

	void save(SSOUserDTO u);
	
}
