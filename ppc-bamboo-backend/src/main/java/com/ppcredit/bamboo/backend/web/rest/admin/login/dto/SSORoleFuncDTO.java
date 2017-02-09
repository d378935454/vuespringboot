 
package com.ppcredit.bamboo.backend.web.rest.admin.login.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Title: SSORoleFuncDTO.java    
 * Description: 描述
 * @author yang_hx       
 * @created 2015-9-1 下午2:07:40    
 */
@Entity
@Table(name = "PPDAI_SSO_ROLE_FUNC")
public class SSORoleFuncDTO implements Serializable {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id;
	
	@ManyToOne(targetEntity = SSORoleDTO.class)
	@JoinColumn(name = "ROLEID")
	//@Cascade(CascadeType.SAVE_UPDATE)
	private SSORoleDTO role;
	
	@ManyToOne(targetEntity = SSOResFuncDTO.class)
	@JoinColumn(name = "FUNCID")
	private SSOResFuncDTO func;
	
	@Column(name = "ISADD")
	private String isAdd ="N";
	
	@Column(name = "ISUPDATE")
	private String isUpdate="N";
	
	@Column(name = "ISDELETE")
	private String isDelete="N";

	@Column(name = "ISACTIVE")
	private byte isActive =1;
	
	
	public void setIsAdd(boolean f){
		if(f){
			this.isAdd = "Y";
		}else{
			this.isAdd = "N";
		}
		
	}
	
	public boolean getIsAdd(){
		if(isAdd.equals("Y")){
			return true;
		}else{
			return false;
		}
	}
	
	public void setIsUpdate(boolean f){
		if(f){
			this.isUpdate = "Y";
		}else{
			this.isUpdate = "N";
		}
		
	}
	
	public boolean getIsUpdate(){
		if(isUpdate.equals("Y")){
			return true;
		}else{
			return false;
		}
	}
	
	public void setIsDelete(boolean f){
		if(f){
			this.isDelete = "Y";
		}else{
			this.isDelete = "N";
		}
		
	}
	
	public boolean getIsDelete(){
		if(isDelete.equals("Y")){
			return true;
		}else{
			return false;
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SSORoleDTO getRole() {
		return role;
	}

	public void setRole(SSORoleDTO role) {
		this.role = role;
	}

	public SSOResFuncDTO getFunc() {
		return func;
	}

	public void setFunc(SSOResFuncDTO func) {
		this.func = func;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	
	
}
