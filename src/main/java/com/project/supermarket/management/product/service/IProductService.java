package com.project.supermarket.management.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.management.product.entity.Product;





public interface IProductService 
{
	public void save(Product product);
	public void delete(Long id);
	public void deleteAll(Long[] ids);
	public Product findOne(Long id);
	public Page<Product> findAll(Pageable pageable);
	public List<Product> findAll();
	//public List<product> findQuick(String date1,String date2);            //自定义的查询方法，太麻烦
	public Page<Product> findAll(Specification<Product> spec, Pageable pageable); //条件查询分页方法
	public Specification<Product> findQuick(final Product product,String myArray[]); //重写条件
	public Specification<Product> moresearch(final Product product,String toSubmit[]); //重写条件
}
