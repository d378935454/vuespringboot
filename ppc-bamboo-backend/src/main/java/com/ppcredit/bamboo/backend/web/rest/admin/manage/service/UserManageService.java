package com.ppcredit.bamboo.backend.web.rest.admin.manage.service;

import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Component;

@Component
public interface UserManageService {

	public Pager query(String status, String searchCondition, int offset, int pagesize);

}
