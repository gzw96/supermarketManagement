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

import com.project.supermarket.common.util.BeanUtils;
import com.project.supermarket.common.web.ExtAjaxResponse;
import com.project.supermarket.common.web.ExtjsPageRequest;
import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.management.repo.entity.RepoQueryDTO;
import com.project.supermarket.stock.entity.Stock;
import com.project.supermarket.stock.service.StockService;






@RestController
@RequestMapping(value="/stock")
public class StockController 
{
	@Autowired
	private StockService stockService;
	
	@RequestMapping(value = "/initRepo")
    public ExtAjaxResponse initRepo(@RequestParam(name="ids") Long[] ids) {
		
    	try {
    		
    		stockService.save(ids);
    		
    		return new ExtAjaxResponse(true,"保存成功!");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"保存失败!");
	    }
    }
	
	@GetMapping("/getAllRepoCloumn")
	public List<Object> getAllRepoCloumn() 
	{
		return stockService.getAllRepoCloumn();
	}
	
	@GetMapping("/getRepoPie")
	public List<Object> getAllRepoPie(@RequestParam(name="reoid") String reoid) 
	{
		
		return stockService.getRepoPie(Long.parseLong(reoid));
	}
}
