package com.project.supermarket.stock.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.supermarket.management.brand.dao.BrandRepository;
import com.project.supermarket.management.brand.entity.Brand;
import com.project.supermarket.management.product.dao.ProductRepository;
import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.management.repo.repository.RepoRepository;
import com.project.supermarket.management.repo.service.RepoService;
import com.project.supermarket.stock.dao.StockDetailRepository;
import com.project.supermarket.stock.dao.StockRepository;
import com.project.supermarket.stock.entity.Stock;
import com.project.supermarket.stock.entity.StockDetail;



@Service
@Transactional
public class StockDetailService implements IStockDetailService {

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private RepoRepository repoRepository;
	
	@Autowired
	private StockDetailRepository stockDetailRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	/*@Autowired
	private RepoService repoService;*/
	
	@Override
	public void save(String []toSubmit) {
		String []proId=toSubmit[1].split(",");
		String []proNum=toSubmit[0].split(",");	
		Stock stock=stockRepository.findOne(findstock(toSubmit)).get();
		for(int i=0;i<proId.length;i++) {
			StockDetail stockDetail=new StockDetail();
			stockDetail.setStock(stock);
			stockDetail.setNum(Integer.parseInt(proNum[i]));
			Product product=productRepository.findById(Long.parseLong(proId[i])).get();
			stockDetail.setProduct(product);
			stockDetailRepository.save(stockDetail);
		}
		
	}

	/*@Override
	public void delete(Long id) {
		Stock stock = stockRepository.findById(id).get();
		if(stock!=null){
			stockRepository.delete(stock);
		}
	}
	

	
	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Stock> stock = (List<Stock>) stockRepository.findAllById(idLists);
		if(stock!=null) {
			stockRepository.deleteAll(stock);
		}
	}
	
	@Override
	public Stock findOne(Long id) {
		return stockRepository.findById(id).get();
	}

	
	@Override
	public Page<Stock> findAll(Pageable pageable) {
		
		Page<Stock> entityPage = stockRepository.findAll(pageable);
		List<Stock> entityLists = entityPage.getContent();
		/*List list = new ArrayList();
		SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		for(int i=0;i<entityLists.size();i++) {
			Map<String,Object> map=new HashMap<String, Object>();
	        map.put("createTime", fromat.format(entityLists.get(i).getCreateTime()));
	        map.put("updateTime", fromat.format(entityLists.get(i).getUpdateTime()));
	        map.put("stockImg", entityLists.get(i).getStockImg());
	        map.put("stockName", entityLists.get(i).getStockName());
	        map.put("stockPrice", entityLists.get(i).getStockPrice());
	        map.put("stockNum", entityLists.get(i).getStockNum());
	        map.put("status", entityLists.get(i).getStatus());
	        map.put("brandName", entityLists.get(i).getBrand().getBrandName());
	        map.put("brandName1", entityLists.get(i).getBrand().getId());
	        map.put("id", entityLists.get(i).getId());
	        list.add(map);
		}*/
		/*return new PageImpl<Stock>(entityLists, pageable, entityPage.getTotalElements());
		
	}
	
	public Specification<Stock> findQuick(final Stock stock,String myArray[]) {
		return new Specification<Stock>() {
			@Override
			public Predicate toPredicate(Root<Stock> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				try {
					System.out.println(myArray[0]);
					System.out.println(myArray[1]);
					System.out.println(myArray[2]);
					if(myArray[2].equals("stockPrice")) {
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get(myArray[2]).as(Double.class),Double.parseDouble(myArray[0])));
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get(myArray[2]).as(Double.class),Double.parseDouble(myArray[1])));
					}else {
						myArray[0]=myArray[0]+" 00:00:00";
						myArray[1]=myArray[1]+" 23:59:59";
						SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
						predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get(myArray[2]).as(Date.class),fromat.parse(myArray[0])));
						predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get(myArray[2]).as(Date.class),fromat.parse(myArray[1])));
					}
				}catch (ParseException e) {
					e.printStackTrace();
				}
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}*/
	
	public Specification<Stock> findstock(String toSubmit[]) {
		return new Specification<Stock>() {
			@Override
			public Predicate toPredicate(Root<Stock> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				predicate.add(criteriaBuilder.equal(root.join("repository").get("id").as(Long.class),Long.parseLong(toSubmit[2])));
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	/*@Override
	public Page<Stock> findAll(Specification<Stock> spec, Pageable pageable){
		return stockRepository.findAll(spec, pageable);
	}
	
	
	/*@Override
	public List<stock> findQuick(String date1,String date2) {
		return stockRepository.findQuick(date1, date2);
		
	}*/
}