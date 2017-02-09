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
* Title: SSOAPIUserDTO.java    
* @author hu_yj       
* @created 2016-02-04 下午16:18:22    
*/
@Entity
@Table(name = "PPDAI_API_SSO_USER")
public class SSOAPIUserDTO {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id;
	
	@ManyToOne(targetEntity = SSOUserDTO.class)
	@JoinColumn(name = "SSOID")
	private SSOUserDTO ssoUser;
	
	@ManyToOne(targetEntity = APIUserDTO.class)
	@JoinColumn(name = "USERID")
	private APIUserDTO apiUser;
	
	@Column(name = "ISACTIVE")
	private byte isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SSOUserDTO getSsoUser() {
		return ssoUser;
	}

	public void setSsoUser(SSOUserDTO ssoUser) {
		this.ssoUser = ssoUser;
	}

	public APIUserDTO getApiUser() {
		return apiUser;
	}

	public void setApiUser(APIUserDTO apiUser) {
		this.apiUser = apiUser;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}	
}
