/**
 * 
 */
package com.ppcredit.bamboo.backend.web.rest.admin.login.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author yanghongxin
 *
 */
@Entity
@Table(name = "PPDAI_SSO_USER_PIC")
public class SSOUserPicDTO {
	
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Column(name = "ID")
	private String id;
	
	@Column(name = "PIC_TITLE")
	private String title;
	
	@Column(name = "USER_PIC")
	private String pic;
	
	@Column(name = "USER_PIC_MIN")
	private String picMin;
	
	@Column(name = "ISACTIVE")
	private byte isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPicMin() {
		return picMin;
	}

	public void setPicMin(String picMin) {
		this.picMin = picMin;
	}


}
