package com.project.supermarket.purchase.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.management.supplier.entity.Supplier;
import com.project.supermarket.purchase.entity.Purchase;
import com.project.supermarket.user.entity.User;

@Repository
public interface PurchaseHistoryRepository extends PagingAndSortingRepository<Purchase, Long>,JpaSpecificationExecutor<Purchase>{
	//通过name找外键的id
	@Query("select u.id from User u where u.userRealName like ?1 and u.workNum like ?2")
	public String getPurchaseUserId(String userRealName,Long workNum);
	
	@Query("select r.id from Repo r where r.repoName like ?1")
	public Long getPurchaseRepoId(String repoName);
	
	@Query("select s.id from Supplier s where s.supplierName like ?1")
	public Long getPurchaseSupplierId(String supplierName);
	
	//根据单号更改外键
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update purchase set operator_id = ?1 where purchase_num = ?2 ",nativeQuery=true)
	public void updatePurchaseUser(String userid,String purchaseNum);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update purchase set supplier_id = ?1 where purchase_num = ?2 ",nativeQuery=true)
	public void updatePurchaseSupplier(Long supplierId,String purchaseNum);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update purchase set repo_id = ?1 where purchase_num = ?2 ",nativeQuery=true)
	public void updatePurchaseRepo(Long repoId,String purchaseNum);
	
	//获取外键的实体
	@Query("from User u where u.status = 'yes' ")
	public List<User> findAllActiveUserRealName();
	
	@Query("from Supplier s where s.status = '可用' ")
	public List<Supplier> findAllSupplier();
	
	@Query("select repoName from Repo r where r.status = '可用' ")
	public List<String> findAllRepo();
	
	//保存外键
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update purchase set operator_id = ?1 where id = ?2 ",nativeQuery=true)
	public void updateOperatorById(String userid,Long purchaseId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update purchase set repo_id = ?1 where id = ?2 ",nativeQuery=true)
	public void updateRepoById(Long repoId,Long purchaseId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update purchase set supplier_id = ?1 where id = ?2 ",nativeQuery=true)
	public void updateSupplierById(Long supplierId,Long purchaseId);


	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="INSERT INTO purchase (purchase_num, statement, payment, method, remark, purchase_time, operator_id, supplier_id, repo_id ,type) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9,'待审批');" ,nativeQuery=true)
	public void updatePurchase(String purchaseNum,Double statement,Double payment,String method,String remark,Date date,String userId,Long supplierId,Long repoId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="INSERT INTO purchase_detail (product_id, purchase_price, purchase_product_num, purchase_id,purchase_status) VALUES (?1, ?2, ?3, ?4,'待审批');" ,nativeQuery=true)
	public void updatePurchaseDetail(Long productId,Double purchasePrice,Integer purchaseProductNum,Long purchaseId);
	
	
	@Query("select r.id from Purchase r where r.purchaseNum = ?1")
	public Long getPurchaseIdByPurchaseNum(String purchaseNum);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="delete from purchase_detail where purchase_id = ?1;" ,nativeQuery=true)
	public void deleteDetailByPuchaseId(Long purchaseId);
}
