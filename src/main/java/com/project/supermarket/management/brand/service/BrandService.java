package com.project.supermarket.management.brand.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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





@Service
@Transactional
public class BrandService implements IBrandService {

	@Autowired
	private BrandRepository brandRepository;
	
	@Override
	public void save(Brand brand) {
		brandRepository.save(brand);
	}
	
	@Override
	public List<Brand> findAll(){
		return (List<Brand>) brandRepository.findAll();
	}
	
	@Override
	public void delete(Long id) {
		Brand brand = brandRepository.findById(id).get();
		if(brand!=null){
			brandRepository.delete(brand);
		}
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Brand> brand = (List<Brand>) brandRepository.findAllById(idLists);
		if(brand!=null) {
			brandRepository.deleteAll(brand);
		}
	}
	
	@Override
	public Brand findOne(Long id) {
		return brandRepository.findById(id).get();
	}

	@Override
	public Page<Brand> findAll(Pageable pageable) {
		
		Page<Brand> entityPage = brandRepository.findAll(pageable);
		List<Brand> entityLists = entityPage.getContent();
		return new PageImpl<Brand>(entityLists, pageable, entityPage.getTotalElements());
		
	}
	
	public Specification<Brand> findQuick(final Brand brand,String myArray[]) {
		return new Specification<Brand>() {
			@Override
			public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				List<Predicate> predicate = new ArrayList<>();
				try {
					predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get(myArray[2]).as(Date.class),fromat.parse(myArray[0])));
					predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get(myArray[2]).as(Date.class),fromat.parse(myArray[1])));
				}catch (ParseException e) {
					e.printStackTrace();
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	public Specification<Brand> moresearch(final Brand brand,String toSubmit[]) {
		return new Specification<Brand>() {
			@Override
			public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				predicate.add(criteriaBuilder.equal(root.get(toSubmit[0]).as(String.class),toSubmit[1]));
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	@Override
	public Page<Brand> findAll(Specification<Brand> spec, Pageable pageable){
		return brandRepository.findAll(spec, pageable);
	}
	
	
	/*@Override
	public List<Brand> findQuick(String date1,String date2) {
		return brandRepository.findQuick(date1, date2);
		
	}*/
}
