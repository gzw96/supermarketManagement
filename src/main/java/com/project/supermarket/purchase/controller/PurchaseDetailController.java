package com.project.supermarket.purchase.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.project.supermarket.common.util.BeanUtils;
import com.project.supermarket.common.web.ExtAjaxResponse;
import com.project.supermarket.common.web.ExtjsPageRequest;
import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.purchase.entity.PurchaseDetail;
import com.project.supermarket.purchase.entity.PurchaseDetailDTO;
import com.project.supermarket.purchase.service.PurchaseDetailServiceImpl;

@RestController
@RequestMapping("/purchasedetail")
public class PurchaseDetailController {

	@Autowired
	private PurchaseDetailServiceImpl purchaseDetailService;
	
	@GetMapping
	public Page<PurchaseDetail> getPage(PurchaseDetailDTO purchaseDetailDTO,ExtjsPageRequest pageRequest){
		return purchaseDetailService.findAll(PurchaseDetailDTO.getWhereClause(purchaseDetailDTO), pageRequest.getPageable());
	}

	@PutMapping(value="{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse update (@PathVariable("id") Long myId,@RequestBody PurchaseDetailDTO purchaseDetailDTO) {
		try {
			PurchaseDetail purchaseDetail = purchaseDetailService.findById(myId).get();
			if (purchaseDetail!=null) {
				BeanUtils.copyProperties(purchaseDetailDTO, purchaseDetail);
				if (purchaseDetailDTO.getProductNum()!=null) {
					Long productid = purchaseDetailService.getProductIdByProductNum(purchaseDetailDTO.getProductNum());
					System.out.println(productid);

					purchaseDetailService.save(purchaseDetail);
					purchaseDetailService.updatePurchaseRepo(productid, myId);
				}
				
				else {
					purchaseDetailService.save(purchaseDetail);
				}
			}
			return new ExtAjaxResponse(true, "update successfully");
		} catch (Exception e) {
			// TODO: handle exception
			return new ExtAjaxResponse(true, "update fail");
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/getproductnums")
	@ResponseBody
	public String getAllProducts() {
		List<String> nameString = purchaseDetailService.findAllProduct();
		Map<String, Object> map = new HashMap<>();
		map.put("root", nameString);
		
		String jsonString = JSON.toJSONString(map);
		return jsonString;
	}
	
	
}
