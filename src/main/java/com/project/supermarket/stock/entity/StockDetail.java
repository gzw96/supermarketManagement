package com.project.supermarket.stock.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.supermarket.management.product.entity.Product;


@Entity
@Table(name="stockDetail")
public class StockDetail {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.MERGE})
	private Stock stock;
	
	@ManyToOne(cascade= {CascadeType.MERGE,CascadeType.REFRESH})
	private Product product=new Product();
	
	private int num;   //商品数量

	
	public Long getId() {
		return id;
	}

	public Stock getStock() {
		return stock;
	}

	public Product getProduct() {
		return product;
	}

	public int getNum() {
		return num;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
	
	
}
