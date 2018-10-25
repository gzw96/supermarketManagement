package com.project.supermarket.stock.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.project.supermarket.management.product.dao.ProductRepository;
import com.project.supermarket.management.product.entity.Product;

import com.project.supermarket.management.repo.repository.RepoRepository;

import com.project.supermarket.purchase.dao.PurchaseDetailRepository;
import com.project.supermarket.purchase.dao.PurchaseRepository;
import com.project.supermarket.purchase.entity.Purchase;
import com.project.supermarket.purchase.entity.PurchaseDetail;
import com.project.supermarket.stock.dao.StockDetailRepository;
import com.project.supermarket.stock.dao.StockRepository;
import com.project.supermarket.stock.dao.TranDetailRepository;
import com.project.supermarket.stock.dao.TranlogRepository;
import com.project.supermarket.stock.entity.Stock;
import com.project.supermarket.stock.entity.StockDetail;
import com.project.supermarket.stock.entity.TranDetail;
import com.project.supermarket.stock.entity.Tranlog;



@Service
@Transactional
public class StockDetailService implements IStockDetailService {

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private TranlogRepository tranlogRepository;
	
	@Autowired
	private TranDetailRepository tranDetailRepository;
	
	@Autowired
	private RepoRepository repoRepository;
	
	@Autowired
	private StockDetailRepository stockDetailRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private PurchaseDetailRepository purchaseDetailRepository;
	
	
	@Override
	public void save(String []toSubmit) {
		String []proId=toSubmit[1].split(",");
		String []proNum=toSubmit[0].split(",");	
		int count=0;
		Stock stock=stockRepository.findOne(findstock(Long.parseLong(toSubmit[3]))).get();		//存入的仓库
		Stock stock1=stockRepository.findOne(findstock(Long.parseLong(toSubmit[2]))).get(); 	  //取出的仓库
		List<StockDetail> list=stockDetailRepository.findAll(findstockDetail(stock.getId()));	//获取存入仓库的全部库存详情表
		List<StockDetail> list1=stockDetailRepository.findAll(findstockDetail(stock1.getId()));	//获取取出仓库的库存详情表
		/*存入仓库库存详情表的操作*/
		if(list.isEmpty()==true) {
			for(int i=0;i<proId.length;i++) {
				StockDetail stockDetail=new StockDetail();
				stockDetail.setNum(Integer.parseInt(proNum[i]));
				Product product=productRepository.findById(Long.parseLong(proId[i])).get();
				stockDetail.setProduct(product);
				stockDetail.setStock(stock);
				stockDetailRepository.save(stockDetail);
				count=count+Integer.parseInt(proNum[i]);
			}
			/*stock更新货物总量*/
			stock.setStockNum(count);
			stockRepository.save(stock);
		}else if(!list.isEmpty()) {
			for(int j=0;j<proId.length;j++) {
				int flag=0;
				for(int i=0;i<list.size();i++)  {
					if(Long.parseLong(proId[j])!=list.get(i).getProduct().getId()) {
						flag++;
						if(flag==list.size()) {
							StockDetail stockDetail=new StockDetail();
							stockDetail.setNum(Integer.parseInt(proNum[j]));
							Product product=productRepository.findById(Long.parseLong(proId[j])).get();
							stockDetail.setProduct(product);
							stockDetail.setStock(stock);
							stockDetail.setStock(stock);
							stockDetailRepository.save(stockDetail);
							count=stock.getStockNum()+Integer.parseInt(proNum[j]);
							stock.setStockNum(count);
							stockRepository.save(stock);
						}
					}else if(Long.parseLong(proId[j])==list.get(i).getProduct().getId()){
						int countpro=list.get(i).getNum()+Integer.parseInt(proNum[j]);
						StockDetail stockDetail=list.get(i);
						stockDetail.setNum(countpro);
						stockDetailRepository.save(stockDetail);
						count=stock.getStockNum()+Integer.parseInt(proNum[j]);
						stock.setStockNum(count);
						stockRepository.save(stock);
					}
				}
			}
		}
		/*取出仓库库存详情表的操作*/
		for(int i=0;i<list1.size();i++) {
			for(int j=0;j<proId.length;j++) {
				if(list1.get(i).getProduct().getId()==Long.parseLong(proId[j])) {
					list1.get(i).setNum(list1.get(i).getNum()-Integer.parseInt(proNum[j]));
					stockDetailRepository.save(list1.get(i));
				}
			}
		}
		int all=0;
		for(int i=0;i<proNum.length;i++) {
			all=all+Integer.parseInt(proNum[i]);
		}
		stock1.setStockNum(stock1.getStockNum()-all);
		stockRepository.save(stock1);
		
		/*库存流水数据*/
		int num=0;
		SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Tranlog tranlog= new Tranlog();
		try {
			tranlog.setMoveDate(fromat.parse(fromat.format(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tranlog.setRepoFrom(repoRepository.findById(Long.parseLong(toSubmit[2])).get());
		tranlog.setRepoTo(repoRepository.findById(Long.parseLong(toSubmit[3])).get());
		for(int i=0;i<proId.length;i++) {
			TranDetail trandetail=new TranDetail();
			trandetail.setProduct(productRepository.findById(Long.parseLong(proId[i])).get());
			trandetail.setTranNum(Integer.parseInt(proNum[i]));
			trandetail.setTranlog(tranlog);
			tranDetailRepository.save(trandetail);
			num=num+Integer.parseInt(proNum[i]);
		}
		tranlog.setMoveNum(num);
		tranlogRepository.save(tranlog);
	}
	
	
	
	@Override
	public List<Object> findAll(String repoid){
		Stock stock=stockRepository.findOne(findstock(Long.parseLong(repoid))).get();
		List<StockDetail> list=stockDetailRepository.findAll(findstockDetail(stock.getId()));
		List finalList = new ArrayList();
		if(!list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				Map<String,Object> map1=new HashMap<String, Object>();
				map1.put("value",list.get(i).getProduct().getId() );
				map1.put("name", list.get(i).getProduct().getProductName()+"(剩余库存："+list.get(i).getNum()+")");
				finalList.add(map1);
				
			}	
		}
		return finalList;
		
	}
	
	@Override
	public List<Object> purchasepro(String pruid){
		List<PurchaseDetail> list=purchaseDetailRepository.findAll(findpurpro(Long.parseLong(pruid)));
		List finalList = new ArrayList();
		if(!list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				Map<String,Object> map1=new HashMap<String, Object>();
				map1.put("value",list.get(i).getId());
				map1.put("name", list.get(i).getProduct().getProductName()+"(数量：" +list.get(i).getPurchaseProductNum()+")");
				finalList.add(map1);
				
			}	
		}
		return finalList;
		
	}
	
	
	
	@Override
	public void tran(String []subitem){
		/*进货模块反馈*/
		Purchase purchase=purchaseRepository.findById(Long.parseLong(subitem[0])).get();
		purchase.setType("已审批");
		purchaseRepository.save(purchase);
		List<PurchaseDetail> allList=purchaseDetailRepository.findAll(findpurpro(purchase.getId()));
		
		/*若提交上来的Detail集为空 则把所有设置为不通过*/
		if(subitem.length==1) {
			for(int i=0;i<allList.size();i++) {
				allList.get(i).setPurchaseStatus("不通过");
				purchaseDetailRepository.save(allList.get(i));
			}
		}else {
			/*库存流水数据*/
			int num=0;
			SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Tranlog tranlog= new Tranlog();
			try {
				tranlog.setMoveDate(fromat.parse(fromat.format(new Date())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			tranlog.setRepoFrom(null);
			tranlog.setRepoTo(repoRepository.findById(purchase.getRepo().getId()).get());
			for(int i=0;i<subitem.length-1;i++) {
				TranDetail trandetail=new TranDetail();
				PurchaseDetail purchaseDetail=purchaseDetailRepository.findById(Long.parseLong(subitem[i+1])).get();
				trandetail.setProduct(purchaseDetail.getProduct());
				trandetail.setTranNum(Integer.parseInt(String.valueOf(purchaseDetail.getPurchaseProductNum())));
				trandetail.setTranlog(tranlog);
				tranDetailRepository.save(trandetail);
				num=num+Integer.parseInt(String.valueOf(purchaseDetail.getPurchaseProductNum()));
			}
			tranlog.setMoveNum(num);
			tranlogRepository.save(tranlog);
			
			/*加载进库存模块*/
			int count=0;
			Stock stock=stockRepository.findOne(findstock(purchase.getRepo().getId())).get();		//存入的仓库
			List<StockDetail> list=stockDetailRepository.findAll(findstockDetail(stock.getId()));	//获取存入仓库的全部库存详情表
			List<PurchaseDetail> listpurde=new ArrayList();
			for(int i=0;i<subitem.length-1;i++) {
				listpurde.add(purchaseDetailRepository.findById(Long.parseLong(subitem[i+1])).get());
			}
			for(int i=0;i<allList.size();i++) {
				int flag=0;
				for(int j=0;j<listpurde.size();j++) {
					if(allList.get(i).getId()!=listpurde.get(j).getId()) {
						flag++;
						if(flag==listpurde.size()) {
							allList.get(i).setPurchaseStatus("不通过");
							purchaseDetailRepository.save(allList.get(i));
						}
					}
				}
			}
			/*存入仓库库存详情表的操作*/
			if(list.isEmpty()==true) {
				for(int i=0;i<listpurde.size();i++) {
					listpurde.get(i).setPurchaseStatus("通过");
					purchaseDetailRepository.save(listpurde.get(i));
					StockDetail stockDetail=new StockDetail();
					stockDetail.setNum(Integer.parseInt(String.valueOf(listpurde.get(i).getPurchaseProductNum())));
					stockDetail.setProduct(listpurde.get(i).getProduct());
					stockDetail.setStock(stock);
					stockDetailRepository.save(stockDetail);
					count=count+Integer.parseInt(String.valueOf(listpurde.get(i).getPurchaseProductNum()));
				}
				stock.setStockNum(count);
				stockRepository.save(stock);
			}else if(!list.isEmpty()) {
				for(int j=0;j<listpurde.size();j++) {
					int flag=0;
					for(int i=0;i<list.size();i++)  {
						if(listpurde.get(j).getProduct().getId()!=list.get(i).getProduct().getId()) {
							flag++;
							if(flag==list.size()) {
								listpurde.get(j).setPurchaseStatus("通过");
								purchaseDetailRepository.save(listpurde.get(j));
								
								StockDetail stockDetail=new StockDetail();
								stockDetail.setNum(Integer.parseInt(String.valueOf(listpurde.get(j).getPurchaseProductNum())));
								stockDetail.setProduct(listpurde.get(j).getProduct());
								count=count+Integer.parseInt(String.valueOf(listpurde.get(j).getPurchaseProductNum()));
								stockDetail.setStock(stock);
								stockDetailRepository.save(stockDetail);
								stock.setStockNum(count);
								stockRepository.save(stock);
							}
						}else if(listpurde.get(j).getProduct().getId()==list.get(i).getProduct().getId()){
							listpurde.get(j).setPurchaseStatus("通过");
							purchaseDetailRepository.save(listpurde.get(j));
							
							int countpro=list.get(i).getNum()+Integer.parseInt(String.valueOf(listpurde.get(j).getPurchaseProductNum()));
							StockDetail stockDetail=list.get(i);
							stockDetail.setNum(countpro);
							stockDetailRepository.save(stockDetail);
							count=stock.getStockNum()+Integer.parseInt(String.valueOf(listpurde.get(j).getPurchaseProductNum()));
							stock.setStockNum(count);
							stockRepository.save(stock);
						}
					}
				}
			}
		}
	}
	
	@Override
	public Page<Purchase> findAll(Pageable pageable){
		
		Page<Purchase> page = purchaseRepository.findAll(findpurchase(), pageable);
		return page;
	}

	
	
	public Specification<Stock> findstock(Long toSubmit) {
		return new Specification<Stock>() {
			@Override
			public Predicate toPredicate(Root<Stock> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				predicate.add(criteriaBuilder.equal(root.join("repository").get("id").as(Long.class),toSubmit));
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	public Specification<PurchaseDetail> findpurpro(Long toSubmit) {
		return new Specification<PurchaseDetail>() {
			@Override
			public Predicate toPredicate(Root<PurchaseDetail> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				predicate.add(criteriaBuilder.equal(root.join("purchase").get("id").as(Long.class),toSubmit));
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	public Specification<Purchase> findpurchase() {
		return new Specification<Purchase>() {
			@Override
			public Predicate toPredicate(Root<Purchase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				predicate.add(criteriaBuilder.equal(root.get("type").as(String.class),"待审批"));
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	public Specification<StockDetail> findstockDetail(Long id) {
		return new Specification<StockDetail>() {
			@Override
			public Predicate toPredicate(Root<StockDetail> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				predicate.add(criteriaBuilder.equal(root.join("stock").get("id").as(Long.class),id));
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	
	
}
