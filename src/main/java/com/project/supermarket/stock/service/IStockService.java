package com.project.supermarket.stock.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.stock.entity.Stock;







public interface IStockService 
{
	public void save(Long []id);
	public List<Object> getAllRepoCloumn();
	public List<Object> getRepoPie(Long repoid);
}
