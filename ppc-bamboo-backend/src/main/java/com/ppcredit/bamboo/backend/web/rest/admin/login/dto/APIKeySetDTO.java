package com.ppcredit.bamboo.backend.web.rest.admin.login.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * Title: APIKeySetDTO.java    
 * Description: APPKEY实体类
 * @author yang_hx       
 * @created 2015-8-17 下午7:18:11
 */
@Entity
@Table(name = "PPDAI_API_KEYSET")
public class APIKeySetDTO {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id;
	
	@ManyToOne(targetEntity = APIUserDTO.class)
	@JoinColumn(name = "UID")
	private APIUserDTO user;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "APPKEY")
	private String appKey;
	
	@Column(name = "APPSECRET")
	private String appSecret;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "ISACTIVE")
	private byte isActive = 1;
	
	/*@Column(name = "INTIME")
	private Date inTime;
	
	@Column(name = "UPTIME")
	private Date upTime;*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*public Date getInTime() {
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
	}*/

	public APIUserDTO getUser() {
		return user;
	}

	public void setUser(APIUserDTO user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}
	
	

}
