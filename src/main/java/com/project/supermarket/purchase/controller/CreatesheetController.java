package com.project.supermarket.purchase.controller;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.supermarket.purchase.service.PurchaseDetailServiceImpl;
import com.project.supermarket.purchase.service.PurchaseHistoryServiceImpl;

@Controller
@RequestMapping("/createsheet")
public class CreatesheetController {

	@Autowired
	private PurchaseHistoryServiceImpl purchaseHistoryService;

	
	@RequestMapping(method=RequestMethod.POST,value="/saveDetail")
	@ResponseBody
	@Transactional
	public void saveDetail(@RequestBody String jString) {
		
		JSONObject root = new JSONObject().parseObject(jString);
		
		String purchaseNum = root.getString("purchaseNum");
		Double statement = root.getDouble("statement");
		Double payment = root.getDouble("payment");
		String method = root.getString("method");
		String remark = root.getString("remark");
		String supplierName = root.getString("supplierName");
		String repoName = root.getString("repoName");
		String userRealName = root.getString("userRealName");
		Long workNum = root.getLong("workNum");
		Date purchaseTime = new Date();
		
		String userId = purchaseHistoryService.getPurchaseUserId(userRealName, workNum);
		System.out.println(userId);
		Long supplierId = purchaseHistoryService.getPurchaseSupplierId(supplierName);
		Long repoId = purchaseHistoryService.getPurchaseRepoId(repoName);
		
		//保存进货表
		purchaseHistoryService.updatePurchase(purchaseNum, statement, payment, method, remark, purchaseTime, userId, supplierId, repoId);

		//保存详单0
		JSONArray list = root.getJSONArray("list");
		for (int i = 0; i < list.size(); i++) {
			JSONObject detailBean = (JSONObject) list.get(i);
			Long productId = detailBean.getLong("id");
//			System.out.println(productId);
			Double purchasePrice = detailBean.getDouble("purchasePrice");
			Integer purchaseProductNum = detailBean.getInteger("purchaseProductNum");
			
			Long purchaseId = purchaseHistoryService.getPurchaseIdByPurchaseNum(purchaseNum);
//			System.out.println(purchaseNum);
//			System.out.println(purchaseId);
			purchaseHistoryService.updatePurchaseDetail(productId, purchasePrice, purchaseProductNum, purchaseId);
		}
		
		//System.out.println(list);
	}
	
}
