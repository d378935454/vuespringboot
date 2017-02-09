package com.ppcredit.bamboo.backend.web.rest.admin.response.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 接口配置表
 * date: 2016-11-24 16:19:26
 */
@Entity
@Table(name = "PPC_PRODUCT_API_CONFIG")
public class ApiManageConfigDTO {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "APPKEY")
	private String appKey;
	
	@ManyToOne(targetEntity = ApiManageInterfaceDTO.class)
	@JoinColumn(name = "API", insertable = false, updatable = false)
	private ApiManageInterfaceDTO face; // API名称
	
	@Column(name = "API")
	private String api;
	
	@Column(name = "API_TYPE")
	private String apiType; // API类型 : 0 接口 1认证

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

	public ApiManageInterfaceDTO getFace() {
		return face;
	}

	public void setFace(ApiManageInterfaceDTO face) {
		this.face = face;
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

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}
	
}
