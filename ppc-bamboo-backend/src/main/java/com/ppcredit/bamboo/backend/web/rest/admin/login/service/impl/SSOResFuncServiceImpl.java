package com.ppcredit.bamboo.backend.web.rest.admin.login.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dao.SSOResFuncDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dao.SSOUserDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOResFuncDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleFuncDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;
import org.springframework.stereotype.Service;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.service.SSOResFuncService;

@Service("mySSOResFuncService")
public class SSOResFuncServiceImpl implements SSOResFuncService {

	@Inject
	private SSOResFuncDAO mySSOResFuncDAO;
	
	@Inject
	private SSOUserDAO mySSOUserDAO;
	    
	@Override
	public List<SSOResFuncDTO> getUserFuncsList(SSOUserDTO u) {
		List<SSOResFuncDTO> funs = null;
		//拿到用户的所有角色
		List<SSORoleDTO> roles = mySSOUserDAO.getRoles(u);
		if(roles != null) {
			funs = new ArrayList<SSOResFuncDTO>();
			for (SSORoleDTO role : roles) {
				//如果角色中有超级管理员，则直接查询所有菜单
//				if(role.getAuthAdmin() == 1){
//					List<SSOResFuncDTO> f = mySSOResFuncDAO.list();
//					return f;
//				}					
				List<SSOResFuncDTO> f = mySSOResFuncDAO.getFuncList(role);
				if(f != null){
					funs.addAll(f);
				}
			}
		}
		return funs;
	}

	@Override
	public List<SSORoleDTO> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SSORoleDTO> listRoles(SSOUserDTO u) {
		List<SSORoleDTO> roles = mySSOUserDAO.getRoles(u);
		return roles;
	}

	@Override
	public List<SSOResFuncDTO> list() {
		return mySSOResFuncDAO.list();
	}

	@Override
	public List<SSORoleFuncDTO> listRoleFuncs(List<SSORoleDTO> roles) {
		//拿到用户的所有角色
		if(roles != null){
			List<SSORoleFuncDTO> list = new ArrayList<SSORoleFuncDTO>();
			for (SSORoleDTO role : roles) {
				List<SSORoleFuncDTO> f = mySSOResFuncDAO.listRoleFuncs(role);
				if(f != null && f.size() >0){
					list.addAll(f);
				}
			}
			return list;
		}
		return null;
	}
}
