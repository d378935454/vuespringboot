package com.ppcredit.bamboo.backend.web.rest.admin.request.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PPC_PRODUCT_PROCESS_API")
public class ProcessManageDTO {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private int id;
	
	@Column(name = "APPKEY")
	private String appKey; // appkey
	
	@Column(name = "PROCESS")
	private int process; // 流程号
	
	@Column(name = "ORDERS")
	private int orders; // 执行顺序
	
	@Column(name = "API")
	private String api; // 服务名称
	
	@Column(name = "API_TYPE")
	private String apiType; // 服务类型
	
	@Column(name = "ARGUMENT")
	private String argument; // 参数
	
	@Column(name = "ARG_TYPE")
	private String argType; // 参数类型
	
	@Column(name = "ALIAS")
	private String Alias; // 标准别名

	@Column(name = "UPDATETIME")
	private Timestamp updateTime;
	
	@Column(name = "ISACTIVE")
	private byte isActive = 1;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}

	public String getArgument() {
		return argument;
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}

	public String getArgType() {
		return argType;
	}

	public void setArgType(String argType) {
		this.argType = argType;
	}

	public String getAlias() {
		return Alias;
	}

	public void setAlias(String alias) {
		Alias = alias;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}
	
}
