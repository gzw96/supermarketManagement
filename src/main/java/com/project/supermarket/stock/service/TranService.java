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
import com.project.supermarket.stock.dao.TranDetailRepository;
import com.project.supermarket.stock.dao.TranlogRepository;
import com.project.supermarket.stock.entity.TranDetail;
import com.project.supermarket.stock.entity.Tranlog;



@Service
@Transactional
public class TranService implements ITranService {

	@Autowired
	private TranlogRepository tranlogRepository;
	
	@Autowired
	private TranDetailRepository tranDetailRepository;
	
	@Override
	public Page<Object> findAll(Pageable pageable) {
		
		Page<Tranlog> page=tranlogRepository.findAll(pageable);
		List<Object> list=new ArrayList();
		List<Object> list1=new ArrayList();
		for(int i=0;i<page.getContent().size();i++) {
			List<TranDetail> detail=tranDetailRepository.findAll(moresearch(page.getContent().get(i).getId()));
			Map map=new HashMap<String,Object>();
			map.put("id", page.getContent().get(i).getId());
			map.put("moveDate", page.getContent().get(i).getMoveDate());
			map.put("moveNum", page.getContent().get(i).getMoveNum());
			map.put("repoFrom", page.getContent().get(i).getRepoFrom());
			map.put("repoTo", page.getContent().get(i).getRepoTo());
			map.put("tranDetail", detail);
			list.add(map);
		}
		return new PageImpl<Object>(list, pageable, page.getTotalElements());
	}
	
	
	
	public Specification<TranDetail> moresearch(Long id) {
		return new Specification<TranDetail>() {
			@Override
			public Predicate toPredicate(Root<TranDetail> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				predicate.add(criteriaBuilder.equal(root.join("tranlog").get("id"),id));
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	
}
