package com.project.supermarket.management.supplier.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.supermarket.common.bean.Status;

@Entity
@Table(name="supplier")
public class Supplier {
	private Long id;
	private String supplierName;
	private String supplierPeople;
	private String supplierPhone;
	private String bankAccount;
	private String bankName;
	private String address;
	private String remark;
	private String status;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@Column(nullable=false)
	public String getSupplierName() {
		return supplierName;
	}
	public String getSupplierPeople() {
		return supplierPeople;
	}
	@Column(nullable=false)
	public String getSupplierPhone() {
		return supplierPhone;
	}
	@Column(nullable=false)
	public String getBankAccount() {
		return bankAccount;
	}
	public String getBankName() {
		return bankName;
	}
	public String getAddress() {
		return address;
	}
	public String getRemark() {
		return remark;
	}
	
	@Column(nullable=false)
	public String getStatus() {
		return status;
	}
	
	
	//setter
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public void setSupplierPeople(String supplierPeople) {
		this.supplierPeople = supplierPeople;
	}
	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
