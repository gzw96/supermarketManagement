package com.project.supermarket.management.brand.service;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.management.brand.entity.Brand;





public interface IBrandService 
{
	public void save(Brand leave);
	public void delete(Long id);
	public void deleteAll(Long[] ids);
	public Brand findOne(Long id);
	public Page<Brand> findAll(Pageable pageable);
	public List<Brand> findAll();
	//public List<Brand> findQuick(String date1,String date2);            //自定义的查询方法，太麻烦
	public Page<Brand> findAll(Specification<Brand> spec, Pageable pageable); //条件查询分页方法
	public Specification<Brand> findQuick(final Brand brand,String myArray[]); //重写条件
	public Specification<Brand> moresearch(final Brand brand,String toSubmit[]); //重写条件
}
