package com.ppcredit.bamboo.backend.web.rest.admin.login.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;

@Component
public interface SSOUserDAO {

	public List<SSORoleDTO> getRoles(SSOUserDTO u);

}
