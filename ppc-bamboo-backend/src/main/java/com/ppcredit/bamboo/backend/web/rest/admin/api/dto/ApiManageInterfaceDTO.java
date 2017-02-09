package com.ppcredit.bamboo.backend.web.rest.admin.api.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 接口参数表
 * date: 2016-11-24 16:18:34
 */
@Entity
@Table(name = "PPC_PRODUCT_API_INTERFACE")
public class ApiManageInterfaceDTO {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id;
	
	@Column(name = "API")
	private String api;
	
	@Column(name = "API_TYPE")
	private String apiType;
	
	@Column(name = "ARGUMENT")
	private String argument;
	
	@Column(name = "ARGUMENT_NAME")
	private String argumentName;
	
	@Column(name = "ARGUMENT_TYPE")
	private String argumentType;
	
	@Column(name = "ISACTIVE")
	private byte isActive = 1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getArgumentName() {
		return argumentName;
	}

	public void setArgumentName(String argumentName) {
		this.argumentName = argumentName;
	}

	public String getArgumentType() {
		return argumentType;
	}

	public void setArgumentType(String argumentType) {
		this.argumentType = argumentType;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	
}