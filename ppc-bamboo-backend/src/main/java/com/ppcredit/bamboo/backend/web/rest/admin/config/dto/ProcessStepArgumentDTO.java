package com.ppcredit.bamboo.backend.web.rest.admin.config.dto;

import com.ppcredit.bamboo.backend.web.rest.type.ArgType;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 流程节点参数表
 */
@Entity
@Table(name = "ppc_product_process_step_argument")
public class ProcessStepArgumentDTO {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private int id;
	
	@Column(name = "CONFIG_ID")
	private String configId; 			// 节点配置id
	
	@Column(name = "ARG_TYPE")
	@Enumerated(EnumType.ORDINAL)
	private ArgType argType; 			// 参数类型
	
	@Column(name = "ARG_NAME")
	private String argName; 			// 参数名称
	
	@Column(name = "ALIAS")
	private String alias; 				// 参数别名
	
	@Column(name = "ARG_RULES")
	private String argRules; 			// 参数校验规则
	
	@Column(name = "ARG_LENGTH")
	private String argLength; 			// 参数校验长度
	
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

	public ArgType getArgType() {
		return argType;
	}

	public void setArgType(ArgType argType) {
		this.argType = argType;
	}

	public String getArgName() {
		return argName;
	}

	public void setArgName(String argName) {
		this.argName = argName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getArgRules() {
		return argRules;
	}

	public void setArgRules(String argRules) {
		this.argRules = argRules;
	}

	public String getArgLength() {
		return argLength;
	}

	public void setArgLength(String argLength) {
		this.argLength = argLength;
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
