package com.ppcredit.bamboo.backend.web.rest.admin.login.service;

import java.util.List;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOResFuncDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleFuncDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public interface SSOResFuncService{
	
	public List<SSORoleDTO> findByName(String name) ;
	
	public List<SSOResFuncDTO> getUserFuncsList(SSOUserDTO u);
	
	public List<SSORoleDTO> listRoles(SSOUserDTO u);
	
	public List<SSOResFuncDTO> list();
	
	public List<SSORoleFuncDTO> listRoleFuncs(List<SSORoleDTO> roles);
	
}
