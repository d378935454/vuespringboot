package com.ppcredit.bamboo.backend.web.rest.admin.login.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dao.SSOLoginDAO;
import org.springframework.stereotype.Repository;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;

@Transactional
@Repository("mySSOLoginDAO")
public class SSOLoginDAOImpl implements SSOLoginDAO {

	@PersistenceContext
	private EntityManager  em;
	
	@Override
	public SSOUserDTO findByuserLdapName(String ldapName) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * from PPDAI_SSO_USER WHERE  isactive = 1 AND user_ldap_name = '"+ldapName+"'");
		Query query = em.createNativeQuery(sql.toString(), SSOUserDTO.class);
		List<SSOUserDTO> list = query.getResultList();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public SSOUserDTO getUserByName(String name) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * from PPDAI_SSO_USER WHERE  isactive = 1 AND user_login_name = '"+name+"'");
		Query query = em.createNativeQuery(sql.toString(), SSOUserDTO.class);
		List<SSOUserDTO> list = query.getResultList();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public SSOUserDTO findByid(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * from PPDAI_SSO_USER WHERE  isactive = 1 AND id = '"+id+"'");
		Query query = em.createNativeQuery(sql.toString(), SSOUserDTO.class);
		List<SSOUserDTO> list = query.getResultList();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void save(SSOUserDTO u) {
		em.persist(u);
	}



}
