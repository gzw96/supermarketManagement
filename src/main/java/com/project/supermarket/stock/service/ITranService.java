package com.project.supermarket.stock.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.stock.entity.TranDetail;
import com.project.supermarket.stock.entity.Tranlog;





public interface ITranService 
{
	
	public Page<Object> findAll(Pageable pageable);
	
}
