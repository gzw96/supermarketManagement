package com.project.supermarket.stock.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.supermarket.stock.entity.Stock;
import com.project.supermarket.stock.entity.Tranlog;






@Repository
public interface TranlogRepository extends PagingAndSortingRepository<Tranlog, Long>,JpaSpecificationExecutor<Tranlog>
{
	/*@Query(value="select *  from brand where create_time between ?1 and ?2 ",nativeQuery=true)
	public List<Brand> findQuick(String date1,String date2);*/
}
