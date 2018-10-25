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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.supermarket.management.repo.entity.Repo;


@Entity
@Table(name="stock")
@JsonIgnoreProperties(value={"repository","stockDetail"})
public class Stock {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade= {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
	private Repo repository;
	private int stockNum;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateDate;
	@OneToMany(cascade= {CascadeType.ALL},mappedBy="stock")
	private Set<StockDetail> stockDetail = new HashSet<StockDetail>();//外键,一对多 

	
	public Long getId() {
		return id;
	}

	public Repo getRepository() {
		return repository;
	}

	public int getStockNum() {
		return stockNum;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public Set<StockDetail> getStockDetail() {
		return stockDetail;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRepository(Repo repository) {
		this.repository = repository;
	}

	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setStockDetail(Set<StockDetail> stockDetail) {
		this.stockDetail = stockDetail;
	}

	
	
	
}
