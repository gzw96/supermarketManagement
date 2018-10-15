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
		//System.out.println(repoid);
		List<Object> resList = stockDetailService.findAll(repoid);	
		
		return resList;
	}
	
	/*@RequestMapping(value = "/upload")
    public @ResponseBody ExtAjaxResponse deploy(@RequestParam(value = "stockPrice") Double stockPrice,@RequestParam(value = "status") String status,@RequestParam(value = "getBrandName") String getBrandName,@RequestParam(value = "id") Long id,@RequestParam(value = "stockName") String stockName,@RequestParam(value = "stockImg") MultipartFile file) {
        try {
        	long l=Long.parseLong(getBrandName);
        	SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        	Date today=new Date();
        	today=fromat.parse(fromat.format(today));
        	Stock entity = stockService.findOne(id);
        	String fileName = file.getOriginalFilename();
        	String realPath = System.getProperty("user.dir");
    		String filePath = realPath+"\\src\\main\\webapp\\resources\\images\\";
    		String realfile=filePath + fileName;
    		File dest = new File(realfile);
    		file.transferTo(dest);
    		if(entity!=null) {
				entity.setUpdateTime(today);
				entity.setStockImg(fileName);
				entity.setStockPrice(stockPrice);
				entity.setStatus(status);
				entity.setStockName(stockName);
				entity.setGetBrandName(l);
				stockService.save(entity);
			}
    		return new ExtAjaxResponse(true,"更新成功!");
        } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"更新失败!");
	    }
    }
	
	@RequestMapping(value = "/quicksearch")
    public Page<Stock>  quicksearch(@RequestParam(value = "myArray") String myArray[],ExtjsPageRequest pageable) {
		Stock stock=new Stock();
		Page<Stock> page=stockService.findAll(stockService.findQuick(stock, myArray), pageable.getPageable());
		return 	page;
    }
	
	@RequestMapping(value = "/moresearch")
    public Page<Stock>  moresearch(@RequestParam(value = "toSubmit") String toSubmit[],ExtjsPageRequest pageable) {
		Stock stock=new Stock();
		Page<Stock> page=stockService.findAll(stockService.moresearch(stock, toSubmit), pageable.getPageable());
		return 	page;
    }
	
	@DeleteMapping(value="{id}")
    public @ResponseBody ExtAjaxResponse delete(@PathVariable("id") Long id) {
    	try {
    		Stock entity = stockService.findOne(id);
			if(entity!=null) {
				stockService.delete(id);
			}
    		return new ExtAjaxResponse(true,"删除成功!");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"删除失败!");
	    }
    }
	
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) 
	{
		try {
			if(ids!=null) {
				stockService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	
	@GetMapping
    public Page<Stock> findPage(ExtjsPageRequest pageable) 
	{
		Page<Stock> page =  stockService.findAll(pageable.getPageable());
		return page;
    }
	
	/*@RequestMapping(value = "/xialakuang")  //测试用
    public @ResponseBody List xialakuang() {
			
			List list = new ArrayList();
			
			Map<String,Object> map2=new HashMap<String, Object>();
			//Map<String,String> map2=new HashMap<String, String>();
			for(int i=0;i<5;i++) {
				Map<String,Object> map1=new HashMap<String, Object>();
		        map1.put("value", i);
		        map1.put("name", i);
		        list.add(map1);
			}
			
	       // JSONObject json = JSONObject.fromObject(list);
	        System.out.println(list);
    		return list;
    }*/
}
