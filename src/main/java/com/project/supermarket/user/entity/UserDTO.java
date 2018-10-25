package com.project.supermarket.user.entity;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.supermarket.common.bean.RoleName;
import com.project.supermarket.common.bean.Sex;
import com.project.supermarket.common.bean.Status;

public class UserDTO {
	private String id;
	private String userName;
	private String userRealName;
	private String password;
	private Long phone;
	private Sex sex;
	private Date birthday;
	private Long workNum;
	private Date enterDate;
	private String remark;
	private Status status;
	private String groupName;
	
	public String getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getUserRealName() {
		return userRealName;
	}
	
	
	public Long getPhone() {
		return phone;
	}
	
	public Sex getSex() {
		return sex;
	}
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	public Date getBirthday() {
		return birthday;
	}
	
	public Long getWorkNum() {
		return workNum;
	}
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	public Date getEnterDate() {
		return enterDate;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public Status getStatus() {
		return status;
	}


	
	//setter
	public void setId(String id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}


	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setWorkNum(Long workNum) {
		this.workNum = workNum;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
