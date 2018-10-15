package com.project.supermarket.management.product.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.supermarket.management.brand.entity.Brand;
import com.project.supermarket.stock.entity.StockDetail;

@Entity
@JsonIgnoreProperties(value={"stockDetail"})
public class Product{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;						//主键，id，自增
	private String productName;				//商品名
	private String productNum;				//商品编号
	private String productImg;				//商品商标
	private Double productPrice;				//价格
	private String status;					//商品状态
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;				//创建时间
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateTime;				//最后一次修改时间
	
	@OneToMany(cascade= {CascadeType.MERGE,CascadeType.REFRESH} ,mappedBy="product",fetch=FetchType.LAZY)
	private Set<StockDetail> stockDetail = new HashSet<StockDetail>();//外键,多对多 
	
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.REFRESH}) 
	private Brand brand;					//外键，所属品牌名
	/*!!!!!!!!!!!!!!!!!!!!!!!!注意数据库方言问题  可能会导致外键无法创建 但不报错!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
	
	/*--------------------------------------------------------------------------------------------------------*/	
	@Transient
	private Long getBrandName;                   //用于获取  不映射进数据库
	/*--------------------------------------------------------------------------------------------------------*/		
	
	public Long getId() {
		return id;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductNum() {
		return productNum;
	}
	public String getProductImg() {
		return productImg;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public String getStatus() {
		return status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public Brand getBrand() {
		return brand;
	}
	public Long getGetBrandName() {
		return getBrandName;
	}
	
	public Set<StockDetail> getStockDetail() {
		return stockDetail;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public void setGetBrandName(Long getBrandName) {
		this.getBrandName = getBrandName;
	}
	public void setStockDetail(Set<StockDetail> stockDetail) {
		this.stockDetail = stockDetail;
	}
	
}
