package com.project.supermarket.user.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.supermarket.common.bean.RoleName;

@Entity
@Table(name="act_id_group")
public class Role {
	private String id;
	private RoleName roleName;
	private String remark;
	private Set<User> user;
	
	@Id
	@Column(name = "ID_")
	public String getId() {
		return id;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="NAME_",nullable=false,unique=true)
	public RoleName getRoleName() {
		return roleName;
	}

	@ManyToMany(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinTable(
            name="act_id_membership",
            joinColumns=@JoinColumn(name="GROUP_ID_"),
            inverseJoinColumns=@JoinColumn(name="USER_ID_")
    )   
	@JsonIgnore
	public Set<User> getUser() {
		return user;
	}

	
	
	public String getRemark() {
		return remark;
	}

	//setter
	public void setId(String id) {
		this.id = id;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	
}
