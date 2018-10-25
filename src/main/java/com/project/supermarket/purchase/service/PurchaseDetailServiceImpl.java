package com.project.supermarket.purchase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.purchase.entity.PurchaseDetail;


public interface PurchaseDetailServiceImpl {
	public PurchaseDetail save(PurchaseDetail entity);
	public Optional<PurchaseDetail> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	public Page<PurchaseDetail> findAll(Specification<PurchaseDetail> spec, Pageable pageable);
	public void deleteAll(Long[] ids);
	
	//
	public List<String> findAllProduct();
	public void updatePurchaseRepo(Long newProductId,Long oldProductId);
	public Long getProductIdByProductNum(Long productNum);
}
