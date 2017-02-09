package com.ppcredit.bamboo.backend.web.rest.admin.step.service.impl;

import com.ppcredit.bamboo.backend.web.rest.admin.step.dao.ProcessStepDAO;
import com.ppcredit.bamboo.backend.web.rest.admin.step.dto.ProcessStepDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.step.service.ProcessStepService;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import com.ppcredit.bamboo.backend.web.rest.type.StepType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service("myProcessStepService")
public class ProcessStepServiceImpl implements ProcessStepService {

	private static Logger logger = LoggerFactory.getLogger(ProcessStepServiceImpl.class); // 日志
	
	@Inject
	private ProcessStepDAO myProcessStepDAO;

	@Override
	public Pager query(String processId, int offset, int pagesize, Integer isactive) {
		return myProcessStepDAO.query( processId, offset, pagesize,isactive);
	}

    @Override
    public ProcessStepDTO queryStepBystepId(String stepId) {
        return myProcessStepDAO.queryStepBystepId( stepId);
    }

    @Transactional
    @Override
    public void update(ProcessStepDTO processStepDTO) {
        myProcessStepDAO.update(processStepDTO);
    }

	@Override
	@Transactional
	public void delStepByStepId(String stepId) {
		myProcessStepDAO.delStepByStepId(stepId);
	}

	@Override
	public ProcessStepDTO save(String processId, String stepId, StepType stepType, String stepDesc, String orders, String nextOrders)
			throws Exception {
		
		ProcessStepDTO processStep = new  ProcessStepDTO();
		
		processStep.setStepId(stepId);
		processStep.setProcessId(processId);
		processStep.setStepType(stepType);
		processStep.setStepDesc(stepDesc);
		processStep.setOrders(orders);
		processStep.setNextOrders(nextOrders);
		
		myProcessStepDAO.save(processStep);
		return processStep;
	}
	
}
