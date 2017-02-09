package com.ppcredit.bamboo.backend.web.rest.admin.process.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ppcredit.bamboo.backend.web.rest.admin.process.dao.ProcessDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.process.dto.ProcessDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.process.dto.ProcessJoinDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository("myProcessDAO")
public class ProcessDAOImpl implements ProcessDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(ProcessDTO process)  {
        em.persist(process);
    }

    @Override
    public Pager query(String param, int offset, int pagesize) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT p.* ");
        sql.append(" FROM PPDAI_API_KEYSET k, PPDAI_API_USER u, ppc_product_process p WHERE k.uid=u.id AND k.`APPKEY` = p.`APPKEY` and p.ISACTIVE=1");
        sql.append(" and p.APPKEY = '" + param + "'");
        Query query = em.createNativeQuery(sql.toString(), ProcessDTO.class);
        int count = query.getResultList().size();
        List pageList = query.setFirstResult(offset).setMaxResults(pagesize).getResultList();
        return new Pager(offset, pagesize, pageList, count);
    }

    @Override
    public void delProcessByProcessId(String processId) {
        ProcessDTO processDTO = em.createQuery("select p from ProcessDTO p where p.isActive=1 and p.processId=:processId", ProcessDTO.class)
                .setParameter("processId",processId)
                .getSingleResult();
        processDTO.setIsActive((byte) 0);
        em.flush();
    }

    @Override
    public ProcessDTO update(int id, String processName, String processDesc) {
        ProcessDTO processDTO= em.find(ProcessDTO.class, id);
        processDTO.setProcessName(processName);
        processDTO.setProcessDesc(processDesc);
        em.flush();
        return processDTO;
    }

	@Override
	public List<ProcessDTO> queryProcessList() {
			
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT process_id,process_name FROM `ppc_product_process` WHERE isactive='1' ");
		sql.append(" ORDER BY updatetime DESC ");
		Query query = em.createNativeQuery(sql.toString());
		List list = query.getResultList();
		List<ProcessDTO> li = new ArrayList();
		if(list != null && !list.isEmpty()){
			ProcessDTO ad ;
			int size = list.size();
			for(int i = 0;i < size; i++){
				Object[] obj = (Object[]) list.get(i);
				ad = new ProcessDTO();
				ad.setProcessId(String.valueOf(obj[0]));
				ad.setProcessName(String.valueOf(obj[1]));
				li.add(ad);
			}
		}
		return li;
	}
	
	@Override
	public List<ProcessDTO> queryCheckedProcessList(String appkey) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT process_id FROM ppc_product_process_join WHERE appkey='"+appkey+"' AND isactive='1' ");
		sql.append(" ORDER BY updatetime DESC ");
		Query query = em.createNativeQuery(sql.toString());
		List list = query.getResultList();
		List<ProcessDTO> li = new ArrayList();
		if(list != null && !list.isEmpty()){
			ProcessDTO ad ;
			int size = list.size();
			for(int i = 0;i < size; i++){
				String processId = (String) list.get(i);
				ad = new ProcessDTO();
				ad.setProcessId(processId);
				li.add(ad);
			}
		}
		return li;
	}

	@Override
	public void saveProcessAppkey(ProcessJoinDTO process) {
		em.persist(process);
	}

	@Override
	public void deleleAppKey(String appKey) {
		List<ProcessJoinDTO> list = em.createQuery("select p from ProcessJoinDTO p where p.isActive=1 and p.appKey=:appKey", ProcessJoinDTO.class)
                .setParameter("appKey",appKey).getResultList();
		if(list != null && list.size() > 0){
			for(ProcessJoinDTO pm : list){
				pm.setIsActive((byte) 0);
				pm.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				em.merge(pm);
			}
		}
	}

	@Override
	public Pager queryByName(String param, int i, int pageSize) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT p.* ");
		sql.append(" FROM PPDAI_API_KEYSET k, PPDAI_API_USER u, ppc_product_process p WHERE k.uid=u.id AND k.`APPKEY` = p.`APPKEY` and p.ISACTIVE=1");
		sql.append("".equals(param)?"":" and p.PROCESS_NAME like '%" + param + "%' ");
		sql.append(" order by p.id desc ");
		Query query = em.createNativeQuery(sql.toString(), ProcessDTO.class);
		int count = query.getResultList().size();
		List pageList = query.setFirstResult(i).setMaxResults(pageSize).getResultList();
		return new Pager(i, pageSize, pageList, count);
	}

}
