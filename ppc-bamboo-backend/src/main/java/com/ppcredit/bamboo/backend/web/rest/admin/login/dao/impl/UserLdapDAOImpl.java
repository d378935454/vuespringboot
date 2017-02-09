package com.ppcredit.bamboo.backend.web.rest.admin.login.dao.impl;

import java.util.List;

import javax.naming.directory.DirContext;

//import org.springframework.ldap.core.DirContextOperations;
//import org.springframework.ldap.core.LdapTemplate;
//import org.springframework.ldap.core.support.AbstractContextMapper;
//import org.springframework.ldap.filter.AndFilter;
//import org.springframework.ldap.filter.EqualsFilter;
//import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository("myUserDao")
public class UserLdapDAOImpl {
//	private LdapTemplate ldapTemplate;
	private String baseDN = "";

	public boolean authenticate(String userName, String password) {
		/*AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectclass", "account")).and(new EqualsFilter("uid", userName));
		// Actual filter will differ depending on LDAP Server and schema
		List<?> results = ldapTemplate.search(baseDN, filter.toString(), new DnContextMapper());

		// List<?> result = ldapTemplate.search(DistinguishedName.EMPTY_PATH, filter.toString(), new DnContextMapper());
		if (results.size() != 1)
			return false;

		DirContext ctx = null;
		try {
			ctx = ldapTemplate.getContextSource().getContext((String) results.get(0), password);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			LdapUtils.closeContext(ctx);
		}*/
		return true;
	}

	/*private final static class DnContextMapper extends AbstractContextMapper {
		@Override
		protected String doMapFromContext(DirContextOperations ctx) {
			return ctx.getNameInNamespace();
		}
	} */
}
