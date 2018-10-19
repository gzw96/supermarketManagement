package com.project.supermarket.user.entity;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.project.supermarket.common.bean.RoleName;

@Entity
@Table(name="role")
public class Role {
	private Long id;
	private RoleName roleName;
	private Set<User> user;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false,unique=true)
	public RoleName getRoleName() {
		return roleName;
	}

	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(
            name="membership",
            joinColumns=@JoinColumn(name="roleId"),
            inverseJoinColumns=@JoinColumn(name="userId")
    )    
	public Set<User> getUser() {
		return user;
	}

	//setter
	public void setId(Long id) {
		this.id = id;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}
	
	
	
	
}
