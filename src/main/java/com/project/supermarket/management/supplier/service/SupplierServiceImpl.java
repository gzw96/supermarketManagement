package com.project.supermarket.management.supplier.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.management.supplier.entity.Supplier;


public interface SupplierServiceImpl {
	public Supplier save(Supplier entity);
	public Optional<Supplier> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	public Page<Supplier> findAll(Specification<Supplier> spec, Pageable pageable);
	
	public void deleteAll(Long[] ids);
}
