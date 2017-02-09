package com.ppcredit.bamboo.backend.web.rest.admin.login.dto;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 管理员操作日志实体类
 * @description 
 * @author yuankun
 * @created 2015-12-30 下午3:01:16
 */

@Entity
@Table(name = "PPDAI_SSO_OPERATION_LOG")
public class SSOUserOperationLogDTO {

	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid")
//	@GenericGenerator(name = "generator", strategy = "increment")
	@Column(name = "ID")
	private String id;
	
	@Column(name = "USER_LOGIN_NAME")
	private String userLoginName;
	
	@Column(name = "USER_REAL_NAME")
	private String userRealName;
	
	@Column(name = "OPERATION_URL")
	private String operationUrl;
	
	@Column(name = "OPERATION_TYPE")
	private String operationType;
	
	@Column(name = "OPERATION_CONTENT")
	private String operationContent;
	
	@Column(name = "OPERATION_IP")
	private String operationIp;
	
	@Column(name = "INSERTTIME")
	private Timestamp insertTime;
	
	@Column(name = "ISACTIVE")
	private byte isActive =1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getOperationUrl() {
		return operationUrl;
	}

	public void setOperationUrl(String operationUrl) {
		this.operationUrl = operationUrl;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationContent() {
		return operationContent;
	}

	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}

	public String getOperationIp() {
		return operationIp;
	}

	public void setOperationIp(String operationIp) {
		this.operationIp = operationIp;
	}

	public String getInsertTime() {
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(insertTime != null){
			return format.format(insertTime);
		}
		return "";
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}
	
	
}
