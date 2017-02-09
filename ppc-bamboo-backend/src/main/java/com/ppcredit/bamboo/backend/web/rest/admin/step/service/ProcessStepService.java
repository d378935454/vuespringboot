package com.ppcredit.bamboo.backend.web.rest.admin.step.service;

import com.ppcredit.bamboo.backend.web.rest.admin.step.dto.ProcessStepDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import com.ppcredit.bamboo.backend.web.rest.type.StepType;
import org.springframework.stereotype.Component;

@Component
public interface ProcessStepService {

	ProcessStepDTO save(String processId, String stepId, StepType stepType, String stepDesc, String orders, String nextOrders) throws Exception;

	Pager query(String processId, int offset, int pagesize, Integer isactive);

    ProcessStepDTO queryStepBystepId(String stepId);

	void update(ProcessStepDTO processStepDTO);

	/**
	 * 根据stepid删除step
	 * @param stepId
	 */
	void delStepByStepId(String stepId);
}
