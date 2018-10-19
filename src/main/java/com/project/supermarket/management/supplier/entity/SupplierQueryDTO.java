package com.project.supermarket.management.supplier.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.common.bean.Status;

public class SupplierQueryDTO {
	private String supplierName;
	private String supplierPhone;
	private String supplierPeople;
	private String bankAccount;
	private String bankName;
	private String address;
	private String remark;
	private String status;//查询当前状态

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	
	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSupplierPeople() {
		return supplierPeople;
	}

	public void setSupplierPeople(String supplierPeople) {
		this.supplierPeople = supplierPeople;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@SuppressWarnings({"serial"})
	public static Specification<Supplier> getWhereClause(final SupplierQueryDTO supplierQueryDTO){
		return new Specification<Supplier>() {
			public Predicate toPredicate(Root<Supplier> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(supplierQueryDTO.getSupplierName())) {
					predicate.add(criteriaBuilder.like(root.get("supplierName").as(String.class),
							"%" + supplierQueryDTO.getSupplierName() + "%"));
				}
				if (StringUtils.isNotBlank(supplierQueryDTO.getSupplierPeople())) {
					predicate.add(criteriaBuilder.like(root.get("supplierPeople").as(String.class),
							"%" + supplierQueryDTO.getSupplierName() + "%"));
				}
				if (StringUtils.isNotBlank(supplierQueryDTO.getBankName())) {
					predicate.add(criteriaBuilder.like(root.get("bankName").as(String.class),
							"%" + supplierQueryDTO.getBankName() + "%"));
				}
				if (StringUtils.isNotBlank(supplierQueryDTO.getStatus())) {
					predicate.add(criteriaBuilder.like(root.get("status").as(String.class),
							 supplierQueryDTO.getStatus() + "%"));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
