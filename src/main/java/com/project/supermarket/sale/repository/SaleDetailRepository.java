package com.project.supermarket.sale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.sale.entity.SaleDetail;


public interface SaleDetailRepository extends PagingAndSortingRepository<SaleDetail,Long>,JpaSpecificationExecutor<SaleDetail>{
	
	@Query("from Product p")
	public List<Product> findAllProduct();
	
}
