 
package com.ppcredit.bamboo.backend.web.rest.admin.login.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

  
    /**        
 * Title: SSOUserRoleDTO.java    
 * Description: 描述
 * @author yang_hx       
 * @created 2015-9-1 下午2:40:53    
 */
@Entity
@Table(name = "PPDAI_SSO_USER_ROLE")
public class SSOUserRoleDTO {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id;
	
	@Column(name = "AUTHADM")
	private String authAdm;
	
	@ManyToOne(targetEntity = SSORoleDTO.class)
	@JoinColumn(name = "ROLEID")
	private SSORoleDTO role;
	
	@ManyToOne(targetEntity = SSOUserDTO.class)
	@JoinColumn(name = "USERID")
	@Cascade(CascadeType.SAVE_UPDATE)
	private SSOUserDTO user;
	
	@Column(name = "ISACTIVE")
	private byte isActive = 1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthAdm() {
		return authAdm;
	}

	public void setAuthAdm(String authAdm) {
		this.authAdm = authAdm;
	}

	public SSORoleDTO getRole() {
		return role;
	}

	public void setRole(SSORoleDTO role) {
		this.role = role;
	}

	public SSOUserDTO getUser() {
		return user;
	}

	public void setUser(SSOUserDTO user) {
		this.user = user;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}
	
	

}
