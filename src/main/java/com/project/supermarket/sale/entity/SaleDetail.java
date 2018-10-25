package com.project.supermarket.sale.entity;

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
@Table(name="sale_detail")
public class SaleDetail {
	private Long id;
	private Sale sale;
	private Long productId;
	private Long saleNum;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	@ManyToOne
	@JoinColumn(name="saleId")
	public Sale getSale() {
		return sale;
	}
	
	public Long getProductId() {
		return productId;
	}
	
	@Column(nullable=false)
	public Long getSaleNum() {
		return saleNum;
	}
	
	//setter
	public void setId(Long id) {
		this.id = id;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setSaleNum(Long saleNum) {
		this.saleNum = saleNum;
	}
	
	
}
