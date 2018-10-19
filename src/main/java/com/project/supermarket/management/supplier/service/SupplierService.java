package com.project.supermarket.management.supplier.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.supermarket.management.supplier.entity.Supplier;
import com.project.supermarket.management.supplier.repository.SupplierRepository;

@Service
public class SupplierService implements SupplierServiceImpl{

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Override
	public Supplier save(Supplier entity) {
		// TODO Auto-generated method stub
		return supplierRepository.save(entity);
	}

	@Override
	public Optional<Supplier> findById(Long id) {
		// TODO Auto-generated method stub
		return supplierRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return supplierRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return supplierRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		supplierRepository.deleteById(id);
	}

	@Override
	public Page<Supplier> findAll(Specification<Supplier> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return supplierRepository.findAll(spec, pageable);
	}

	@Override
	public void deleteAll(Long[] ids) {
		// TODO Auto-generated method stub
		List<Long> idsList = new ArrayList<Long>(Arrays.asList(ids));
		List<Supplier> suppliers = (List<Supplier>) supplierRepository.findAllById(idsList);
		if(suppliers!=null) {
			supplierRepository.deleteAll(suppliers);
		}
	}

}
