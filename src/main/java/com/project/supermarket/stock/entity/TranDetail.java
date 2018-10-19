package com.project.supermarket.stock.entity;

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
@Table(name="tran_detail")
public class TranDetail {
	private Long id;
	private Transhipment transhipment;
	private Product product;
	private Long tranNum;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@ManyToOne
	@JoinColumn(name="tranId")
	public Transhipment getTranshipment() {
		return transhipment;
	}
	
	@ManyToOne
	@JoinColumn(name="productId")
	public Product getProduct() {
		return product;
	}
	
	@Column(nullable=false)
	public Long getTranNum() {
		return tranNum;
	}
	
	//setter
	public void setId(Long id) {
		this.id = id;
	}
	public void setTranshipment(Transhipment transhipment) {
		this.transhipment = transhipment;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setTranNum(Long tranNum) {
		this.tranNum = tranNum;
	}
	
	
}
