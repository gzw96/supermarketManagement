package com.project.supermarket.management.product.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;





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
import com.project.supermarket.management.product.service.IProductService;





@RestController
@RequestMapping(value="/product")
public class ProductController 
{
	@Autowired
	private IProductService productService;
	
	@PostMapping
    public ExtAjaxResponse save(@RequestBody Product product) {
		
    	try {
    		
    		productService.save(product);
    		
    		return new ExtAjaxResponse(true,"保存成功!");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"保存失败!");
	    }
    }
	
	@RequestMapping(value = "/upload")
    public @ResponseBody ExtAjaxResponse deploy(@RequestParam(value = "productPrice") Double productPrice,@RequestParam(value = "status") String status,@RequestParam(value = "getBrandName") String getBrandName,@RequestParam(value = "id") Long id,@RequestParam(value = "productName") String productName,@RequestParam(value = "productImg") MultipartFile file) {
        try {
        	long l=Long.parseLong(getBrandName);
        	SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        	Date today=new Date();
        	today=fromat.parse(fromat.format(today));
        	Product entity = productService.findOne(id);
        	String fileName = file.getOriginalFilename();
        	String realPath = System.getProperty("user.dir");
    		String filePath = realPath+"\\src\\main\\webapp\\resources\\images\\";
    		String realfile=filePath + fileName;
    		File dest = new File(realfile);
    		file.transferTo(dest);
    		if(entity!=null) {
				entity.setUpdateTime(today);
				entity.setProductImg(fileName);
				entity.setProductPrice(productPrice);
				entity.setStatus(status);
				entity.setProductName(productName);
				entity.setGetBrandName(l);
				productService.save(entity);
			}
    		return new ExtAjaxResponse(true,"更新成功!");
        } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"更新失败!");
	    }
    }
	
	@RequestMapping(value = "/quicksearch")
    public Page<Product>  quicksearch(@RequestParam(value = "myArray") String myArray[],ExtjsPageRequest pageable) {
		Product product=new Product();
		Page<Product> page=productService.findAll(productService.findQuick(product, myArray), pageable.getPageable());
		return 	page;
    }
	
	@RequestMapping(value = "/moresearch")
    public Page<Product>  moresearch(@RequestParam(value = "toSubmit") String toSubmit[],ExtjsPageRequest pageable) {
		Product product=new Product();
		Page<Product> page=productService.findAll(productService.moresearch(product, toSubmit), pageable.getPageable());
		return 	page;
    }
	
	@DeleteMapping(value="{id}")
    public @ResponseBody ExtAjaxResponse delete(@PathVariable("id") Long id) {
    	try {
    		Product entity = productService.findOne(id);
			if(entity!=null) {
				productService.delete(id);
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
				productService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	
	@GetMapping
    public Page<Product> findPage(ExtjsPageRequest pageable) 
	{
		Page<Product> page =  productService.findAll(pageable.getPageable());
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
