package com.project.supermarket.user.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.supermarket.common.bean.Sex;
import com.project.supermarket.common.bean.Status;

@Entity
@Table(name="user")
public class User {
	private Long id;
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
	private Set<Role> role;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@Column(nullable=false,unique=true)
	public String getUserName() {
		return userName;
	}
	
	@Column(nullable=false)
	public String getUserRealName() {
		return userRealName;
	}
	
	@Column(nullable=false)
	public String getPassword() {
		return password;
	}
	
	public Long getPhone() {
		return phone;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	public Sex getSex() {
		return sex;
	}
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	public Date getBirthday() {
		return birthday;
	}
	
	@Column(nullable=false)
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
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	public Status getStatus() {
		return status;
	}

	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(
    		name="membership",
            joinColumns=@JoinColumn(name="userId"),
            inverseJoinColumns=@JoinColumn(name="roleId")
    )    
	public Set<Role> getRole() {
		return role;
	}

	
	//setter
	public void setId(Long id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	
}
