package com.ppcredit.bamboo.backend.web.rest.admin.step.dto;

import com.ppcredit.bamboo.backend.web.rest.type.StepType;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "ppc_product_process_step")
public class ProcessStepDTO {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private int id;
	
	@Column(name = "STEP_ID")
	private String stepId; 				// 节点id
	
	@Column(name = "PROCESS_ID")
	private String processId;			// 流程编号
	
	@Column(name = "STEP_TYPE")
	@Enumerated(EnumType.ORDINAL)
	private StepType stepType;				// 节点类型
	
	@Column(name = "STEP_DESC")
	private String stepDesc;			// 节点描述
	
	@Column(name = "ORDERS")
	private String orders;				// 节点编号
	
	@Column(name = "NEXT_ORDERS")
	private String nextOrders;			// 下一节点编号
	
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

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public StepType getStepType() {
		return stepType;
	}

	public void setStepType(StepType stepType) {
		this.stepType = stepType;
	}

	public String getStepDesc() {
		return stepDesc;
	}

	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
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
