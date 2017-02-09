package com.ppcredit.bamboo.backend.web.rest.admin.login.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Title: SSORoleDTO.java    
 * Description: 角色实体
 * @author yang_hx       
 * @created 2015-9-1 下午2:03:37    
 */
@Entity
@Table(name = "PPDAI_SSO_ROLE")
public class SSORoleDTO implements Serializable {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id;
	
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "AUTH_ADMIN")
	private byte authAdmin=0;
	
	
	@Column(name = "REMARK")
	private String remark;
	
	@Column(name = "LOCATIONID")
	private String locationid;

	
	@Column(name = "ISACTIVE")
	private byte isActive =1;
	
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

	public String getLocationid() {
		return locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public byte getAuthAdmin() {
		return authAdmin;
	}

	public void setAuthAdmin(byte authAdmin) {
		this.authAdmin = authAdmin;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}
	
	
}
