package com.ppcredit.bamboo.backend.web.rest.admin.login.dao;

import org.springframework.stereotype.Component;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;

@Component
public interface SSOLoginDAO {

	SSOUserDTO findByuserLdapName(String ldapName);

	SSOUserDTO findByid(String id);

	void save(SSOUserDTO u);

	SSOUserDTO getUserByName(String name);

}
