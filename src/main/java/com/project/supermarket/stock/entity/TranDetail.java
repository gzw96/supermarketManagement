package com.project.supermarket.stock.entity;

import javax.persistence.CascadeType;
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
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.REFRESH})
	private Tranlog tranlog;
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.REFRESH})
	private Product product;
	private int tranNum;
	
	
	public Long getId() {
		return id;
	}
	public Tranlog getTranlog() {
		return tranlog;
	}
	public Product getProduct() {
		return product;
	}
	public int getTranNum() {
		return tranNum;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setTranlog(Tranlog tranlog) {
		this.tranlog = tranlog;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setTranNum(int tranNum) {
		this.tranNum = tranNum;
	}

	
	
}
