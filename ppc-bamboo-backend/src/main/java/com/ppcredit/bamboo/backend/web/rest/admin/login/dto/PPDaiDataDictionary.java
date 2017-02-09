package com.ppcredit.bamboo.backend.web.rest.admin.login.dto;

import java.util.Date;

/**
 * 
 * Title: DATA_DICTIONARY.java    
 * Description: 数据字典表    
 */
public class PPDaiDataDictionary {
	private String dbName;
	private String dbType;
	private String tbName;
	private String tbDescribe;
	private Date createTime;
	private Date updateTime;
	private int fieldNum;
	private String fieldName;
	private String fieldDescribe;
	private String fieldType;
	private int length;
	private String astrict;
	private String note;
	
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getTbName() {
		return tbName;
	}
	public void setTbName(String tbName) {
		this.tbName = tbName;
	}
	public String getTbDescribe() {
		return tbDescribe;
	}
	public void setTbDescribe(String tbDescribe) {
		this.tbDescribe = tbDescribe;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getFieldNum() {
		return fieldNum;
	}
	public void setFieldNum(int fieldNum) {
		this.fieldNum = fieldNum;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldDescribe() {
		return fieldDescribe;
	}
	public void setFieldDescribe(String fieldDescribe) {
		this.fieldDescribe = fieldDescribe;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getAstrict() {
		return astrict;
	}
	public void setAstrict(String astrict) {
		this.astrict = astrict;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}			
}
