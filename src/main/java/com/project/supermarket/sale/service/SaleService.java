package com.project.supermarket.sale.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.supermarket.purchase.entity.Purchase;
import com.project.supermarket.sale.entity.Sale;
import com.project.supermarket.sale.repository.SaleRepository;


@Service
@Transactional
public class SaleService implements ISaleService {

	@Autowired
	private SaleRepository saleRepository;
	
	@Override
	public Sale save(Sale sale) {
		return saleRepository.save(sale);
	}

	@Override
	public List<Sale> findAll() {
		return (List<Sale>) saleRepository.findAll();
	}

	@Override
	public Sale findById(Long id) {
		return saleRepository.findById(id).get();
	}

	@Override
	public Page<Sale> findAll(Specification<Sale> spec, Pageable pageable) {
		return saleRepository.findAll(spec,pageable);
	}

	@Override
	public void deleteById(Long id) {
		saleRepository.deleteById(id);
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idsList = new ArrayList<Long>(Arrays.asList(ids));
		List<Sale> purchases = (List<Sale>) saleRepository.findAllById(idsList);
		if(purchases!=null) {
			saleRepository.deleteAll(purchases);
		}
	}

	@Override
	public void deleteDetailBySaleId(Long saleId) {
		saleRepository.deleteDetailBySaleId(saleId);
		
	}
}
