package com.project.supermarket.management.product.service;

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



@Service
@Transactional
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Override
	public void save(Product product) {
		String val="";
		Random random=new Random();
		for(int i=0;i<8;i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; 
			 if( "char".equalsIgnoreCase(charOrNum) ) {  
				 int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
	             val += (char)(random.nextInt(26) + temp);  
			 }else if( "num".equalsIgnoreCase(charOrNum) ) {  
	                val += String.valueOf(random.nextInt(10));  
	         } 
		}
		product.setProductNum(val);
		Brand brand=new Brand();
		brand=brandRepository.findById(product.getGetBrandName()).get();
		product.setBrand(brand);
		productRepository.save(product);
	}

	@Override
	public void delete(Long id) {
		Product product = productRepository.findById(id).get();
		if(product!=null){
			productRepository.delete(product);
		}
	}
	
	@Override
	public List<Product> findAll(){
		return (List<Product>) productRepository.findAll();
	}
	
	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Product> product = (List<Product>) productRepository.findAllById(idLists);
		if(product!=null) {
			productRepository.deleteAll(product);
		}
	}
	
	@Override
	public Product findOne(Long id) {
		return productRepository.findById(id).get();
	}

	
	@Override
	public Page<Product> findAll(Pageable pageable) {
		
		Page<Product> entityPage = productRepository.findAll(pageable);
		List<Product> entityLists = entityPage.getContent();
		/*List list = new ArrayList();
		SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		for(int i=0;i<entityLists.size();i++) {
			Map<String,Object> map=new HashMap<String, Object>();
	        map.put("createTime", fromat.format(entityLists.get(i).getCreateTime()));
	        map.put("updateTime", fromat.format(entityLists.get(i).getUpdateTime()));
	        map.put("productImg", entityLists.get(i).getProductImg());
	        map.put("productName", entityLists.get(i).getProductName());
	        map.put("productPrice", entityLists.get(i).getProductPrice());
	        map.put("productNum", entityLists.get(i).getProductNum());
	        map.put("status", entityLists.get(i).getStatus());
	        map.put("brandName", entityLists.get(i).getBrand().getBrandName());
	        map.put("brandName1", entityLists.get(i).getBrand().getId());
	        map.put("id", entityLists.get(i).getId());
	        list.add(map);
		}*/
		return new PageImpl<Product>(entityLists, pageable, entityPage.getTotalElements());
		
	}
	
	public Specification<Product> findQuick(final Product product,String myArray[]) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				try {
					System.out.println(myArray[0]);
					System.out.println(myArray[1]);
					System.out.println(myArray[2]);
					if(myArray[2].equals("productPrice")) {
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
	
	public Specification<Product> moresearch(final Product product,String toSubmit[]) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
	public Page<Product> findAll(Specification<Product> spec, Pageable pageable){
		return productRepository.findAll(spec, pageable);
	}
	
	
	/*@Override
	public List<product> findQuick(String date1,String date2) {
		return productRepository.findQuick(date1, date2);
		
	}*/
}
