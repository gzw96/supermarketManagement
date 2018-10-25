package com.project.supermarket.sale.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.sale.entity.SaleDetail;
import com.project.supermarket.sale.repository.SaleDetailRepository;

@Service
@Transactional
public class SaleDetailService implements ISaleDetailService {
	
	@Autowired
	private SaleDetailRepository saleDetailReposity;
	
	@Override
	public SaleDetail save(SaleDetail saleDetail) {
		return saleDetailReposity.save(saleDetail);
	}

	@Override
	public List<SaleDetail> findAll() {
		return (List<SaleDetail>) saleDetailReposity.findAll();
	}

	@Override
	public SaleDetail findById(Long id) {
		return saleDetailReposity.findById(id).get();
	}

	@Override
	public List<Product> findAllProduct() {
		return saleDetailReposity.findAllProduct();
	}

	@Override
	public Page<SaleDetail> findAll(Specification<SaleDetail> spec, Pageable pageable) {
		return saleDetailReposity.findAll(spec,pageable);
	}
	
}
