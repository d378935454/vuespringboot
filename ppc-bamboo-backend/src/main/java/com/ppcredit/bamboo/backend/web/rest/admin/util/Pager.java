/*
 * @(#)Pager.java 2010-8-24下午11:03:05
 * Copyright 2013 sinovatech, Inc. All rights reserved.
 */
package com.ppcredit.bamboo.backend.web.rest.admin.util;
import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * <ul>
 * <li>
 * <b>修改历史：</b><br/>
 * <p>
 * [2010-8-24下午11:03:05]yang_hx<br/>
 * TODO
 * </p>
 * </li>
 * </ul>
 */
@SuppressWarnings("rawtypes")
public class Pager extends PageObject implements Serializable {
 	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since v 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
 	/**
 	 * 
 	 * 创建一个新的实例Pager.
 	 *
 	 */
	public Pager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * 创建一个新的实例Pager.
	 * 
	 * @param page	当前页
	 * @param pageSize	每页初始记录数
	 * @param path	分页请求URI
	 * @param pageList	分页数据
	 * @param count	总记录数
	 */
	public Pager(int page, int pageSize, List pageList, long count) {
		super();
		super.setCurrentPageNum(page);
		super.setPageSize(pageSize);
		super.setResultList(pageList);
		super.setTotalNum(count);
	}
	
}