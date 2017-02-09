package com.ppcredit.bamboo.backend.web.rest.admin.config.dao.impl;

import com.ppcredit.bamboo.backend.web.rest.admin.config.dao.ProcessStepConfigDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.Param;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepArgumentDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepConfigDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepLogicDTO;
import com.ppcredit.bamboo.backend.web.rest.type.ArgType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Transactional
@Repository("myProcessStepConfigDAO")
public class ProcessStepConfigDAOImpl implements ProcessStepConfigDAO {

    private static String configID = "ConfigId";
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(ProcessStepConfigDTO psf) {
        em.persist(psf);
    }

    @Override
    public void save(ProcessStepArgumentDTO psa) {
        em.persist(psa);
    }

    @Override
    public void save(ProcessStepConfigDTO psf, ProcessStepArgumentDTO psa, ProcessStepLogicDTO psl) {
        if (null != psf) {
            em.persist(psf);
        }
        if (null != psa) {
            em.persist(psa);
        }
        if (null != psl) {
            em.persist(psl);
        }
    }


    @SuppressWarnings("rawtypes")
    @Override
    public List<Param> queryApiList(String appKey, String type) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT i.api,d.REMARK  FROM ppc_product_api_interface i  LEFT JOIN ppdai_third_dictionary d ON i.api = d.channel_source_name where 1=1  ");
        sql.append("AND i.isactive = 1");
        if (StringUtils.isNotBlank(type)) {
            sql.append(" AND i.api_type = '" + type + "'");
        }
        sql.append("GROUP BY i.API;");
        Query query = em.createNativeQuery(sql.toString());
        List list = query.getResultList();
        List<Param> li = new ArrayList();

        if (list != null && !list.isEmpty()) {

            for (Object aList : list) {
                Param ad = new Param();
                Object[] obj = (Object[]) aList;
                ad.setApi(String.valueOf(obj[0]));
                ad.setApiName(String.valueOf(obj[1]));

                StringBuilder sqlP = new StringBuilder();
                sqlP.append("SELECT  i.argument, i.ARGUMENT_TYPE, i.ARGUMENT_NAME FROM ppc_product_api_interface i where i.api='" + obj[0].toString() + "'");
                Query queryP = em.createNativeQuery(sqlP.toString());
                List listP = queryP.getResultList();
                List<ConcurrentMap<String, String>> list1 = new ArrayList();
                for (Object aListP : listP) {

                    Object[] objP = (Object[]) aListP;
                    ConcurrentHashMap<String, String> map = new ConcurrentHashMap();
                    map.put("argument", String.valueOf(objP[0]));
                    map.put("argumentType", String.valueOf(objP[1]));
                    map.put("argumentName", String.valueOf(objP[2]));
                    list1.add(map);
                }
                ad.setArgList(list1);
                li.add(ad);
            }
        }
        return li;
    }

    @Override
    public List<ProcessStepConfigDTO> queryConfigByStepId(String stepId) {
        return em.createQuery("select p from ProcessStepConfigDTO p where p.stepId=:stepId and p.isActive=1", ProcessStepConfigDTO.class)
                .setParameter("stepId", stepId)
                .getResultList();
    }

    @Override
    public List<ProcessStepArgumentDTO> queryArgumentByConfigId(String configId, ArgType argType) {
        return em.createQuery("select p from ProcessStepArgumentDTO p where p.configId=:ConfigId and p.isActive=1 " + (argType == null ? "" : "and p.argType=:argType"), ProcessStepArgumentDTO.class)
                .setParameter(configID, configId)
                .getResultList();
    }

    @Override
    public List<ProcessStepLogicDTO> queryLogicByConfigId(String configId) {
        return em.createQuery("select p from ProcessStepLogicDTO p where p.configId=:ConfigId and p.isActive=1", ProcessStepLogicDTO.class)
                .setParameter(configID, configId)
                .getResultList();
    }

    @Override
    public void delArgumentByConfigId(String configId) {
        List<ProcessStepArgumentDTO> processStepArgumentDTOs = em.createQuery("select p from ProcessStepArgumentDTO p where p.configId=:ConfigId and p.isActive=1", ProcessStepArgumentDTO.class)
                .setParameter(configID, configId)
                .getResultList();
        for (ProcessStepArgumentDTO i : processStepArgumentDTOs) {
            i.setIsActive((byte) 0);
        }
        em.flush();
    }

    @Override
    public void delLogicByConfigId(String configId) {
        List<ProcessStepLogicDTO> processStepLogicDTOs = em.createQuery("select p from ProcessStepLogicDTO p where p.configId=:ConfigId and p.isActive=1", ProcessStepLogicDTO.class)
                .setParameter(configID, configId)
                .getResultList();
        for (ProcessStepLogicDTO i : processStepLogicDTOs) {
            i.setIsActive((byte) 0);
        }
        em.flush();
    }

    @Override
    public void updateConfig(ProcessStepConfigDTO psf) {
        em.merge(psf);
    }

    @Override
    public List<ProcessStepArgumentDTO> queryArgumentByProcessId(String processId, String stepId) {
        return em.createQuery("select distinct psa from ProcessStepArgumentDTO psa " +
                        ", ProcessStepConfigDTO psc  " +
                        ", ProcessStepDTO ps " +
                        ", ProcessDTO p  " +
                        "where p.processId=:processId " +
                        "and psc.configId=psa.configId " +
                        "and p.processId=ps.processId " +
                        "and psc.stepId=ps.stepId  " +
                        "and ps.isActive=1 " +
                        "and psc.isActive=1 " +
                        "and psa.isActive=1 " +
                        "and psa.alias<>'' " +
                        "and psa.alias is not null " +
                        "and psa.argType=1 " +
                        "and ps.stepId<>:stepId"
                , ProcessStepArgumentDTO.class)
                .setParameter("processId", processId)
                .setParameter("stepId", stepId)
                .getResultList();
    }

    @Override
    public List queryConfigByprocessId(String processId, String stepId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT psc.id,ps.STEP_DESC,i.ARGUMENT from ppc_product_process_step_config psc \n" +
                "LEFT JOIN ppc_product_process_step ps ON psc.STEP_ID=ps.STEP_ID\n" +
                "LEFT JOIN ppc_product_api_interface i on i.API= psc.API\n" +
                "WHERE ps.PROCESS_ID='" + processId + "' AND ps.STEP_ID<>'" + stepId + "'\n" +
                "AND psc.API is not null and psc.API<>\"\" AND i.ARGUMENT_TYPE=1");
        Query query = em.createNativeQuery(sql.toString());
        return query.getResultList();
    }

    @Override
    public ProcessStepArgumentDTO createOutParam(int configId, String argparam) {
        ProcessStepConfigDTO processStepConfigDTO = em.find(ProcessStepConfigDTO.class, configId);
        List<ProcessStepArgumentDTO> parg = em.createQuery("select p from ProcessStepArgumentDTO p where p.configId=:configId and p.argName=:argparam and p.isActive=3"
                , ProcessStepArgumentDTO.class)
                .setParameter("configId", processStepConfigDTO.getConfigId())
                .setParameter("argparam", argparam).getResultList();
        if (parg == null || parg.isEmpty()) {
            ProcessStepArgumentDTO processStepArgumentDTO = new ProcessStepArgumentDTO();
            processStepArgumentDTO.setConfigId(processStepConfigDTO.getConfigId());
            processStepArgumentDTO.setArgName(argparam);
            processStepArgumentDTO.setArgType(ArgType.OUT);
            processStepArgumentDTO.setIsActive((byte) 3);
            processStepArgumentDTO.setAlias("");
            em.persist(processStepArgumentDTO);
            return processStepArgumentDTO;
        }
        return parg.get(0);
    }

    /**
     * 删除未被引用的关联出参
     */
    @Override
    public void delRefenrenceArg() {
        List<ProcessStepArgumentDTO> processStepArgumentDTOs = em.createNativeQuery("SELECT " +
                "p.* " +
                "FROM " +
                "ppc_product_process_step_argument p " +
                "LEFT JOIN ppc_product_process_step_argument p1 ON p.id = p1.ALIAS " +
                "WHERE " +
                "p.ISACTIVE = 3 " +
                "AND (p1.ID IS NULL OR p1.ISACTIVE = 0) ", ProcessStepArgumentDTO.class).getResultList();
        for (ProcessStepArgumentDTO  p:processStepArgumentDTOs){
            p.setIsActive((byte)0);
        }
        em.flush();
    }

    @Override
    public ProcessStepArgumentDTO queryArgumentById(Integer alias) {
        return em.find(ProcessStepArgumentDTO.class,alias);
    }
}
