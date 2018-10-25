package com.project.supermarket.sale.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.sale.entity.Sale;

public interface ISaleService {
	public Sale save(Sale sale);
	public List<Sale> findAll();
	public Sale findById(Long id);
	public Page<Sale> findAll(Specification<Sale> spec, Pageable pageable);
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	public void deleteDetailBySaleId(Long saleId);
}
