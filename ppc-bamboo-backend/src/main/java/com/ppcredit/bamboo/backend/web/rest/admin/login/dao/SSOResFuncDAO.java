package com.ppcredit.bamboo.backend.web.rest.admin.login.dao;

import java.util.List;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOResFuncDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleFuncDTO;
import org.springframework.stereotype.Component;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleDTO;

@Component
public interface SSOResFuncDAO {
	
	public List<SSOResFuncDTO> list();
	
	public List<SSOResFuncDTO> getFuncList(SSORoleDTO role);

	public List<SSORoleFuncDTO> listRoleFuncs(SSORoleDTO role);
	
}
