package com.project.supermarket.purchase.entity;

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
import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.management.supplier.entity.Supplier;
import com.project.supermarket.user.entity.User;

@Entity
@Table(name="purchase")
public class Purchase {
	private Long id;
	private Long purchaseNum;
	private double statement;
	private double payment;
	private String method;
	private Date purchaseTime;
	private String remark;
	//foreignkey
	private Supplier supplier;
	private Repo repo;
	private User operator;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@Column(nullable=false,unique=true)
	public Long getPurchaseNum() {
		return purchaseNum;
	}
	
	@Column(nullable=false)
	public double getStatement() {
		return statement;
	}
	
	@Column(nullable=false)
	public double getPayment() {
		return payment;
	}
	
	public String getMethod() {
		return method;
	}
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
	public Date getPurchaseTime() {
		return purchaseTime;
	}
	public String getRemark() {
		return remark;
	}
	
	@ManyToOne
	@JoinColumn(name="repoId")
	public Repo getRepo() {
		return repo;
	}
	@ManyToOne
	@JoinColumn(name="operatorId")
	public User getOperator() {
		return operator;
	}
	@ManyToOne
	@JoinColumn(name="supplierId")
	public Supplier getSupplier() {
		return supplier;
	}

	//setter
	public void setId(Long id) {
		this.id = id;
	}

	public void setPurchaseNum(Long purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public void setStatement(double statement) {
		this.statement = statement;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void setRepo(Repo repo) {
		this.repo = repo;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}
	
	
	
}
