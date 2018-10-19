package com.project.supermarket.purchase.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

public class PurchaseDetailDTO {

	private String purchaseStatus;
	private Integer purchaseProductNum;//采购数量
	
	private String purchaseNum;//单号
	
	private String productName;
	private Long productNum;
	private String format;
	private Double purchasePrice;//采购价
	private Double retailPrice;//零售价
	
	public void dtoToEntity(PurchaseDetailDTO dto,PurchaseDetail purchaseDetail) {
		purchaseDetail.setPurchaseStatus(dto.getPurchaseStatus());
		purchaseDetail.setPurchaseProductNum(dto.getPurchaseProductNum());
		purchaseDetail.setPurchasePrice(dto.getPurchasePrice());
	}
	
	public void entityToDto(PurchaseDetailDTO dto , PurchaseDetail purchaseDetail) {
		BeanUtils.copyProperties(purchaseDetail, dto);
	}
	
	
	
	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public Integer getPurchaseProductNum() {
		return purchaseProductNum;
	}

	public void setPurchaseProductNum(Integer purchaseProductNum) {
		this.purchaseProductNum = purchaseProductNum;
	}

	public String getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	@SuppressWarnings({"serial"})
	public static Specification<PurchaseDetail> getWhereClause(final PurchaseDetailDTO purchaseDetailDTO){
		return new Specification<PurchaseDetail>() {
			public Predicate toPredicate(Root<PurchaseDetail> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(purchaseDetailDTO.getPurchaseNum())) {
					predicate.add(criteriaBuilder.like(root.get("purchase").get("purchaseNum").as(String.class),
							"%" + purchaseDetailDTO.getPurchaseNum() + "%"));
				}
				
				
//				if (null!=(purchaseDetailDTO.getPurchaseNum())) {
//					Join<PurchaseDetail,Purchase> join = root.join("purchase",JoinType.LEFT);
//					System.out.println(purchaseDetailDTO.getPurchaseNum());
//					predicate.add(criteriaBuilder.equal(join.get("purchase").get("purchaseNum"),
//							 purchaseDetailDTO.getPurchaseNum() ));
//				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
