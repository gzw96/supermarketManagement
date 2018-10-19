package com.project.supermarket.stock.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.stock.entity.Stock;
import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.purchase.entity.Purchase;






public interface IStockDetailService 
{
	public void save(String []toSubmit);
	public List<Object> findAll(String repoid);
	public Page<Purchase> findAll(Pageable pageable);
	public List<Object> purchasepro(String pruid);
	public void tran(String[] subitem);

}
