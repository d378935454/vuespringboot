package com.ppcredit.bamboo.backend.web.rest.admin.login.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dao.SSOUserDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;
import org.springframework.stereotype.Repository;

/**
 * Title: SSOUserDAOImpl.java Description: 描述
 * @created 2016-11-22 17:25:54
 */
@Repository("mySSOUserDAO")
public class SSOUserDAOImpl implements SSOUserDAO {

	@PersistenceContext
	private EntityManager  em;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<SSORoleDTO> getRoles(SSOUserDTO u) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT r.* FROM PPDAI_SSO_USER_ROLE ur, PPDAI_SSO_ROLE r, PPDAI_SSO_USER u WHERE ur.roleid = r.id AND ur.userid = u.id ");
		sql.append(" AND u.user_ldap_name = '"+u.getUserLdapName()+"' AND ur.isactive='1' ");
		Query query = em.createNativeQuery(sql.toString(),SSORoleDTO.class);
		if (em != null) {
			em.close();
		}
		List<SSORoleDTO> list = query.getResultList();
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	
}
