package com.project.supermarket.purchase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.supermarket.management.product.entity.Product;

@Entity
@Table(name="purchase_detail")
public class PurchaseDetail {
	private Long id;
	private Double purchasePrice;
	private String purchaseStatus="待审核";
	private Integer purchaseProductNum;
	
	private Purchase purchase;
	private Product product;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="purchaseId")
	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	@Column(nullable=false)
	public Integer getPurchaseProductNum() {
		return purchaseProductNum;
	}

	public void setPurchaseProductNum(Integer purchaseProductNum) {
		this.purchaseProductNum = purchaseProductNum;
	}

	@ManyToOne
	@JoinColumn(name="productId")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(nullable=false)
	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	@Column(nullable=false)
	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	
}
