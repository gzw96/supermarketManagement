package com.project.supermarket.purchase.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.management.supplier.entity.Supplier;
import com.project.supermarket.purchase.dao.PurchaseHistoryRepository;
import com.project.supermarket.purchase.entity.Purchase;
import com.project.supermarket.user.entity.User;

@Service
public class PurchaseHistoryService implements PurchaseHistoryServiceImpl{

	@Autowired
	private PurchaseHistoryRepository purchaseHistoryRepository;
	
	@Override
	public Purchase save(Purchase entity) {
		// TODO Auto-generated method stub
		return purchaseHistoryRepository.save(entity);
	}

	@Override
	public Optional<Purchase> findById(Long id) {
		// TODO Auto-generated method stub
		return purchaseHistoryRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return purchaseHistoryRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return purchaseHistoryRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		purchaseHistoryRepository.deleteById(id);
	}

	@Override
	public Page<Purchase> findAll(Specification<Purchase> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return purchaseHistoryRepository.findAll(spec, pageable);
	}

	@Override
	public void deleteAll(Long[] ids) {
		// TODO Auto-generated method stub
		List<Long> idsList = new ArrayList<Long>(Arrays.asList(ids));
		List<Purchase> purchases = (List<Purchase>) purchaseHistoryRepository.findAllById(idsList);
		if(purchases!=null) {
			purchaseHistoryRepository.deleteAll(purchases);
		}
	}

	@Override
	public String getPurchaseUserId(String userRealName, Long workNum) {
		// TODO Auto-generated method stub
		return purchaseHistoryRepository.getPurchaseUserId(userRealName, workNum);
	}

	@Override
	public Long getPurchaseRepoId(String repoName) {
		// TODO Auto-generated method stub
		return purchaseHistoryRepository.getPurchaseRepoId(repoName);
	}

	@Override
	public Long getPurchaseSupplierId(String supplierName) {
		// TODO Auto-generated method stub
		return purchaseHistoryRepository.getPurchaseSupplierId(supplierName);
	}

	@Override
	public void updatePurchaseUser(String userid, String purchaseNum) {
		// TODO Auto-generated method stub
		purchaseHistoryRepository.updatePurchaseUser(userid, purchaseNum);
	}

	@Override
	public List<User> findAllActiveUserRealName() {
		// TODO Auto-generated method stub
		return purchaseHistoryRepository.findAllActiveUserRealName();
	}

	@Override
	public void updatePurchaseSupplier(Long supplierId, String purchaseNum) {
		// TODO Auto-generated method stub
		purchaseHistoryRepository.updatePurchaseSupplier(supplierId, purchaseNum);
	}

	@Override
	public List<Supplier> findAllSupplier() {
		// TODO Auto-generated method stub
		return purchaseHistoryRepository.findAllSupplier();
	}

	@Override
	public void updatePurchaseRepo(Long repoId, String purchaseNum) {
		// TODO Auto-generated method stub
		purchaseHistoryRepository.updatePurchaseRepo(repoId, purchaseNum);
	}

	@Override
	public List<String> findAllRepo() {
		// TODO Auto-generated method stub
		return purchaseHistoryRepository.findAllRepo();
	}

	@Override
	public void updateOperatorById(String userid, Long purchaseId) {
		// TODO Auto-generated method stub
		purchaseHistoryRepository.updateOperatorById(userid, purchaseId);
	}

	@Override
	public void updateRepoById(Long repoId, Long purchaseId) {
		// TODO Auto-generated method stub
		purchaseHistoryRepository.updateRepoById(repoId, purchaseId);
	}

	@Override
	public void updateSupplierById(Long supplierId, Long purchaseId) {
		// TODO Auto-generated method stub
		purchaseHistoryRepository.updateSupplierById(supplierId, purchaseId);
	}

	@Override
	public void updatePurchase(String purchaseNum, Double statement, Double payment, String method, String remark,
			Date date, String userId, Long supplierId, Long repoId) {
		// TODO Auto-generated method stub
		purchaseHistoryRepository.updatePurchase(purchaseNum, statement, payment, method, remark, date, userId, supplierId, repoId);
		
	}

	@Override
	public Long getPurchaseIdByPurchaseNum(String purchaseNum) {
		// TODO Auto-generated method stub
		return purchaseHistoryRepository.getPurchaseIdByPurchaseNum(purchaseNum);
	}

	@Override
	public void updatePurchaseDetail(Long productId, Double purchasePrice, Integer purchaseProductNum,
			Long purchaseId) {
		// TODO Auto-generated method stub
		purchaseHistoryRepository.updatePurchaseDetail(productId, purchasePrice, purchaseProductNum, purchaseId);
	}

	@Override
	public void deleteDetailByPuchaseId(Long purchaseId) {
		// TODO Auto-generated method stub
		purchaseHistoryRepository.deleteDetailByPuchaseId(purchaseId);
	}

}
