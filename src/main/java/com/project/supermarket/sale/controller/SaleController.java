package com.project.supermarket.sale.controller;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.supermarket.sale.repository.SaleRepository;

@RestController
@RequestMapping("/sale")
public class SaleController {

	@Autowired
	public SaleRepository saleRepository;
	
	@RequestMapping(method=RequestMethod.POST,value="/saveDetail")
	@ResponseBody
	@Transactional
	public void saveDetail(@RequestBody String jString) {
		
		JSONObject root = new JSONObject().parseObject(jString);
		System.out.println("!1");
		String saleNum = root.getString("saleNum");
		Double statement = root.getDouble("statement");
		Double payment = root.getDouble("payment");
		String method = root.getString("method");
		String remark = root.getString("remark");
		String customerName = root.getString("customerName");
		Long customerPhone = root.getLong("customerPhone");
		String repoName = root.getString("repoName");
		String salerId = root.getString("salerId");
		Date saleTime = new Date();
		
		//Long repoId = saleRepository.getRepoId(repoName);由name设置仓库门店id后期补上
		
		//保存进货表   !!!后期补上repoid
		saleRepository.updateSale(saleNum, statement, payment, method, remark, saleTime, salerId, customerName,customerPhone);

		//保存详单0
		JSONArray list = root.getJSONArray("list");
		for (int i = 0; i < list.size(); i++) {
			JSONObject detailBean = (JSONObject) list.get(i);
			Long productId = detailBean.getLong("id");
			Double retailPrice = detailBean.getDouble("retailPrice");
			Integer saleProductNum = detailBean.getInteger("saleProductNum");
			
			Long saleId = saleRepository.getSaleIdBySaleNum(saleNum);
			saleRepository.updateSaleDetail(productId, saleProductNum, saleId);
		}
		
		//System.out.println(list);
	}
	
}
