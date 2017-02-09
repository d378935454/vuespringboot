package com.ppcredit.bamboo.backend.web.rest.admin.api.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.ppcredit.bamboo.backend.web.rest.admin.api.dao.ApiManageDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiManageConfigDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiManageInterfaceDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiParam;

@Transactional
@Repository("myApiManageDAO")
public class ApiManageDAOImpl implements ApiManageDAO {

	@PersistenceContext
	private EntityManager  em;
	
	@Override
	public Pager query(String appKey, String type, String api, int page, int pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT c.* FROM ppc_product_api_config c  ");
		sql.append(" WHERE c.isactive=1 AND c.appkey = '"+appKey+"' ");
		if(StringUtils.isNotBlank(type)){
			sql.append(" AND c.api_type = '"+type+"'");
		}
		if(StringUtils.isNotBlank(api)){
			sql.append(" AND c.api = '"+api+"'");
		}
		sql.append(" ORDER BY c.updatetime DESC ");
		int count = count(sql.toString());
		List<ApiManageConfigDTO> pageList = queryList(sql.toString(), page, pageSize);
		if (em != null) {
			em.close();
		}
		return new Pager(page, pageSize, pageList, count);
	}


	@SuppressWarnings("unchecked")
	private int count(String sql){
		Query query = em.createNativeQuery(sql, ApiManageConfigDTO.class);
		List<ApiManageConfigDTO> list = query.getResultList();
		return list.size();
	}
	
	@SuppressWarnings("unchecked")
	private List<ApiManageConfigDTO> queryList(String sql, int page, int pageSize){
		Query query = em.createNativeQuery(sql, ApiManageConfigDTO.class);
		int start = (page > 0) ? page : 0;
		List<ApiManageConfigDTO> pageList = query.setFirstResult(start).setMaxResults(pageSize).getResultList();
		return pageList;
	}

	@Override
	public void addApi(ApiManageConfigDTO apiConfig) throws Exception {
		em.persist(apiConfig);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<ApiParam> queryApi(String appKey,String type) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT i.api,d.remark FROM ppc_product_api_interface i ,ppdai_third_dictionary d  WHERE i.api = d.channel_source_name  ");
		sql.append("AND d.channel_source_name <> '' AND i.api NOT IN(SELECT api FROM ppc_product_api_config WHERE isactive = 1 and appkey= '"+appKey+"') ");
		if(StringUtils.isNotBlank(type)){
			sql.append(" AND i.api_type = '"+type+"'");
		}
		sql.append(" GROUP BY d.channel_source_name  ");
		Query query = em.createNativeQuery(sql.toString());
		List list = query.getResultList();
		List<ApiParam> li = new ArrayList<ApiParam>();
		if(list != null && list.size() > 0){
			ApiParam ad = null;
			int size = list.size();
			for(int i = 0;i < size; i++){
				Object[] obj = (Object[]) list.get(i);
				ad = new ApiParam();
				ad.setApi(String.valueOf(obj[0]));
				ad.setApiName(String.valueOf(obj[1]));
				li.add(ad);
			}
		}
		return li;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<ApiParam> queryApiList(String appKey,String type) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT c.api,d.remark FROM ppc_product_api_config c,ppdai_third_dictionary d WHERE c.api = d.channel_source_name  ");
		sql.append(" AND  c.isactive=1 AND d.channel_source_name <> '' AND c.appkey='"+appKey+"' ");
		if(StringUtils.isNotBlank(type)){
			sql.append(" AND c.api_type = '"+type+"'");
		}
		sql.append(" GROUP BY d.channel_source_name  ");
		Query query = em.createNativeQuery(sql.toString());
		List list = query.getResultList();
		List<ApiParam> li = new ArrayList<ApiParam>();
		if(list != null && list.size() > 0){
			ApiParam ad = null;
			int size = list.size();
			for(int i = 0;i < size; i++){
				Object[] obj = (Object[]) list.get(i);
				ad = new ApiParam();
				ad.setApi(String.valueOf(obj[0]));
				ad.setApiName(String.valueOf(obj[1]));
				li.add(ad);
			}
		}
		return li;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(String appKey,String api) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT * FROM ppc_product_api_config WHERE isactive=1  AND api = '"+api+"' AND appkey = '"+appKey+"'");
		Query query = em.createNativeQuery(sql.toString(), ApiManageConfigDTO.class);
		List<ApiManageConfigDTO> list = query.getResultList();
		if(list != null && list.size() > 0){
			ApiManageConfigDTO ad = list.get(0);
			ad.setIsActive((byte) 0);
			ad.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			em.merge(ad);
		}
	}


	@Override
	public List<ApiManageInterfaceDTO> queryInterface(String apiType, String api) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ID,API,API_TYPE,ARGUMENT,ARGUMENT_NAME,ARGUMENT_TYPE,ISACTIVE FROM ppc_product_api_interface ");
		sql.append(" WHERE api = '"+api+"' AND api_type = '"+apiType+"' AND argument_type = '0' AND isactive = 1  ");
		Query query = em.createNativeQuery(sql.toString(), ApiManageInterfaceDTO.class);
		List<ApiManageInterfaceDTO> list = query.getResultList();
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
}
