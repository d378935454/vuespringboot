package com.ppcredit.bamboo.backend.web.rest.admin.login.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSOResFuncDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleFuncDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.util.limit.HqlProperty;
import com.ppcredit.bamboo.backend.web.rest.admin.util.limit.LimitInfo;
import org.springframework.stereotype.Repository;

import com.ppcredit.bamboo.backend.web.rest.admin.login.dao.SSOResFuncDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.login.dto.SSORoleDTO;

@Repository("mySSOResFuncDAO")
public class SSOResFuncDAOImpl  implements SSOResFuncDAO {

	@PersistenceContext
	private EntityManager  em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SSOResFuncDTO> list() {
		LimitInfo limit = new LimitInfo();
		limit.addFilterProperty(HqlProperty.getEq("isActive", (byte)1));
		limit.setSortProperty("parentid,sortid");
		limit.setSortType("asc");
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM PPC_PRODUCT_SSO_RSC_FUNC WHERE isactive=1 ORDER BY parentid,sortid ");
		Query query = em.createNativeQuery(sql.toString(), SSOResFuncDTO.class);
		List<SSOResFuncDTO> roleFuncs = query.getResultList();
		if (em != null) {
			em.close();
		}
		if(roleFuncs != null && roleFuncs.size()>0){
			return roleFuncs;
		}
		return null;
	}

	/**
	 * 根据角色拿到对应的关系对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SSORoleFuncDTO> listRoleFuncs(SSORoleDTO role) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT f.* FROM PPDAI_SSO_ROLE_FUNC f, PPDAI_SSO_ROLE r, PPC_PRODUCT_SSO_RSC_FUNC ff" );
		sql.append(" WHERE f.isactive=1 AND f.roleid = r.id AND f.FUNCID = ff.id AND r.id = '"+role.getId()+"' AND ");
		sql.append(" ff.isactive=1 ORDER BY ff.parentid,ff.sortid ASC ");
		Query query = em.createNativeQuery(sql.toString(), SSORoleFuncDTO.class);
		List<SSORoleFuncDTO> roleFuncs = query.getResultList();
		if (em != null) {
			em.close();
		}
		if(roleFuncs != null && roleFuncs.size()>0){
			return roleFuncs;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SSOResFuncDTO> getFuncList(SSORoleDTO role) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ff.* FROM PPDAI_SSO_ROLE_FUNC f, PPDAI_SSO_ROLE r, PPC_PRODUCT_SSO_RSC_FUNC ff" );
		sql.append(" WHERE f.isactive=1 AND f.roleid = r.id AND f.FUNCID = ff.id AND r.id = '"+role.getId()+"' AND ");
		sql.append(" ff.isactive=1 ORDER BY ff.parentid,ff.sortid ASC ");
		Query query = em.createNativeQuery(sql.toString(), SSOResFuncDTO.class);
		List<SSOResFuncDTO> roleFuncs = query.getResultList();
		if (em != null) {
			em.close();
		}
		if(roleFuncs != null && roleFuncs.size()>0){
			return roleFuncs;
		}
		return null;
	}

}
