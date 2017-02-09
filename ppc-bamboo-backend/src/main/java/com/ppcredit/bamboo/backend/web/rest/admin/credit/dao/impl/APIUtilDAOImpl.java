package com.ppcredit.bamboo.backend.web.rest.admin.credit.dao.impl;

import com.ppcredit.bamboo.backend.web.rest.admin.credit.dao.APIUtilDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.APIKeySetDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOUserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("myAPIUtilDAO")
public class APIUtilDAOImpl implements APIUtilDAO {

	@Override
	public List<APIKeySetDTO> findAPIKeySetList(SSOUserDTO sessionUser) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	@Override
	public List<APIKeySetDTO> findAPIKeySetList(SSOUserDTO sessionUser) {
		if (StringUtils.equals(sessionUser.getIsAllcustomer(), "1")) {
			return findAPIKeySetList(); 
		} else {
			String ids = getUserIds(sessionUser);
			return findAPIKeySetList(ids);
		}
	}*/
/*
	@SuppressWarnings("unchecked")
	public List<APIKeySetDTO> findAPIKeySetList() {
		StringBuffer hql = new StringBuffer("from APIKeySetDTO where 1=1");
		List<APIKeySetDTO> list = super.find(hql.toString(), new Object[]{});

		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<APIKeySetDTO> findAPIKeySetList(String ids) {
		if(StringUtils.isNotBlank(ids)) {
			StringBuffer hql = new StringBuffer("from APIKeySetDTO k where 1=1 and k.user.id in ("+ids+")");
			List<APIKeySetDTO> list = super.find(hql.toString(), new Object[]{});
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String getUserIds(SSOUserDTO sessionUser) {
		String ids ="";
		StringBuffer hql = new StringBuffer("from SSOAPIUserDTO where isActive=1");
		hql.append(" and ssoUser = ?");
		List<SSOAPIUserDTO> list = super.find(hql.toString(), new Object[]{sessionUser});
		for (int i=0;list!=null&&i<list.size();i++) {
			APIUserDTO user = list.get(i).getApiUser();
			ids += "'"+user.getId()+"'"+",";
		}
		if (ids.length() > 0) {
			ids = ids.substring(0, ids.length()-1);
		}
		return ids;
	}*/
	
}
