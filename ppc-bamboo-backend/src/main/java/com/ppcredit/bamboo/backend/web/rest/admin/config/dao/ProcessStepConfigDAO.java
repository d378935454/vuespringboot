package com.ppcredit.bamboo.backend.web.rest.admin.config.dao;

import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.Param;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepArgumentDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepConfigDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepLogicDTO;
import com.ppcredit.bamboo.backend.web.rest.type.ArgType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProcessStepConfigDAO {

	void save(ProcessStepConfigDTO psf) ;
	void save(ProcessStepArgumentDTO psa) ;

	void save(ProcessStepConfigDTO psf, ProcessStepArgumentDTO psa, ProcessStepLogicDTO psl);

	List<Param> queryApiList(String appKey, String type);

    List<ProcessStepConfigDTO> queryConfigByStepId(String stepId);

	List<ProcessStepArgumentDTO> queryArgumentByConfigId(String stepId,ArgType argType);

	List<ProcessStepLogicDTO> queryLogicByConfigId(String stepId);

	void delArgumentByConfigId(String configId);

	void delLogicByConfigId(String configId);

	void updateConfig(ProcessStepConfigDTO psf);

	List<ProcessStepArgumentDTO> queryArgumentByProcessId(String processId,String stepId);

	List queryConfigByprocessId(String processId, String stepId);

	ProcessStepArgumentDTO createOutParam(int configId, String argparam);

	/**
	 * 删除未被引用的关联出参
	 */
	void delRefenrenceArg();

    ProcessStepArgumentDTO queryArgumentById(Integer alias);
}
