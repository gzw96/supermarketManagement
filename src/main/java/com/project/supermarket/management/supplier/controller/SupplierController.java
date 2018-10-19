package com.project.supermarket.management.supplier.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.supermarket.common.util.BeanUtils;
import com.project.supermarket.common.web.ExtAjaxResponse;
import com.project.supermarket.common.web.ExtjsPageRequest;
import com.project.supermarket.management.supplier.entity.Supplier;
import com.project.supermarket.management.supplier.entity.SupplierQueryDTO;
import com.project.supermarket.management.supplier.service.SupplierServiceImpl;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	private SupplierServiceImpl supplierServiceImpl;
	
	@GetMapping
	public Page<Supplier> getPage(SupplierQueryDTO supplierQueryDTO,ExtjsPageRequest pageRequest) {
		return supplierServiceImpl.findAll(SupplierQueryDTO.getWhereClause(supplierQueryDTO), pageRequest.getPageable());
		
	}
	
	@GetMapping(value="{id}")
	public Supplier getOne(@PathVariable("id") Long id) {
		return supplierServiceImpl.findById(id).get();
	}
	
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) {
		try {
			if (id!=null) {
				supplierServiceImpl.deleteById(id);
			}
			return new ExtAjaxResponse(true ,"delete successfully");
		} catch (Exception e) {
			// TODO: handle exception
			return new ExtAjaxResponse(true,"delete fail");
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) 
	{
		try {
			if(ids!=null) {
				supplierServiceImpl.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	
	@PutMapping(value="{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse update(@PathVariable("id") Long myId,@RequestBody Supplier dto) {
		try {
			Supplier supplier = supplierServiceImpl.findById(myId).get();
			if (supplier!=null) {
				BeanUtils.copyProperties(dto, supplier);
				supplierServiceImpl.save(supplier);
			}
			return new ExtAjaxResponse(true,"update success");
		} catch (Exception e) {
			// TODO: handle exception
			return new ExtAjaxResponse(true,"update fail");
		}
	}
	
	@PostMapping(consumes="application/json")
	public ExtAjaxResponse save(@RequestBody Supplier supplier) {
		try {
			supplierServiceImpl.save(supplier);
			return new ExtAjaxResponse(true,"save success");
		} catch (Exception e) {
			// TODO: handle exception
			return new ExtAjaxResponse(true,"save fail");
		}
	}
}
