package com.project.supermarket.stock.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.supermarket.common.web.ExtAjaxResponse;
import com.project.supermarket.common.web.ExtjsPageRequest;
import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.purchase.entity.Purchase;
import com.project.supermarket.stock.entity.Stock;
import com.project.supermarket.stock.service.StockDetailService;
import com.project.supermarket.stock.service.StockService;






@RestController
@RequestMapping(value="/stockDetail")
public class StockDetailController 
{
	@Autowired
	private StockDetailService stockDetailService;
	
	@RequestMapping(value = "/save")
    public void save(@RequestParam(name="toSubmit") String[] toSubmit) {
		
		stockDetailService.save(toSubmit);
    	
    }
	
	@RequestMapping(value = "/getProduct")
	public List<Object> getProduct(@RequestParam(value = "repoid") String repoid) 
	{
		
		List<Object> resList = stockDetailService.findAll(repoid);	
		
		return resList;
	}
	
	@RequestMapping(value = "/getPurchase")
    public Page<Purchase>  moresearch(ExtjsPageRequest pageable) {
		Page<Purchase> page=stockDetailService.findAll(pageable.getPageable());
		return 	page;
    }
	
	@RequestMapping(value = "/purchasepro")
	public List<Object> purchasepro(@RequestParam(value = "purid") String purid) 
	{

		List<Object> resList = stockDetailService.purchasepro(purid);
		
		return resList;
		
	}
	
	@RequestMapping(value = "/tran")
	public ExtAjaxResponse tran(@RequestParam(value = "subitem") String []subitem) 
	{
		try{
			stockDetailService.tran(subitem);
			return new ExtAjaxResponse(true,"进货审核成功!");
		} catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"更新失败!");
	    }
		
		
	}

}
