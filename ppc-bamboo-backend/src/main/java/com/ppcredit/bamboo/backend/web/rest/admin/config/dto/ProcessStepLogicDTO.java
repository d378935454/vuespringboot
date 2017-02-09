package com.ppcredit.bamboo.backend.web.rest.admin.config.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 流程节点逻辑表
 */
@Entity
@Table(name = "ppc_product_process_step_logic")
public class ProcessStepLogicDTO {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private int id;
	
	@Column(name = "CONFIG_ID")
	private String configId; 			// 节点配置id
	
	@Column(name = "RETURN_CODE")
	private String returnCode; 			// 逻辑判断结果
	
	@Column(name = "NEXT_ORDERS")
	private String nextOrders; 			// 逻辑判断结果
	
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

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getNextOrders() {
		return nextOrders;
	}

	public void setNextOrders(String nextOrders) {
		this.nextOrders = nextOrders;
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
