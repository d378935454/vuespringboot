package com.ppcredit.bamboo.backend.web.rest.admin.login.service.impl;

import javax.inject.Inject;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dao.impl.UserLdapDAOImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("myUserLoginService")
public class UserLdapServiceImpl {
	
	@Inject
	private UserLdapDAOImpl myUserDao;

	public boolean Auth(String userName, String pwd) {
		try {
			return myUserDao.authenticate(userName, pwd);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
