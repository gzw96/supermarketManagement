package com.project.supermarket.sale.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.sale.entity.SaleDetail;

public interface ISaleDetailService {
	public SaleDetail save(SaleDetail saleDetail);
	public List<SaleDetail> findAll();
	public SaleDetail findById(Long id);
 	public List<Product> findAllProduct();
	public Page<SaleDetail> findAll(Specification<SaleDetail> spec, Pageable pageable);
}
