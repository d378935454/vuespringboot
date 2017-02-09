package com.ppcredit.bamboo.backend.web.rest.admin.login.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Title: SSOUserDTO.java
 * Description:
 * 用户实体
 *
 * @author yang_hx
 * @created 2015-9-1 上午11:40:47
 */
@Entity
@Table(name = "PPDAI_SSO_USER")
public class SSOUserDTO implements Serializable {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_LOGIN_NAME")
    private String userLoginName;

    @Column(name = "USER_LOGIN_PASSWORD")
    private String userLoginPassword;

    @Column(name = "LOCATIONID")
    private String locationid;

    @Column(name = "USER_SEX")
    private String userSex;

    @Column(name = "USER_REAL_NAME")
    private String userRealName;

    @Column(name = "USER_MOBILE")
    private String userMobile;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_FAX")
    private String userFax;

    @Column(name = "USER_PHONE")
    private String userPhone;

    @Column(name = "USER_POSITION")
    private String userPosition;

    @Column(name = "USER_STATUS")
    private String userStatus = "S";// 启用：S ,禁用：F

    @Column(name = "USER_PASSANS")
    private String userPassAns;

    @Column(name = "USER_DESC")
    private String userDesc;

    @Column(name = "USER_PASSQUE")
    private String userPassQue;

    @Column(name = "DEPTID")
    private String deptid;

    @Column(name = "POSITION")
    private String postion;

    @Column(name = "ISACTIVE")
    private byte isActive = 1;

    @Column(name = "ISALLCUSTOMER")
    private String isAllcustomer;

    @Column(name = "USER_LDAP_NAME")
    private String userLdapName;

    @ManyToOne(targetEntity = SSOUserPicDTO.class)
    @JoinColumn(name = "USER_PIC")
    private SSOUserPicDTO pic;

    public String getUserLdapName() {
        return userLdapName;
    }

    public void setUserLdapName(String userLdapName) {
        this.userLdapName = userLdapName;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getUserLoginPassword() {
        return userLoginPassword;
    }

    public void setUserLoginPassword(String userLoginPassword) {
        this.userLoginPassword = userLoginPassword;
    }

    public String getLocationid() {
        return locationid;
    }

    public void setLocationid(String locationid) {
        this.locationid = locationid;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFax() {
        return userFax;
    }

    public void setUserFax(String userFax) {
        this.userFax = userFax;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getUserPassQue() {
        return userPassQue;
    }

    public void setUserPassQue(String userPassQue) {
        this.userPassQue = userPassQue;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassAns() {
        return userPassAns;
    }

    public void setUserPassAns(String userPassAns) {
        this.userPassAns = userPassAns;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    public String getIsAllcustomer() {
        return isAllcustomer;
    }

    public void setIsAllcustomer(String isAllcustomer) {
        this.isAllcustomer = isAllcustomer;
    }

    public SSOUserPicDTO getPic() {
        return pic;
    }

    public void setPic(SSOUserPicDTO pic) {
        this.pic = pic;
    }
}
