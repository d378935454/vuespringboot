package com.ppcredit.bamboo.backend.web.rest.admin.login.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dao.SSOLoginDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.service.SSOLoginService;

@Service("mySSOLoginService")
public class SSOLoginServiceImpl implements SSOLoginService {
	
	@Inject
	private SSOLoginDAO mySSOLoginDAO;
	
	@Override
	public SSOUserDTO findByuserLdapName(String ldapName) {
		SSOUserDTO sso = mySSOLoginDAO.findByuserLdapName(ldapName);
		if (sso != null && !"".equals(sso))
			return sso;
		String name = ldapName + "@ppcredit.com";
		sso = mySSOLoginDAO.getUserByName(name);
		if (sso != null && !"".equals(sso))
			return sso;
		name = ldapName + "@ppdai.com";
		sso = mySSOLoginDAO.getUserByName(name);
		if (sso != null && !"".equals(sso))
			return sso;
		return null;
	}

	@Override
	public SSOUserDTO findByid(String id) {
		return mySSOLoginDAO.findByid(id);
	}

	@Override
	public void save(SSOUserDTO u) {
		mySSOLoginDAO.save(u);
	}


}
