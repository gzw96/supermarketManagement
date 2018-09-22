package com.project.supermarket.stock.entity;

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
import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.management.repo.entity.Repo;

@Entity
@Table(name="stock")
public class Stock {
	private Long id;
	private Product product;
	private Repo repo;
	private Long stockNum;
	private Date updateDate;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@ManyToOne
	@JoinColumn(name="productId")
	public Product getProduct() {
		return product;
	}
	
	@ManyToOne
	@JoinColumn(name="repoId")
	public Repo getRepo() {
		return repo;
	}
	
	@Column(nullable=false)
	public Long getStockNum() {
		return stockNum;
	}
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
	@Column(nullable=false)
	public Date getUpdateDate() {
		return updateDate;
	}
	
	//setter
	public void setId(Long id) {
		this.id = id;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setRepo(Repo repo) {
		this.repo = repo;
	}
	public void setStockNum(Long stockNum) {
		this.stockNum = stockNum;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
