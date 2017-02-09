package com.ppcredit.bamboo.backend.web.rest.admin.login.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * 
 * Title: APIUserDTO.java    
 * Description: 用户表
 * @author yang_hx       
 * @created 2015-8-17 下午7:18:35
 */
@Entity
@Table(name = "PPDAI_API_USER")
public class APIUserDTO {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PWD")
	private String pwd;
	
	@Column(name = "TEL")
	private String tel;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "REAL_NAME")
	private String realName;
	
	@Column(name = "POSITION")
	private String position;
	
	@Column(name = "AUTH")
	private String auth;
	
	@Column(name = "DEPARTMENT")
	private String department;
	
	@Column(name = "AREA")
	private String area;
	
	@Column(name = "COMPANY")
	private String company;
	
	@Column(name = "ISACTIVE")
	private byte isActive = 1;
	
	@Column(name = "URL")
	private String url;
	
	@Column(name = "ISYIXIN")
	private String isyixin;
	
	@Column(name = "ISDATARESULT")
	private String isdataresult;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIsyixin() {
		return isyixin;
	}

	public void setIsyixin(String isyixin) {
		this.isyixin = isyixin;
	}

	public String getIsdataresult() {
		return isdataresult;
	}

	public void setIsdataresult(String isdataresult) {
		this.isdataresult = isdataresult;
	}
}
