package com.ppcredit.bamboo.backend.web.rest.admin.step.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.ppcredit.bamboo.backend.web.rest.admin.step.dao.ProcessStepDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.step.dto.ProcessStepDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Repository;

@Transactional
@Repository("myProcessStepDAO")
public class ProcessStepDAOImpl implements ProcessStepDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Pager query(String processId, int page, int pageSize, Integer isactive ) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT s.* ");
        sql.append("  FROM ppc_product_process p, ppc_product_process_step s WHERE p.process_id = s.PROCESS_ID  ");
        sql.append("  AND p.process_id = '" + processId +"'"+ (isactive==null?"":(" AND s.isactive= "+isactive)));
        sql.append(" ORDER BY s.orders ");
        int count = count(sql.toString());
        List<ProcessStepDTO> pageList = queryList(sql.toString(), page, pageSize);
        if (em != null) {
            em.close();
        }
        return new Pager(page, pageSize, pageList, count);
    }

    @Override
    public ProcessStepDTO queryStepBystepId(String stepId) {
        Query query = em.createQuery("select Step from " +
                " ProcessStepDTO Step where Step.stepId=:stepId and Step.isActive=1", ProcessStepDTO.class)
                .setParameter("stepId", stepId);

        return query.getResultList().isEmpty() ? null : (ProcessStepDTO) query.getSingleResult();

    }

    @Override
    public void update(ProcessStepDTO processStepDTO) {
        em.merge(processStepDTO);
    }

    @Override
    public void delStepByStepId(String stepId) {
        ProcessStepDTO processStepDTO = queryStepBystepId(stepId);
        processStepDTO.setIsActive((byte)0);
        em.flush();
    }

    @SuppressWarnings("unchecked")
    private int count(String sql) {
        Query query = em.createNativeQuery(sql, ProcessStepDTO.class);
        List<ProcessStepDTO> list = query.getResultList();
        return list.size();
    }

    @SuppressWarnings("unchecked")
    private List<ProcessStepDTO> queryList(String sql, int page, int pageSize) {
        Query query = em.createNativeQuery(sql, ProcessStepDTO.class);
        int start = (page > 0) ? page : 0;
        List<ProcessStepDTO> pageList = query.setFirstResult(start).setMaxResults(pageSize).getResultList();
        return pageList;
    }

    @Override
    public void save(ProcessStepDTO processStep) {
        em.persist(processStep);
    }

}
