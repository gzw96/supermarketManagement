package com.project.supermarket.purchase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.supermarket.common.bean.PurchaseStatus;
import com.project.supermarket.management.product.entity.Product;

@Entity
@Table(name="purchase_detail")
public class PurchaseDetail {
	private Long id;
	private Purchase purchase;
	private Product product;
	private Long purchaseNum;
	private String puchaseStatus;
	
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
	@JoinColumn(name="purchaseId")
	public Purchase getPurchase() {
		return purchase;
	}


	public Long getPurchaseNum() {
		return purchaseNum;
	}
	

	public String getPuchaseStatus() {
		return puchaseStatus;
	}
	
	//setter
	public void setId(Long id) {
		this.id = id;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setPurchaseNum(Long purchaseNum) {
		this.purchaseNum = purchaseNum;
	}
	public void setPuchaseStatus(String puchaseStatus) {
		this.puchaseStatus = puchaseStatus;
	}
	
	
}
