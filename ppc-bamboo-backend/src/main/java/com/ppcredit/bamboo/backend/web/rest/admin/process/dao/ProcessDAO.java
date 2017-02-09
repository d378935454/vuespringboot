package com.ppcredit.bamboo.backend.web.rest.admin.process.dao;

import com.ppcredit.bamboo.backend.web.rest.admin.process.dto.ProcessDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.process.dto.ProcessJoinDTO;
import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProcessDAO {

	void save(ProcessDTO process) ;

	Pager query(String param, int offset, int pagesize);

    void delProcessByProcessId(String processId);

	ProcessDTO update(int id, String processName, String processDesc);

	List<ProcessDTO> queryProcessList();
	
	List<ProcessDTO> queryCheckedProcessList(String appkey);

	void saveProcessAppkey(ProcessJoinDTO process);

    Pager queryByName(String param, int i, int pageSize);

	void deleleAppKey(String appKey);
}
