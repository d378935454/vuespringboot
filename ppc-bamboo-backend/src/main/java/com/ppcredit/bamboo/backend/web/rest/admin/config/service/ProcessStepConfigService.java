package com.ppcredit.bamboo.backend.web.rest.admin.config.service;

import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.Param;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepArgumentDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepConfigDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepLogicDTO;
import com.ppcredit.bamboo.backend.web.rest.type.ArgType;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public interface ProcessStepConfigService {

	ProcessStepConfigDTO save(String stepId, String configId, String apiId, List<LinkedHashMap<String,String>>  inputList, List<LinkedHashMap<String,String>> outList) ;

	ProcessStepArgumentDTO save(String stepId,String configId, ArgType argType, List<Map<String, String>> argName) ;

	ProcessStepConfigDTO save(String type, String stepId,String configId, ArgType argType, String jarMethod, List<LinkedHashMap<String, String>> inputList,
							  List<LinkedHashMap<String, String>> codeList) ;
	List<Param> queryApiList(String appKey, String type);

	List<ProcessStepConfigDTO> queryConfigByStepId(String stepId);

	List<ProcessStepArgumentDTO> queryArgumentByConfigId(String configId,ArgType argType);

	List<ProcessStepLogicDTO> queryLogicByConfigId(String configId);

	void delArgumentByConfigId(String configId);

	ProcessStepConfigDTO updateLogic(String type, String stepId, String configId, ArgType argType, String jarMethod, List<LinkedHashMap<String,String>> argList, List<LinkedHashMap<String,String>> codeList);

	ProcessStepConfigDTO updateInterface(String stepId, String configId, String apiId, List<LinkedHashMap<String,String>> inputList, List<LinkedHashMap<String,String>> outList);

	List<ProcessStepArgumentDTO> queryArgumentByProcessId(String processId,String stepId);


	List queryConfigByprocessId(String processId,String stepId);

	ProcessStepArgumentDTO createOutParam(int configId, String argparam);

	/**
	 * 删除未被引用的关联出参
	 */
    void delRefenrenceArg();
}
