package com.project.supermarket.purchase.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.supermarket.common.util.BeanUtils;
import com.project.supermarket.common.web.ExtAjaxResponse;
import com.project.supermarket.common.web.ExtjsPageRequest;
import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.management.supplier.entity.Supplier;
import com.project.supermarket.purchase.entity.Purchase;
import com.project.supermarket.purchase.entity.PurchaseDTO;
import com.project.supermarket.purchase.service.PurchaseHistoryServiceImpl;
import com.project.supermarket.user.entity.User;

@RestController
@RequestMapping("/purchasehistory")
public class PurchaseHistoryController {
	@Autowired
	private PurchaseHistoryServiceImpl purchaseHistoryService;
	
	@GetMapping
	public Page<Purchase> getPage(PurchaseDTO purchaseDTO,ExtjsPageRequest pageRequest){
		return purchaseHistoryService.findAll(PurchaseDTO.getWhereClause(purchaseDTO), pageRequest.getPageable());
	}

	
	@GetMapping(value="{id}")
	public Purchase getOne(@PathVariable("id") Long id) {
		return purchaseHistoryService.findById(id).get();
	}
	
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) {
		try {
			if (id!=null) {
				purchaseHistoryService.deleteById(id);
			}
			return new ExtAjaxResponse(true, "delete successfully");
		} catch (Exception e) {
			// TODO: handle exception
			return new ExtAjaxResponse(true, "delete fail");
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) {
		try {
			if (ids!=null) {
				purchaseHistoryService.deleteAll(ids);
				
				for (int i = 0; i < ids.length; i++) {
					purchaseHistoryService.deleteDetailByPuchaseId(ids[i]);
				}
			}
			return new ExtAjaxResponse(true, "deletes success");
		} catch (Exception e) {
			// TODO: handle exception
			return new ExtAjaxResponse(true, "deletes fail");
		}
	}
	
	@PutMapping(value="{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse update (@PathVariable("id") Long myId,@RequestBody PurchaseDTO purchaseDTO) {
		try {
			Purchase purchase = purchaseHistoryService.findById(myId).get();
			if (purchase!=null) {
				BeanUtils.copyProperties(purchaseDTO, purchase);
				//获取采购员id
				if ((purchaseDTO.getRepoName()!=null)& (purchaseDTO.getSupplierName()==null) & (purchaseDTO.getUserRealName()==null)) {
					//获取仓库id
					String repoName = purchaseDTO.getRepoName();
					Long repoId = purchaseHistoryService.getPurchaseRepoId(repoName);
					purchaseHistoryService.save(purchase);
					purchaseHistoryService.updateRepoById(repoId, myId);
				}
				else if((purchaseDTO.getSupplierName()!=null)&(purchaseDTO.getRepoName()==null)&(purchaseDTO.getUserRealName()==null)){
					//获取供货商id
					String supplierName = purchaseDTO.getSupplierName();
					Long supplierId = purchaseHistoryService.getPurchaseSupplierId(supplierName);
					purchaseHistoryService.save(purchase);
					purchaseHistoryService.updateSupplierById(supplierId, myId);
				}
				else if ((purchaseDTO.getUserRealName()!=null)&(purchaseDTO.getSupplierName()==null)&(purchaseDTO.getRepoName()==null)) {
					String uName = purchaseDTO.getUserRealName();
					Long num = purchaseDTO.getWorkNum();
					String uId = purchaseHistoryService.getPurchaseUserId(uName, num);
					purchaseHistoryService.save(purchase);
					//保存外键
					purchaseHistoryService.updateOperatorById(uId, myId);
				}
				else if ((purchaseDTO.getRepoName()!=null)& (purchaseDTO.getSupplierName()!=null) & (purchaseDTO.getUserRealName()==null)) {
					String repoName = purchaseDTO.getRepoName();
					Long repoId = purchaseHistoryService.getPurchaseRepoId(repoName);
					String supplierName = purchaseDTO.getSupplierName();
					Long supplierId = purchaseHistoryService.getPurchaseSupplierId(supplierName);
					purchaseHistoryService.save(purchase);
					purchaseHistoryService.updateRepoById(repoId, myId);
					purchaseHistoryService.updateSupplierById(supplierId, myId);
				}
				else if ((purchaseDTO.getRepoName()!=null)& (purchaseDTO.getSupplierName()==null) & (purchaseDTO.getUserRealName()!=null)) {
					String repoName = purchaseDTO.getRepoName();
					Long repoId = purchaseHistoryService.getPurchaseRepoId(repoName);
					String uName = purchaseDTO.getUserRealName();
					Long num = purchaseDTO.getWorkNum();
					String uId = purchaseHistoryService.getPurchaseUserId(uName, num);
					purchaseHistoryService.save(purchase);
					purchaseHistoryService.updateRepoById(repoId, myId);
					purchaseHistoryService.updateOperatorById(uId, myId);
				}
				else if ((purchaseDTO.getRepoName()==null)& (purchaseDTO.getSupplierName()!=null) & (purchaseDTO.getUserRealName()!=null)) {
					String supplierName = purchaseDTO.getSupplierName();
					Long supplierId = purchaseHistoryService.getPurchaseSupplierId(supplierName);
					String uName = purchaseDTO.getUserRealName();
					Long num = purchaseDTO.getWorkNum();
					String uId = purchaseHistoryService.getPurchaseUserId(uName, num);
					purchaseHistoryService.save(purchase);
					purchaseHistoryService.updateSupplierById(supplierId, myId);
					purchaseHistoryService.updateOperatorById(uId, myId);
				}
				else if ((purchaseDTO.getRepoName()!=null)& (purchaseDTO.getSupplierName()!=null) & (purchaseDTO.getUserRealName()!=null)) {
					String supplierName = purchaseDTO.getSupplierName();
					Long supplierId = purchaseHistoryService.getPurchaseSupplierId(supplierName);
					String uName = purchaseDTO.getUserRealName();
					Long num = purchaseDTO.getWorkNum();
					String uId = purchaseHistoryService.getPurchaseUserId(uName, num);
					String repoName = purchaseDTO.getRepoName();
					Long repoId = purchaseHistoryService.getPurchaseRepoId(repoName);
					purchaseHistoryService.save(purchase);
					purchaseHistoryService.updateSupplierById(supplierId, myId);
					purchaseHistoryService.updateOperatorById(uId, myId);
					purchaseHistoryService.updateRepoById(repoId, myId);
				}
				else {
					purchaseHistoryService.save(purchase);
					//保存外键
				}
			}
			return new ExtAjaxResponse(true, "update successfully");
		} catch (Exception e) {
			// TODO: handle exception
			return new ExtAjaxResponse(true, "update fail");
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/getusers")
	@ResponseBody
	public String getAllActiveUser() {
		List<User> nameString = purchaseHistoryService.findAllActiveUserRealName();
		Map<String, Object> map = new HashMap<>();
		map.put("root", nameString);
		
		String jsonString = JSON.toJSONString(map);
		return jsonString;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/getrepos")
	@ResponseBody
	public String getAllRepos() {
		List<String> nameString = purchaseHistoryService.findAllRepo();
		//将reponame放入json
		JSONArray array = new JSONArray();
		for (int i = 0; i < nameString.size(); i++) {
			JSONObject object = new JSONObject();
			object.put("repoName", nameString.get(i));
			array.add(object);
		}
		String jsonString = array.toString();
		return jsonString;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/getsuppliers")
	@ResponseBody
	public String getAllSuppliers() {
		List<Supplier> nameString = purchaseHistoryService.findAllSupplier();
		Map<String, Object> map = new HashMap<>();
		map.put("root", nameString);
		
		String jsonString = JSON.toJSONString(map);
		return jsonString;
	}
}
