package com.project.supermarket.management.brand.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.supermarket.management.product.entity.Product;



@Entity
@JsonIgnoreProperties(value={"product"})      //解决一对多关联的无限递归问题
public class Brand{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;						//主键，id，自增
	private String brandName;				//品牌名
	private String brandDesc;				//品牌描述
	private String brandImg;				//品牌商标
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;				//创建时间
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateTime;				//创建时间
	
	@OneToMany(cascade= {CascadeType.MERGE,CascadeType.REFRESH},mappedBy="brand",fetch=FetchType.LAZY)
	private Set<Product> product = new HashSet<Product>();//外键,一对多 

	
	public Long getId() {
		return id;
	}
	public String getBrandName() {
		return brandName;
	}
	public String getBrandDesc() {
		return brandDesc;
	}
	public String getBrandImg() {
		return brandImg;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public Set<Product> getProduct() {
		return product;
	}
	

	public void setId(Long id) {
		this.id = id;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}
	public void setBrandImg(String brandImg) {
		this.brandImg = brandImg;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	
	
}
