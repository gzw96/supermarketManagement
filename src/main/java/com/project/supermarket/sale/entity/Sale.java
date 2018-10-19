package com.project.supermarket.sale.entity;

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
import com.project.supermarket.common.bean.Status;
import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.user.entity.User;

@Entity
@Table(name="sale")
public class Sale {
	private Long id;
	private Long saleNum;
	private Repo repo;
	private double statement;
	private double payment;
	private String method;
	private Date saleTime;
	private User sales;
	private Long customerPhone;
	private String customerName;
	private Status status;
	private String remark;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@Column(nullable=false)
	public Long getSaleNum() {
		return saleNum;
	}
	
	@ManyToOne
	@JoinColumn(name="repoId")
	public Repo getRepo() {
		return repo;
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
	
	@ManyToOne
	@JoinColumn(name="salesId")
	public User getSales() {
		return sales;
	}
	public Long getCustomerPhone() {
		return customerPhone;
	}
	public String getCustomerName() {
		return customerName;
	}
	@Column(nullable=false)
	public Status getStatus() {
		return status;
	}
	public String getRemark() {
		return remark;
	}
	
	//setter
	public void setId(Long id) {
		this.id = id;
	}
	public void setSaleNum(Long saleNum) {
		this.saleNum = saleNum;
	}
	public void setRepo(Repo repo) {
		this.repo = repo;
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
	public void setSales(User sales) {
		this.sales = sales;
	}
	public void setCustomerPhone(Long customerPhone) {
		this.customerPhone = customerPhone;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
