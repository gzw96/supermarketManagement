package com.project.supermarket.management.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.supermarket.common.bean.Status;
import com.project.supermarket.management.brand.entity.Brand;

@Entity
@Table(name="product")
public class Product {
	private Long id;
	private String productName;
	private Long productNum;

	private double retailPrice;
	private double suggestedPrice;
	private Status status;
	private String sort;
	private String format;
	private Brand brand;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@Column(nullable=false)
	public String getProductName() {
		return productName;
	}
	@Column(nullable=false,unique=true)
	public Long getProductNum() {
		return productNum;
	}

	@Column(nullable=false)
	public double getRetailPrice() {
		return retailPrice;
	}
	public double getSuggestedPrice() {
		return suggestedPrice;
	}
	@Column(nullable=false)
	public Status getStatus() {
		return status;
	}
	@Column(nullable=false)
	public String getSort() {
		return sort;
	}
	
	public String getFormat() {
		return format;
	}
	
	@ManyToOne
	@JoinColumn(name="brandId")
	public Brand getBrand() {
		return brand;
	}
	
	//setter
	public void setId(Long id) {
		this.id = id;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public void setSuggestedPrice(double suggestedPrice) {
		this.suggestedPrice = suggestedPrice;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	
	
}
