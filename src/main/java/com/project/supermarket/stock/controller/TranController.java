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
import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.management.product.service.IProductService;
import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.management.repo.entity.RepoQueryDTO;
import com.project.supermarket.stock.entity.TranDetail;
import com.project.supermarket.stock.entity.Tranlog;
import com.project.supermarket.stock.service.ITranService;
import com.project.supermarket.stock.service.TranService;





@RestController
@RequestMapping(value="/tran")
public class TranController 
{
	@Autowired
	private TranService tranService;
	
	
	@GetMapping
    public Page<Object> findPage(ExtjsPageRequest pageable) 
	{
		
		return tranService.findAll(pageable.getPageable());
    }
	
	

}
