package com.ppcredit.bamboo.backend.web.rest.admin.process.service;

import com.ppcredit.bamboo.backend.web.rest.admin.process.dto.ProcessDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProcessService {

	ProcessDTO save(String appKey, String processName, String processDesc) ;

	Pager query(String param, int offset, int pagesize);

    void delProcessByProcessId(String processId);

	ProcessDTO update(int id, String processName, String processDesc);

	List<ProcessDTO> queryProcessList();

	List<ProcessDTO> queryCheckedProcessList(String appkey);

    Pager queryByName(String param, int i, int pageSize);

	void saveProcessAppkey(String appKey, String ids) ;

}
