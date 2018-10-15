package com.project.supermarket.stock.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.stock.entity.Stock;
import com.project.supermarket.management.product.entity.Product;






public interface IStockDetailService 
{
	public void save(String []toSubmit);
	public List<Object> findAll(String repoid);
	
	/*public void delete(Long id);
	public void deleteAll(Long[] ids);
	public Stock findOne(Long id);
	public Page<Stock> findAll(Pageable pageable);
	//public List<stock> findQuick(String date1,String date2);            //自定义的查询方法，太麻烦
	public Page<Stock> findAll(Specification<Stock> spec, Pageable pageable); //条件查询分页方法
	public Specification<Stock> findQuick(final Stock stock,String myArray[]); //重写条件
	public Specification<Stock> moresearch(final Stock stock,String toSubmit[]); //重写条件*/
}
