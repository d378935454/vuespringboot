package com.ppcredit.bamboo.backend.web.rest.admin.response.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.ppcredit.bamboo.backend.web.rest.admin.request.dto.ProcessManageDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.ppcredit.bamboo.backend.web.rest.admin.response.dao.ResponseManageDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.response.dto.APIReturnInfo;

@Transactional
@Repository("myResponseManageDAOImpl")
public class ResponseManageDAOImpl implements ResponseManageDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("rawtypes")
	@Override
	public List<ProcessManageDTO> queryApiList(String appKey, String type) {
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT DISTINCT a.api FROM PPC_PRODUCT_PROCESS_API a WHERE a.arg_type = '1' AND a.appkey = '"+appKey+"'  ");
		if (StringUtils.isNotBlank(type)){
			sql.append(" AND  a.api_type = '"+type+"' ");
		}
		Query query = em.createNativeQuery(sql.toString());
		if (em != null) {
			em.close();
		}
		List list = query.getResultList();
		List<ProcessManageDTO> li = new ArrayList<ProcessManageDTO>();
		if(list != null && list.size() > 0){
			ProcessManageDTO pm = null;
			int size = list.size();
			for(int i=0;i < size;i++){
				pm = new ProcessManageDTO();
				pm.setApi(String.valueOf(list.get(i)));
				li.add(pm);
			}
		}
		return li;
	}
	
	@Override
	public Pager query(String appKey, String type, String api, int page, int pageSize) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT a.* FROM PPC_PRODUCT_PROCESS_API a WHERE a.arg_type = '1' AND a.appkey = '"+appKey+"'  ");
		if (StringUtils.isNotBlank(type)){
			sql.append(" AND  a.api_type = '"+type+"' ");
		}
		if (StringUtils.isNotBlank(api)){
			sql.append(" AND  a.api = '"+api+"' ");
		}
			
		int count = count(sql.toString());
		List<ProcessManageDTO> pageList = queryList(sql.toString(), page, pageSize);
		if (em != null) {
			em.close();
		}
		return new Pager(page, pageSize, pageList, count);
	}
	
	@SuppressWarnings("unchecked")
	private int count(String sql){
		Query query = em.createNativeQuery(sql, ProcessManageDTO.class);
		List<ProcessManageDTO> list = query.getResultList();
		return list.size();
	}
	
	@SuppressWarnings("unchecked")
	private List<ProcessManageDTO> queryList(String sql, int page, int pageSize){
		Query query = em.createNativeQuery(sql, ProcessManageDTO.class);
		int start = (page > 0) ? page : 0;
		List<ProcessManageDTO> pageList = query.setFirstResult(start).setMaxResults(pageSize).getResultList();
		return pageList;
	}

	@Override
	public void addCallbackInfo(APIReturnInfo callbackInfo) {
		// if (callbackInfo != null && StringUtils.equals(callbackInfo.getId().toString(), "0")) {
		// em.merge(callbackInfo);
		// } else {
		// em.persist(callbackInfo);
		// }
		em.merge(callbackInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public APIReturnInfo getCallbackInfoByAppkey(String appKey) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT i.* FROM ppc_product_api_return i ");
		sql.append(" WHERE appkey = '" + appKey + "' and isactive=1 ");
		Query query = em.createNativeQuery(sql.toString(), APIReturnInfo.class);
		List<APIReturnInfo> list = query.getResultList();
		if (em != null) {
			em.close();
		}
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else
			return null;
	}

}
