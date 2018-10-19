package com.project.supermarket.purchase.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchaseDTO {
	private String purchaseNum;
	private Double statement;
	private Double payment;
	private String method;
	private Date purchaseTime;
	private String remark;
	
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") 
	private Date purchaseTimeStart;
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") 
	private Date purchaseTimeEnd;
	
	private String supplierName;
	private String repoName;
	private String userRealName;
	private Long workNum;
	
	public  void dtoToEntity(PurchaseDTO dto , Purchase purchase) {
		purchase.setPurchaseNum(dto.getPurchaseNum());
		purchase.setStatement(dto.getStatement());
		purchase.setPayment(dto.getPayment());
		purchase.setPurchaseTime(dto.getPurchaseTime());
		purchase.setMethod(dto.getMethod());
		purchase.setRemark(dto.getRemark());
	}
	
	public void entityToDto(PurchaseDTO dto , Purchase purchase) {
		BeanUtils.copyProperties(purchase, dto);
	}
	
	public String getPurchaseNum() {
		return purchaseNum;
	}
	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}
	public Double getStatement() {
		return statement;
	}
	public void setStatement(double statement) {
		this.statement = statement;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
	public Date getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getRepoName() {
		return repoName;
	}
	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public Long getWorkNum() {
		return workNum;
	}
	public void setWorkNum(Long workNum) {
		this.workNum = workNum;
	}
	
	
	public Date getPurchaseTimeStart() {
		return purchaseTimeStart;
	}

	public void setPurchaseTimeStart(Date purchaseTimeStart) {
		this.purchaseTimeStart = purchaseTimeStart;
	}

	public Date getPurchaseTimeEnd() {
		return purchaseTimeEnd;
	}

	public void setPurchaseTimeEnd(Date purchaseTimeEnd) {
		this.purchaseTimeEnd = purchaseTimeEnd;
	}

	@SuppressWarnings({"serial"})
	public static Specification<Purchase> getWhereClause(final PurchaseDTO purchaseDTO){
		return new Specification<Purchase>() {
			public Predicate toPredicate(Root<Purchase> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				
				if (StringUtils.isNotBlank(purchaseDTO.getPurchaseNum())) {
					predicate.add(criteriaBuilder.like(root.get("purchaseNum").as(String.class),
							"%" + purchaseDTO.getPurchaseNum() + "%"));
				}
				
				if (StringUtils.isNotBlank(purchaseDTO.getMethod())) {
					predicate.add(criteriaBuilder.like(root.get("method").as(String.class),
							"%" + purchaseDTO.getMethod() + "%"));
				}
				
				if (null!=purchaseDTO.getPurchaseTimeStart()) {
					predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("purchaseTime").as(Date.class),
							purchaseDTO.getPurchaseTimeStart()));
				}
				if (null!=purchaseDTO.getPurchaseTimeEnd()) {
					predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("purchaseTime").as(Date.class),
							purchaseDTO.getPurchaseTimeEnd()));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
