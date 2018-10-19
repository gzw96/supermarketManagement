package com.project.supermarket.management.repo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.supermarket.user.entity.User;

@Entity
@Table(name="repository")
public class Repo {
	private Long id;
	private String repoName;
	private Date buildDate;
	private String address;
	private String repoPhone;
	private Long maxSize;
	private Long minSize;
	private String status;
	private String type;
	private User user;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@Column(nullable=false,unique=true)
	public String getRepoName() {
		return repoName;
	}
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	public Date getBuildDate() {
		return buildDate;
	}

	public String getAddress() {
		return address;
	}

	public String getRepoPhone() {
		return repoPhone;
	}

	@Column(nullable=false)
	public Long getMaxSize() {
		return maxSize;
	}

	@Column(nullable=false)
	public Long getMinSize() {
		return minSize;
	}

	@Column(nullable=false)
	public String getStatus() {
		return status;
	}

	@Column(nullable=false)
	public String getType() {
		return type;
	}

	@ManyToOne
	@JoinColumn(name="roleId")
	public User getUser() {
		return user;
	}

	
	//setter
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRepoPhone(String repoPhone) {
		this.repoPhone = repoPhone;
	}

	public void setMaxSize(Long maxSize) {
		this.maxSize = maxSize;
	}

	public void setMinSize(Long minSize) {
		this.minSize = minSize;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	//setter
	
}
