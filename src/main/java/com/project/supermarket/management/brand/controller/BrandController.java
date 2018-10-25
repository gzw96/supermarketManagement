package com.project.supermarket.management.brand.controller;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.supermarket.common.web.ExtAjaxResponse;
import com.project.supermarket.common.web.ExtjsPageRequest;
import com.project.supermarket.management.brand.entity.Brand;
import com.project.supermarket.management.brand.service.IBrandService;





@RestController
@RequestMapping(value="/brand")
public class BrandController 
{
	@Autowired
	private IBrandService brandService;
		
	
	@PostMapping
    public ExtAjaxResponse save(@RequestBody Brand brand) {
		
    	try {
    		brandService.save(brand);
    		return new ExtAjaxResponse(true,"保存成功!");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"保存失败!");
	    }
    }
	
	@RequestMapping(value = "/upload")
    public @ResponseBody ExtAjaxResponse deploy(@RequestParam(value = "id") Long id,@RequestParam(value = "brandName") String brandName,@RequestParam(value = "brandDesc") String brandDesc,@RequestParam(value = "brandImg") MultipartFile file) {
        try {
        	
        	SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        	Date today=new Date();
        	today=fromat.parse(fromat.format(today));
        	Brand entity = brandService.findOne(id);
        	String fileName = file.getOriginalFilename();
        	String realPath = System.getProperty("user.dir");
    		String filePath = realPath+"\\src\\main\\webapp\\resources\\images\\";
    		String realfile=filePath + fileName;
    		File dest = new File(realfile);
    		file.transferTo(dest);
    		if(entity!=null) {
				entity.setBrandImg(fileName);
				entity.setBrandDesc(brandDesc);
				entity.setBrandName(brandName);
				entity.setUpdateTime(today);
				brandService.save(entity);
			}
    		return new ExtAjaxResponse(true,"更新成功!");
        } catch (Exception e) {
	    	e.printStackTrace();
	        return new ExtAjaxResponse(false,"更新失败!");
	    }
    }
	
	@RequestMapping(value = "/quicksearch")
    public Page<Brand>  quicksearch(@RequestParam(value = "myArray") String myArray[],ExtjsPageRequest pageable) {
		Brand brand=new Brand();
    	myArray[0]=myArray[0]+" 00:00:00";
		myArray[1]=myArray[1]+" 23:59:59";
		Page<Brand> page=brandService.findAll(brandService.findQuick(brand, myArray), pageable.getPageable());
		return 	page;
    }
	
	
	@RequestMapping(value = "/moresearch")
    public Page<Brand>  moresearch(@RequestParam(value = "toSubmit") String toSubmit[],ExtjsPageRequest pageable) {
		Brand brand=new Brand();
		Page<Brand> page=brandService.findAll(brandService.moresearch(brand, toSubmit), pageable.getPageable());
		return 	page;
    }
	
	@PostMapping("/delete")
    public @ResponseBody ExtAjaxResponse delete(@RequestParam(name="id") Long id) {
    	try {
    		Brand entity = brandService.findOne(id);
			if(entity!=null) {
				brandService.delete(id);
			}
    		return new ExtAjaxResponse(true,"删除成功!");
	    } catch (Exception e) {
	        return new ExtAjaxResponse(false,"该品牌下还有商品，请先删除属于该品牌的商品!");
	    }
    }
	
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) 
	{
		try {
			if(ids!=null) {
				brandService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			 return new ExtAjaxResponse(false,"删除的品牌中有的品牌下还有商品，请先删除属于该品牌的商品!");
		}
	}
	
	
	@RequestMapping(value = "/getBrand")
    public List  getBrand() {
		List<Brand> resList = brandService.findAll();
		List list = new ArrayList();
		for(int i=0;i<resList.size();i++) {
			Map<String,Object> map1=new HashMap<String, Object>();
			map1.put("value", resList.get(i).getId());
			map1.put("name", resList.get(i).getBrandName());
			list.add(map1);
		}
		return list;
    }
	
	@GetMapping
    public Page<Brand> findPage(ExtjsPageRequest pageable) 
	{
		Page<Brand> page =  brandService.findAll(pageable.getPageable());
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
