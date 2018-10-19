package com.project.supermarket.purchase.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.purchase.dao.PurchaseDetailRepository;
import com.project.supermarket.purchase.entity.PurchaseDetail;


@Service
public class PurchaseDetailService implements PurchaseDetailServiceImpl{

	@Autowired
	private PurchaseDetailRepository purchaseDetailRepository;
	
	@Override
	public PurchaseDetail save(PurchaseDetail entity) {
		// TODO Auto-generated method stub
		return purchaseDetailRepository.save(entity);
	}

	@Override
	public Optional<PurchaseDetail> findById(Long id) {
		// TODO Auto-generated method stub
		return purchaseDetailRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return purchaseDetailRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return purchaseDetailRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		purchaseDetailRepository.deleteById(id);
	}

	@Override
	public Page<PurchaseDetail> findAll(Specification<PurchaseDetail> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return purchaseDetailRepository.findAll(spec, pageable);
	}

	@Override
	public void deleteAll(Long[] ids) {
		// TODO Auto-generated method stub
		List<Long> idsList = new ArrayList<Long>(Arrays.asList(ids));
		List<PurchaseDetail> purchases = (List<PurchaseDetail>) purchaseDetailRepository.findAllById(idsList);
		if(purchases!=null) {
			purchaseDetailRepository.deleteAll(purchases);
		}
	}

	@Override
	public List<Product> findAllProduct() {
		// TODO Auto-generated method stub
		return purchaseDetailRepository.findAllProduct();
	}

	@Override
	public void updatePurchaseRepo(Long newProductId, Long oldProductId) {
		// TODO Auto-generated method stub
		purchaseDetailRepository.updatePurchaseRepo(newProductId, oldProductId);
	}

	@Override
	public Long getProductIdByProductNum(Long  productNum) {
		// TODO Auto-generated method stub
		return purchaseDetailRepository.getProductIdByProductNum(productNum);
	}


	
}
