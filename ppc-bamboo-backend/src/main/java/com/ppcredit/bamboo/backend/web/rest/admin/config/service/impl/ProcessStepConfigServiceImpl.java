package com.ppcredit.bamboo.backend.web.rest.admin.config.service.impl;

import com.ppcredit.bamboo.backend.web.rest.admin.config.dao.ProcessStepConfigDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.Param;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepArgumentDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepConfigDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.dto.ProcessStepLogicDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.config.service.ProcessStepConfigService;
import com.ppcredit.bamboo.backend.web.rest.type.ArgType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNumeric;


@Service("myProcessStepConfigService")
public class ProcessStepConfigServiceImpl implements ProcessStepConfigService {

    private static final String NAME = "name";
    private static final String ALIAS = "alias";
    private static final String ARGNAME = "argument";
    private static final String RETURNCODE = "returnCode";
    private static final String NEXTORDER = "nextOrder";
    private static final String ARGUMENTNAME = "argumentName";
    @Inject
    private ProcessStepConfigDAO myProcessStepConfigDAO;

	@Override
	public List<Param> queryApiList(String appKey,String type) {
		return myProcessStepConfigDAO.queryApiList(appKey,type);
	}

    @Override
    public List<ProcessStepConfigDTO> queryConfigByStepId(String stepId) {
        return myProcessStepConfigDAO.queryConfigByStepId( stepId);

    }

    @Override
    public List<ProcessStepArgumentDTO> queryArgumentByConfigId(String configId,ArgType argType) {
        List<ProcessStepArgumentDTO> processStepArgumentDTOs=myProcessStepConfigDAO.queryArgumentByConfigId( configId,argType);
        for (ProcessStepArgumentDTO p:processStepArgumentDTOs){
            if(isNumeric(p.getAlias())){//是数字，代表是关联出参
                ProcessStepArgumentDTO processStepArgumentDTO= myProcessStepConfigDAO.queryArgumentById(Integer.valueOf(p.getAlias()));
                p.setArgRules(processStepArgumentDTO.getArgName());
            }
        }
        return processStepArgumentDTOs;
    }

    @Override
    public List<ProcessStepLogicDTO> queryLogicByConfigId(String configId) {
        return myProcessStepConfigDAO.queryLogicByConfigId( configId);
    }

    @Override
    @Transactional
    public void delArgumentByConfigId(String configId) {
         myProcessStepConfigDAO.delArgumentByConfigId( configId);
    }

    @Override
    @Transactional
    public ProcessStepConfigDTO updateLogic(String type, String stepId, String configId, ArgType argType, String jarMethod, List<LinkedHashMap<String, String>> argList, List<LinkedHashMap<String, String>> codeList) {
        ProcessStepConfigDTO psf = myProcessStepConfigDAO.queryConfigByStepId(stepId).get(0);
        psf.setConfigId(configId);
        psf.setStepId(stepId);
        psf.setJarMethod(jarMethod);
        psf.setType(type);

        myProcessStepConfigDAO.delArgumentByConfigId(configId);
        myProcessStepConfigDAO.delLogicByConfigId(configId);

        for (Map<String, String> map:argList){
            ProcessStepArgumentDTO psa = new ProcessStepArgumentDTO();
            psa.setConfigId(configId);
            psa.setArgType(argType);
            psa.setArgName(map.get(NAME));
            psa.setAlias(String.valueOf(map.get(ALIAS)));
            myProcessStepConfigDAO.save(null, psa, null);
        }

        for (Map<String, String> map:codeList) {
            ProcessStepLogicDTO psl = new ProcessStepLogicDTO();
            psl.setConfigId(configId);
            psl.setReturnCode(map.get(RETURNCODE));
            psl.setNextOrders(String.valueOf(map.get(NEXTORDER)));
            myProcessStepConfigDAO.save(null, null, psl);
        }
        myProcessStepConfigDAO.updateConfig(psf);
        return psf;
    }

    @Override
    @Transactional
    public ProcessStepConfigDTO updateInterface(String stepId, String configId, String apiId, List<LinkedHashMap<String, String>> inputList, List<LinkedHashMap<String, String>> outList) {
        ProcessStepConfigDTO psf = myProcessStepConfigDAO.queryConfigByStepId(stepId).get(0);
        psf.setConfigId(configId);
        psf.setStepId(stepId);
        psf.setApi(apiId);
        myProcessStepConfigDAO.updateConfig(psf);
        myProcessStepConfigDAO.delArgumentByConfigId(configId);

        for(LinkedHashMap<String,String> map:inputList){
            ProcessStepArgumentDTO psa = new ProcessStepArgumentDTO();
            psa.setConfigId(configId);
            psa.setArgType(ArgType.INPUT);
            psa.setArgName(map.get(ARGNAME));
            psa.setAlias(String.valueOf(map.get(ARGUMENTNAME)));
            myProcessStepConfigDAO.save(null, psa, null);
        }
        for(LinkedHashMap<String,String> map:outList){
            ProcessStepArgumentDTO psa = new ProcessStepArgumentDTO();
            psa.setConfigId(configId);
            psa.setArgType(ArgType.OUT);
            psa.setArgName(map.get(ARGNAME));
            psa.setAlias(map.get(ARGUMENTNAME));
            myProcessStepConfigDAO.save(null, psa, null);
        }

        return psf;
    }

    @Override
    public List<ProcessStepArgumentDTO> queryArgumentByProcessId(String processId,String stepId) {
        return  myProcessStepConfigDAO.queryArgumentByProcessId(processId, stepId);
    }

    @Override
    public List queryConfigByprocessId(String processId,String stepId) {
        return myProcessStepConfigDAO.queryConfigByprocessId(processId, stepId);
    }

    @Override
    public ProcessStepArgumentDTO createOutParam(int configId, String argparam)  {
      return  myProcessStepConfigDAO.createOutParam(configId, argparam);
    }
    /**
     * 删除未被引用的关联出参
     */
    @Override
    public void delRefenrenceArg() {
        myProcessStepConfigDAO.delRefenrenceArg();
    }

    @Override
    @Transactional
    public ProcessStepConfigDTO save(String stepId,
                                     String configId,
                                     String apiId,
                                     List<LinkedHashMap<String,String>> inputList,
                                     List<LinkedHashMap<String,String>> outList)
             {

        ProcessStepConfigDTO psf = new ProcessStepConfigDTO();
        psf.setConfigId(configId);
        psf.setStepId(stepId);
        psf.setApi(apiId);
        myProcessStepConfigDAO.save(psf, null, null);
        for(LinkedHashMap<String,String> map:inputList){
            ProcessStepArgumentDTO psa = new ProcessStepArgumentDTO();
            psa.setConfigId(configId);
            psa.setArgType(ArgType.INPUT);
            psa.setArgName(map.get(ARGNAME));
            psa.setAlias(String.valueOf(map.get(ARGUMENTNAME)));
            myProcessStepConfigDAO.save(null, psa, null);
        }
        for(LinkedHashMap<String,String> map:outList){
            ProcessStepArgumentDTO psa = new ProcessStepArgumentDTO();
            psa.setConfigId(configId);
            psa.setArgType(ArgType.OUT);
            psa.setArgName(map.get(ARGNAME));
            psa.setAlias(map.get(ARGUMENTNAME));
            myProcessStepConfigDAO.save(null, psa, null);
        }




        return psf;
    }

	@Override
    @Transactional
	public ProcessStepArgumentDTO save(String stepId,String configId, ArgType argType, List<Map<String,String>> list)  {
		
		ProcessStepConfigDTO psf = new ProcessStepConfigDTO();
		psf.setConfigId(configId);
		psf.setStepId(stepId);
		myProcessStepConfigDAO.save(psf);
		
		ProcessStepArgumentDTO psa = null;
		if(list != null && !list.isEmpty()){
			for(Map<String,String> arg : list){
				psa = new ProcessStepArgumentDTO();
				psa.setConfigId(configId);
				psa.setArgType(argType);
				psa.setArgName(arg.get(ARGNAME));
				psa.setAlias(String.valueOf(arg.get(ALIAS)));
				myProcessStepConfigDAO.save(psa);
			}
		}
		
		return psa;
	}

    @Transactional
    @Override
    public ProcessStepConfigDTO save(String type, String stepId,String configId, ArgType argType, String jarMethod,
                                       List<LinkedHashMap<String, String>> inputList,
                                       List<LinkedHashMap<String, String>> codeList)  {

        ProcessStepConfigDTO psf = new ProcessStepConfigDTO();
        psf.setConfigId(configId);
        psf.setStepId(stepId);
        psf.setJarMethod(jarMethod);
        psf.setType(type);
        myProcessStepConfigDAO.save(psf, null, null);
        for (LinkedHashMap<String, String> map:inputList){
            ProcessStepArgumentDTO psa = new ProcessStepArgumentDTO();
            psa.setConfigId(configId);
            psa.setArgType(argType);
            psa.setArgName(map.get(NAME));
            psa.setAlias(String.valueOf(map.get(ALIAS)));
            myProcessStepConfigDAO.save(null, psa, null);
        }

        for (LinkedHashMap<String, String> map:codeList) {
            ProcessStepLogicDTO psl = new ProcessStepLogicDTO();
            psl.setConfigId(configId);
            psl.setReturnCode(map.get(RETURNCODE));
            psl.setNextOrders(String.valueOf(map.get(NEXTORDER)));
            myProcessStepConfigDAO.save(null, null, psl);
        }

        return psf;
    }


}
