package com.project.supermarket.sale.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.project.supermarket.common.web.ExtjsPageRequest;
import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.sale.entity.SaleDetail;
import com.project.supermarket.sale.entity.SaleDetailDTO;
import com.project.supermarket.sale.service.ISaleDetailService;

@RestController
@RequestMapping("/saledetail")
public class SaleDetailController {

	@Autowired
	private ISaleDetailService saleDetailService;
	
	@GetMapping
	public Page<SaleDetail> getPage(SaleDetailDTO saleDetailDTO,ExtjsPageRequest pageRequest){
		return saleDetailService.findAll(SaleDetailDTO.getWhereClause(saleDetailDTO), pageRequest.getPageable());
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/getproductnums")
	@ResponseBody
	public String getAllProducts() {
		List<Product> nameString = saleDetailService.findAllProduct();
		Map<String, Object> map = new HashMap<>();
		map.put("root", nameString);
		
		String jsonString = JSON.toJSONString(map);
		return jsonString;
	}
	
}
