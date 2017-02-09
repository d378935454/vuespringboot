package com.ppcredit.bamboo.backend.web.rest.admin.process.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ppc_product_process")
public class ProcessDTO {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private int id;
	
	@Column(name = "APPKEY")
	private String appKey; 				// appkey
	
	@Column(name = "PROCESS_ID")
	private String processId;			// 流程编号
	
	@Column(name = "PROCESS_NAME")
	private String processName;			// 流程名称
	
	@Column(name = "PROCESS_DESC")
	private String processDesc; 		// 流程描述
	
	@Column(name = "IS_OVER")
	private byte isOver = 0; 			// 流程配置结束标识 0 未结束，1结束
	
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

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessDesc() {
		return processDesc;
	}

	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}

	public byte getIsOver() {
		return isOver;
	}

	public void setIsOver(byte isOver) {
		this.isOver = isOver;
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
