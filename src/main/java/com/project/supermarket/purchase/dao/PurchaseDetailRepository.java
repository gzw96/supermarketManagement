package com.project.supermarket.purchase.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.purchase.entity.PurchaseDetail;

@Repository
public interface PurchaseDetailRepository extends JpaSpecificationExecutor<PurchaseDetail>,PagingAndSortingRepository<PurchaseDetail, Long>{

	@Query("from Product p")
	public List<Product> findAllProduct();
	
	@Query("select r.id from Product r where r.productNum = ?1")
	public Long getProductIdByProductNum(Long productNum);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update purchase_detail set product_id = ?1 where id = ?2 ",nativeQuery=true)
	public void updatePurchaseRepo(Long newProductId,Long oldProductId);
}
