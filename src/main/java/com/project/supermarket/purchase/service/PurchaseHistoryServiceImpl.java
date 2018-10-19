package com.project.supermarket.purchase.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.management.supplier.entity.Supplier;
import com.project.supermarket.purchase.entity.Purchase;
import com.project.supermarket.user.entity.User;


public interface PurchaseHistoryServiceImpl {
	public Purchase save(Purchase entity);
	public Optional<Purchase> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	public Page<Purchase> findAll(Specification<Purchase> spec, Pageable pageable);
	public void deleteAll(Long[] ids);
	
	//sql or hql 
	public Long getPurchaseUserId(String userRealName,Long workNum);
	public Long getPurchaseRepoId(String repoName);
	public Long getPurchaseSupplierId(String supplierName);
	
	public void updatePurchaseUser(Long userid,String purchaseNum);
	public List<User> findAllActiveUserRealName();
	
	public void updatePurchaseSupplier(Long supplierId,String purchaseNum);
	public List<Supplier> findAllSupplier();
	
	public void updatePurchaseRepo(Long repoId,String purchaseNum);
	public List<Repo> findAllRepo();
	
	public void updateOperatorById(Long userid,Long purchaseId);
	public void updateRepoById(Long repoId,Long purchaseId);
	public void updateSupplierById(Long supplierId,Long purchaseId);

	public void updatePurchase(String purchaseNum,Double statement,Double payment,String method,String remark,Date date,Long userId,Long supplierId,Long repoId);

	public Long getPurchaseIdByPurchaseNum(String purchaseNum);
	
	public void updatePurchaseDetail(Long productId,Double purchasePrice,Integer purchaseProductNum,Long purchaseId);
	
	public void deleteDetailByPuchaseId(Long purchaseId);
}
