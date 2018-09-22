package com.project.supermarket.management.brand.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="brand")
public class Brand {
	private Long id;
	private String brandName;
	private String brandWeb;
	private String brandAddress;
	private String brandDesc;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	@Column(nullable=false,unique=true)
	public String getBrandName() {
		return brandName;
	}
	public String getBrandWeb() {
		return brandWeb;
	}
	public String getBrandAddress() {
		return brandAddress;
	}
	public String getBrandDesc() {
		return brandDesc;
	}
	
	//setter
	public void setId(Long id) {
		this.id = id;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public void setBrandWeb(String brandWeb) {
		this.brandWeb = brandWeb;
	}
	public void setBrandAddress(String brandAddress) {
		this.brandAddress = brandAddress;
	}
	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}
	
	
}
