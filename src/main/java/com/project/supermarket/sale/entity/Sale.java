package com.project.supermarket.sale.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.supermarket.common.bean.Status;

@Entity
@Table(name="sale")
public class Sale {
	private Long id;
	private String saleNum;
	private Long repoId;
	private double statement;
	private double payment;
	private String method;
	private Date saleTime;
	private String salerId;
	private Long customerPhone;
	private String customerName;
	private String status;
	private String remark;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@Column(nullable=false)
	public String getSaleNum() {
		return saleNum;
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
	@Column(nullable=false)
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
	public Date getSaleTime() {
		return saleTime;
	}
	
	
	public Long getRepoId() {
		return repoId;
	}

	public String getSalerId() {
		return salerId;
	}

	public Long getCustomerPhone() {
		return customerPhone;
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getStatus() {
		return status;
	}
	public String getRemark() {
		return remark;
	}
	
	//setter
	public void setId(Long id) {
		this.id = id;
	}
	public void setSaleNum(String saleNum) {
		this.saleNum = saleNum;
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
	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}
	
	public void setCustomerPhone(Long customerPhone) {
		this.customerPhone = customerPhone;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setRepoId(Long repoId) {
		this.repoId = repoId;
	}

	public void setSalerId(String salerId) {
		this.salerId = salerId;
	}
	
}
