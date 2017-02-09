package com.ppcredit.bamboo.backend.web.rest.admin.response.dto;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ppc_product_api_return")
public class APIReturnInfo {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private BigInteger id;

	@Column(name = "APPKEY")
	private String appKey;
	@Column(name = "CALLBACK_TYPE")
	private String callback_type;
	@Column(name = "CALLBACK_URL")
	private String callback_url;
	@Column(name = "NOTIFY_TYPE")
	private String notify_type;
	@Column(name = "NOTIFY_URL")
	private String notify_url;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getCallback_type() {
		return callback_type;
	}

	public void setCallback_type(String callback_type) {
		this.callback_type = callback_type;
	}

	public String getCallback_url() {
		return callback_url;
	}

	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
}
