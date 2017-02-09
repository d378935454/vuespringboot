package com.ppcredit.bamboo.backend.web.rest.admin.api.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 
 * Title: APIMethodDTO.java    
 * Description: 接口表
 * @author yang_hx       
 * @created 2015-8-17 下午7:46:49
 */
@Entity
@Table(name = "PPDAI_API_METHOD")
public class APIMethodDTO {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "CLASS_NAME")
	private String className;
	
	@Column(name = "TEST_CLASS_NAME")
	private String testClassName;
	
	@Column(name = "INSERTTIME")
	private Date inTime;
	
	@Column(name = "UPDATETIME")
	private Date upTime;
	
	@Column(name = "ISACTIVE")
	private byte isActive;
	
	@Column(name = "DESC_NAME")
	private String descName;	
	
	@Column(name = "MARK")
	private String mark;
	
	@Column(name = "PRODUCT_ID")
	private String productId;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "REQUEST_PARAMS")
	private String requestParams;
	
	@Column(name = "RESPONSE_PARAMS")
	private String responseParams;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public Date getUpTime() {
		return upTime;
	}
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}
	
	public byte getIsActive() {
		return isActive;
	}
	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}
	public String getDescName() {
		return descName;
	}
	public void setDescName(String descName) {
		this.descName = descName;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getTestClassName() {
		return testClassName;
	}
	public void setTestClassName(String testClassName) {
		this.testClassName = testClassName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getRequestParams() {
		return requestParams;
	}
	public void setRequestParams(String requestParams) {
		this.requestParams = requestParams;
	}
	public String getResponseParams() {
		return responseParams;
	}
	public void setResponseParams(String responseParams) {
		this.responseParams = responseParams;
	}
}
