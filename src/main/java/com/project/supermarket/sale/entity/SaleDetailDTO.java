package com.project.supermarket.sale.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;


public class SaleDetailDTO {
	private Long id;
	private Sale sale;
	private Long productId;
	private Long saleNum;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(Long saleNum) {
		this.saleNum = saleNum;
	}
	
	@SuppressWarnings({"serial"})
	public static Specification<SaleDetail> getWhereClause(final SaleDetailDTO SaleDetailDTO){
		return new Specification<SaleDetail>() {
			public Predicate toPredicate(Root<SaleDetail> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				if (null!=(SaleDetailDTO.getSaleNum())) {
					predicate.add(criteriaBuilder.like(root.get("sale").get("saleNum").as(String.class),
							"%" + SaleDetailDTO.getSaleNum() + "%"));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
}
