package com.ppcredit.bamboo.backend.web.rest.admin.request.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.ppcredit.bamboo.backend.web.rest.admin.request.dto.ProcessManageDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiManageInterfaceDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.api.dto.ApiParam;
import com.ppcredit.bamboo.backend.web.rest.admin.request.dao.RequestManageDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.request.dto.ProcessConfigManageDTO;

@Transactional
@Repository("myRequestManageDAO")
public class RequestManageDAOImpl implements RequestManageDAO {

	@PersistenceContext
	private EntityManager  em;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<ApiParam> queryApiList(String appKey,String type) {
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT a.api,d.remark FROM PPC_PRODUCT_PROCESS_API a ,ppdai_third_dictionary d  WHERE a.api = d.channel_source_name ");
		sql.append("  AND a.arg_type = '0' AND a.isactive=1 AND a.appkey = '"+appKey+"'  ");
		if (StringUtils.isNotBlank(type)){
			sql.append(" AND  a.api_type = '"+type+"' ");
		}
		sql.append(" GROUP BY d.channel_source_name  ");
		Query query = em.createNativeQuery(sql.toString());
		if (em != null) {
			em.close();
		}
		List list = query.getResultList();
		List<ApiParam> li = new ArrayList<ApiParam>();
		if(list != null && list.size() > 0){
			ApiParam pm = null;
			int size = list.size();
			for(int i=0;i < size;i++){
				Object[] obj = (Object[]) list.get(i);
				pm = new ApiParam();
				pm.setApi(String.valueOf(obj[0]));
				pm.setApiName(String.valueOf(obj[1]));
				li.add(pm);
			}
		}
		return li;
	}
	
	/**
	 * 请求参数
	 */
	@Override
	public Pager query(String appKey, String type, String api, int page, int pageSize) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT a.* FROM PPC_PRODUCT_PROCESS_API a WHERE a.arg_type = '0' AND a.isactive=1 AND a.appkey = '"+appKey+"'  ");
		if (StringUtils.isNotBlank(type)){
			sql.append(" AND  a.api_type = '"+type+"' ");
		}
		if (StringUtils.isNotBlank(api)){
			sql.append(" AND  a.api = '"+api+"' ");
		}
		sql.append(" ORDER BY a.updatetime DESC ");	
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
	public List<ApiParam> queryConfigApiList(String appKey, String type) {
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT a.api,d.remark FROM ppc_product_process_api_temp a ,ppdai_third_dictionary d  WHERE a.api = d.channel_source_name   ");
		sql.append("  AND a.arg_type = '0' AND a.isactive=1 AND a.appkey = '"+appKey+"' ");
		if (StringUtils.isNotBlank(type)){
			sql.append(" AND  a.api_type = '"+type+"' ");
		}
		sql.append(" GROUP BY d.channel_source_name  ");
		Query query = em.createNativeQuery(sql.toString());
		if (em != null) {
			em.close();
		}
		List list = query.getResultList();
		List<ApiParam> li = new ArrayList<ApiParam>();
		if(list != null && list.size() > 0){
			ApiParam pm = null;
			int size = list.size();
			for(int i=0;i < size;i++){
				Object[] obj = (Object[]) list.get(i);
				pm = new ApiParam();
				pm.setApi(String.valueOf(obj[0]));
				pm.setApiName(String.valueOf(obj[1]));
				li.add(pm);
			}
		}
		return li;
	}
	
	
	/**
	 * 请求参数配置
	 */
	@Override
	public Pager queryConfig(String appKey, String type, String api, int page, int pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT a.* FROM ppc_product_process_api_temp a WHERE a.arg_type = '0' AND a.isactive = 1 AND a.appkey = '"+appKey+"'  ");
		if (StringUtils.isNotBlank(type)){
			sql.append(" AND  a.api_type = '"+type+"' ");
		}
		if (StringUtils.isNotBlank(api)){
			sql.append(" AND  a.api = '"+api+"' ");
		}
		sql.append("ORDER BY a.updatetime DESC ");
		int count = countConfig(sql.toString());
		List<ProcessConfigManageDTO> pageList = queryConfigList(sql.toString(), page, pageSize);
		if (em != null) {
			em.close();
		}
		return new Pager(page, pageSize, pageList, count);
	}
	@SuppressWarnings("unchecked")
	private int countConfig(String sql){
		Query query = em.createNativeQuery(sql, ProcessConfigManageDTO.class);
		List<ProcessConfigManageDTO> list = query.getResultList();
		return list.size();
	}
	
	@SuppressWarnings("unchecked")
	private List<ProcessConfigManageDTO> queryConfigList(String sql, int page, int pageSize){
		Query query = em.createNativeQuery(sql, ProcessConfigManageDTO.class);
		int start = (page > 0) ? page : 0;
		List<ProcessConfigManageDTO> pageList = query.setFirstResult(start).setMaxResults(pageSize).getResultList();
		return pageList;
	}

	@Override
	public List<ApiParam> queryConfigList(String appKey, String type) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT c.api,d.remark FROM ppc_product_api_config c ,ppdai_third_dictionary d WHERE c.api = d.channel_source_name AND c.isactive=1 AND c.appkey = '"+appKey+"' ");
		sql.append(" AND c.api NOT IN (SELECT DISTINCT a.api FROM ppc_product_process_api_temp a WHERE a.arg_type = '0' AND a.isactive=1 AND a.appkey ='"+appKey+"')");
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

	@Override
	public void save(ProcessConfigManageDTO pm) throws Exception{
		em.persist(pm);
	}

	@Override
	public ProcessConfigManageDTO updateQuery(String appKey, String id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ppc_product_process_api_temp WHERE isactive=1 AND appkey = '"+appKey+"' AND id = '"+id+"' ");
		Query query =  em.createNativeQuery(sql.toString(), ProcessConfigManageDTO.class);
		List<ProcessConfigManageDTO> list = query.getResultList();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateConfig(String appKey, String id, String orders, String alias, int isActive)  throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT * FROM ppc_product_process_api_temp WHERE appkey = '"+appKey+"' AND id='"+id+"' AND isactive=1 ");
		Query query = em.createNativeQuery(sql.toString(), ProcessConfigManageDTO.class);
		List<ProcessConfigManageDTO> list = query.getResultList();
		if(list != null && list.size() > 0){
			ProcessConfigManageDTO pm = list.get(0);
			pm.setOrders(Integer.parseInt(orders));
			pm.setAlias(alias);
			pm.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			em.merge(pm);
		}
	}

	@Override
	public void delete(String appKey, String id, String apiType, String api, int isActive)  throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT * FROM ppc_product_process_api_temp WHERE isactive = 1 AND api = '"+api+"' and appkey = '"+appKey+"' ");
		Query query = em.createNativeQuery(sql.toString(), ProcessConfigManageDTO.class);
		List<ProcessConfigManageDTO> list = query.getResultList();
		if(list != null && list.size() > 0){
			for(ProcessConfigManageDTO pm : list){
				pm.setIsActive((byte) 0);
				pm.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				em.merge(pm);
			}
		}
	}

	@Override
	public void init(String appKey) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM  ppc_product_process_api_temp WHERE isactive=1 AND appkey='"+appKey+"' ");
		Query query = em.createNativeQuery(sql.toString(),ProcessConfigManageDTO.class);
		List<ProcessConfigManageDTO> list = query.getResultList();
		if(list != null && list.size() > 0){
			for(ProcessConfigManageDTO pm : list){
				pm.setIsActive((byte) 0);
				pm.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				em.merge(pm);
			}
		}
		StringBuffer str = new StringBuffer();
		str.append(" SELECT i.* FROM ppc_product_api_config c,ppc_product_api_interface i WHERE ");
		str.append(" c.API = i.API AND c.isactive=1 AND i.argument_type=0 AND c.appkey='"+appKey+"' ");
		Query qy = em.createNativeQuery(str.toString(),ApiManageInterfaceDTO.class);
		List<ApiManageInterfaceDTO> li = qy.getResultList();
		ProcessConfigManageDTO pm = null;
		for(ApiManageInterfaceDTO ad : li ){
			pm = new ProcessConfigManageDTO();
			pm.setAppKey(appKey);
			String api = ad.getApi();
			pm.setApi(api);
			pm.setProcess(1);
			pm.setOrders(1); // 执行顺序默认为1
			pm.setApiType(ad.getApiType());
			String argument = ad.getArgument();
			pm.setArgument(argument);
			String argType = ad.getArgumentType();
			pm.setArgType(argType);
			String alias = api + "_" +argument;
			pm.setAlias(alias);
			em.persist(pm);
		}
	}

	@Override
	public void reset(String appKey) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM  ppc_product_process_api_temp WHERE isactive=1 AND appkey='"+appKey+"' ");
		Query query = em.createNativeQuery(sql.toString(),ProcessConfigManageDTO.class);
		List<ProcessConfigManageDTO> list = query.getResultList();
		if(list != null && list.size() > 0){
			for(ProcessConfigManageDTO pm : list){
				pm.setIsActive((byte) 0);
				pm.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				em.merge(pm);
			}
		}
		StringBuffer str = new StringBuffer();
		str.append("SELECT * FROM ppc_product_process_api WHERE arg_type=0 AND isactive=1 AND appkey='"+appKey+"'");
		Query qy = em.createNativeQuery(str.toString(),ProcessManageDTO.class);
		List<ProcessManageDTO> li = qy.getResultList();
		ProcessManageDTO pm = null;
		for(ProcessManageDTO ad : li ){
			pm = new ProcessManageDTO();
			pm.setAppKey(appKey);
			pm.setProcess(ad.getProcess());
			pm.setOrders(ad.getOrders());
			pm.setApi(ad.getApi());
			pm.setApiType(ad.getApiType());
			pm.setArgument(ad.getArgument());
			pm.setArgType(ad.getArgType());
			pm.setAlias(ad.getAlias());
			em.persist(pm);
		}
	}

	@Override
	public void submit(String appKey) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM  ppc_product_process_api WHERE isactive=1 AND appkey='"+appKey+"' ");
		Query query = em.createNativeQuery(sql.toString(),ProcessManageDTO.class);
		List<ProcessManageDTO> list = query.getResultList();
		if(list != null && list.size() > 0){
			for(ProcessManageDTO pm : list){
				pm.setIsActive((byte) 0);
				pm.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				em.merge(pm);
			}
		}
		StringBuffer str = new StringBuffer();
		str.append("SELECT * FROM ppc_product_process_api_temp WHERE arg_type=0 AND isactive=1 AND appkey='"+appKey+"'");
		Query qy = em.createNativeQuery(str.toString(),ProcessConfigManageDTO.class);
		List<ProcessConfigManageDTO> li = qy.getResultList();
		ProcessManageDTO pm = null;
		for(ProcessConfigManageDTO ad : li ){
			pm = new ProcessManageDTO();
			pm.setAppKey(appKey);
			pm.setProcess(ad.getProcess());
			pm.setOrders(ad.getOrders());
			pm.setApi(ad.getApi());
			pm.setApiType(ad.getApiType());
			pm.setArgument(ad.getArgument());
			pm.setArgType(ad.getArgType());
			pm.setAlias(ad.getAlias());
			em.persist(pm);
		}
	}
}
