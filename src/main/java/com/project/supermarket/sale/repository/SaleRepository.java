package com.project.supermarket.sale.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.supermarket.sale.entity.Sale;


public interface SaleRepository extends PagingAndSortingRepository<Sale,Long>,JpaSpecificationExecutor<Sale>{
	/*@Query("select r.id from Repo r where r.repoName like ?1")
	public Long getRepoId(String repoName);*/
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="INSERT INTO Sale (sale_num, statement, payment, method, remark, sale_time, saler_id, customer_name, customer_phone) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9);" ,nativeQuery=true)
	public void updateSale(String saleNum,Double statement,Double payment,String method,String remark,Date date,String salerId,String customerName,Long customerPhone);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="INSERT INTO sale_detail (product_id, sale_num, sale_id) VALUES (?1, ?2, ?3);" ,nativeQuery=true)
	public void updateSaleDetail(Long productId,Integer saleProductNum,Long saleId);
	
	@Query("select s.id from Sale s where s.saleNum = ?1")
	public Long getSaleIdBySaleNum(String saleNum);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="delete from sale_detail where sale_id = ?1;" ,nativeQuery=true)
	public void deleteDetailBySaleId(Long saleId);
	
}
