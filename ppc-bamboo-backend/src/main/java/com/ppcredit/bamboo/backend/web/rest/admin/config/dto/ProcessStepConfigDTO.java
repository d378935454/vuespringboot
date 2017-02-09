package com.ppcredit.bamboo.backend.web.rest.admin.config.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 流程节点配置表
 */
@Entity
@Table(name = "ppc_product_process_step_config")
public class ProcessStepConfigDTO {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private int id;
	
	@Column(name = "CONFIG_ID")
	private String configId; 			// 节点配置id
	
	@Column(name = "STEP_ID")
	private String stepId; 				// 节点id
	
	@Column(name = "API")
	private String api; 				// 接口主键编号
	
	@Column(name = "JAR_METHOD")
	private String jarMethod; 			// 逻辑名称
	
	@Column(name = "TYPE")
	private String type; 				// 逻辑类型
	
	@Column(name = "UPDATETIME")
	private Timestamp updateTime; 		// 修改时间
	
	@Column(name = "ISACTIVE")
	private byte isActive = 1; 			// 逻辑删除 0 删除，1正常

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getJarMethod() {
		return jarMethod;
	}

	public void setJarMethod(String jarMethod) {
		this.jarMethod = jarMethod;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
