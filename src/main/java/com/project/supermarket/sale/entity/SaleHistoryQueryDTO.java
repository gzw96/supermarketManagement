package com.project.supermarket.sale.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;


public class SaleHistoryQueryDTO {
	private String saleNum;
	private Double method;
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") 
	private Date saleTimeStart;
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") 
	private Date saleTimeEnd;
	
	
	public String getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(String saleNum) {
		this.saleNum = saleNum;
	}
	
	
	public Double getMethod() {
		return method;
	}
	public void setMethod(Double method) {
		this.method = method;
	}
	
	
	public Date getSaleTimeStart() {
		return saleTimeStart;
	}
	public void setSaleTimeStart(Date saleTimeStart) {
		this.saleTimeStart = saleTimeStart;
	}
	public Date getSaleTimeEnd() {
		return saleTimeEnd;
	}
	public void setSaleTimeEnd(Date saleTimeEnd) {
		this.saleTimeEnd = saleTimeEnd;
	}
	@SuppressWarnings({"serial"})
	public static Specification<Sale> getWhereClause(final SaleHistoryQueryDTO saleHistoryQueryDTO){
		return new Specification<Sale>() {
			public Predicate toPredicate(Root<Sale> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				
				if (StringUtils.isNotBlank(saleHistoryQueryDTO.getSaleNum())) {
					predicate.add(criteriaBuilder.like(root.get("saleNum").as(String.class),
							"%" + saleHistoryQueryDTO.getSaleNum() + "%"));
				}
				
				if (saleHistoryQueryDTO.getMethod()!=null) {
					predicate.add(criteriaBuilder.like(root.get("method").as(String.class),
							"%" + saleHistoryQueryDTO.getMethod() + "%"));
				}
				
				if (null!=saleHistoryQueryDTO.getSaleTimeStart()) {
					predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("saleTime").as(Date.class),
							saleHistoryQueryDTO.getSaleTimeStart()));
				}
				if (null!=saleHistoryQueryDTO.getSaleTimeEnd()) {
					predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("saleTime").as(Date.class),
							saleHistoryQueryDTO.getSaleTimeEnd()));
				}
				
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
}
