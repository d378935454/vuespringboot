package com.ppcredit.bamboo.backend.web.rest.admin.step.dao;

import com.ppcredit.bamboo.backend.web.rest.admin.step.dto.ProcessStepDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Component;

@Component
public interface ProcessStepDAO {

	void save(ProcessStepDTO processStep);

	Pager query(String processId, int page, int pageSize, Integer isactive);

    ProcessStepDTO queryStepBystepId(String stepId);

    void update(ProcessStepDTO processStepDTO);

    void delStepByStepId(String stepId);
}

