package com.ppcredit.bamboo.backend.web.rest.admin.manage.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.APIKeySetDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.manage.dao.UserManageDAO;

@Repository("myUserManageDAO")
public class UserManageDAOImpl implements UserManageDAO {

	@PersistenceContext
	private EntityManager  em;
	
	@Override
	public Pager query(String status, String searchCondition, int page, int pageSize) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT k.* FROM PPDAI_API_KEYSET k, PPDAI_API_USER u WHERE k.uid=u.id AND k.isactive=1 AND k.STATUS='S'");
		if (StringUtils.isNotBlank(status)){
			sql.append(" AND  u.STATUS = '"+status+"' ");
		}
		if (StringUtils.isNotBlank(searchCondition)){
			sql.append(" AND (u.USERNAME LIKE '%"+searchCondition+"%'  OR u.EMAIL LIKE '%"+searchCondition+"%' " +
					"OR u.TEL LIKE '%"+searchCondition+"%' OR u.COMPANY LIKE '%"+searchCondition+"%' OR k.APPKEY LIKE '%"+searchCondition+"%') ");
		}
		sql.append(" ORDER BY U.INSERTTIME DESC");
		
		int count = count(sql.toString());
		List<APIKeySetDTO> pageList = queryList(sql.toString(), page, pageSize);
		if (em != null) {
			em.close();
		}
		return new Pager(page, pageSize, pageList, count);
	}

	@SuppressWarnings("unchecked")
	private int count(String sql){
		Query query = em.createNativeQuery(sql, APIKeySetDTO.class);
		List<APIKeySetDTO> list = query.getResultList();
		return list.size();
	}
	
	@SuppressWarnings("unchecked")
	private List<APIKeySetDTO> queryList(String sql, int page, int pageSize){
		Query query = em.createNativeQuery(sql, APIKeySetDTO.class);
		int start = (page > 0) ? page : 0;
		return query.setFirstResult(start).setMaxResults(pageSize).getResultList();
	}
	
}
