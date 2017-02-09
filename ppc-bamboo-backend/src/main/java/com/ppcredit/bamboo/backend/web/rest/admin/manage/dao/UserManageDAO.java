package com.ppcredit.bamboo.backend.web.rest.admin.manage.dao;

import com.ppcredit.bamboo.backend.web.rest.admin.util.Pager;
import org.springframework.stereotype.Component;

@Component
public interface UserManageDAO {

	public Pager query(String status, String searchCondition, int page, int pageSize);

}
