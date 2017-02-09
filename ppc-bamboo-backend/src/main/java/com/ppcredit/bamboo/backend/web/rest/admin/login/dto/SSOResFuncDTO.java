package com.ppcredit.bamboo.backend.web.rest.admin.login.dto;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PPC_PRODUCT_SSO_RSC_FUNC")
public class SSOResFuncDTO implements Serializable {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "REMARK")
	private String remark;
	
	@Column(name = "ICON")
	private String icon;
	
	@Column(name = "PARENTID")
	private String parentid;
	
	@Column(name = "URL")
	private String url;
	
	

	@Column(name = "OUTERADDR")
	private String outerAddr;
	
	
	@Column(name = "HTTPMETHOD")
	private String method;

	@Column(name = "SORTID")
	private String sortid;
	
	@Column(name = "ISACTIVE")
	private byte isActive =1;
	
	@Transient
	private String parentName;
	
	@Column(name = "LEVEL")
	private byte level;
	
	public byte getLevel() {
		return level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getSortid() {
		return sortid;
	}

	public void setSortid(String sortid) {
		this.sortid = sortid;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getOuterAddr() {
		return outerAddr;
	}

	public void setOuterAddr(String outerAddr) {
		this.outerAddr = outerAddr;
	}
	
	
}
