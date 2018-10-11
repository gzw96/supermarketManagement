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
import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.management.repo.repository.RepoRepository;
import com.project.supermarket.management.repo.service.RepoService;
import com.project.supermarket.stock.dao.StockRepository;
import com.project.supermarket.stock.entity.Stock;



@Service
@Transactional
public class StockService implements IStockService {

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private RepoRepository repoRepository;
	
	/*@Autowired
	private RepoService repoService;*/
	
	@Override
	public void save(Long []ids) {
		for(int i=0;i<ids.length;i++) {
			Stock stock=new Stock();
			Repo repo=new Repo();
			repo=repoRepository.findById(ids[i]).get();
			stock.setRepository(repo);
			Long id=stockRepository.save(stock).getId();
			repo.setStock(stockRepository.findById(id).get());
			repoRepository.save(repo);
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
	}
	
	public Specification<Stock> moresearch(final Stock stock,String toSubmit[]) {
		return new Specification<Stock>() {
			@Override
			public Predicate toPredicate(Root<Stock> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				if(toSubmit[0].equals("getBrandName")) {
					predicate.add(criteriaBuilder.equal(root.join("brand").get("id").as(String.class),toSubmit[1]));
				}else {
					predicate.add(criteriaBuilder.equal(root.get(toSubmit[0]).as(String.class),toSubmit[1]));
				}
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	@Override
	public Page<Stock> findAll(Specification<Stock> spec, Pageable pageable){
		return stockRepository.findAll(spec, pageable);
	}
	
	
	/*@Override
	public List<stock> findQuick(String date1,String date2) {
		return stockRepository.findQuick(date1, date2);
		
	}*/
}
