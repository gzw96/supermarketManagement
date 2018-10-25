package com.project.supermarket.sale.controller;





import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
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
import com.project.supermarket.common.web.ExtAjaxResponse;
import com.project.supermarket.common.web.ExtjsPageRequest;
import com.project.supermarket.sale.entity.Sale;
import com.project.supermarket.sale.entity.SaleHistoryQueryDTO;
import com.project.supermarket.sale.service.ISaleService;

@RestController
@RequestMapping("/salehistory")
public class SaleHistoryController {
	@Autowired
	private ISaleService saleService;
	
	@GetMapping
	public Page<Sale> getPage(SaleHistoryQueryDTO saleHistoryQueryDTO,ExtjsPageRequest pageRequest){
		return saleService.findAll(SaleHistoryQueryDTO.getWhereClause(saleHistoryQueryDTO), pageRequest.getPageable());
	}

	@GetMapping(value="{id}")
	public Sale getOne(@PathVariable("id") Long id) {
		return saleService.findById(id);
	}
	
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) {
		try {
			if (id!=null) {
				saleService.deleteById(id);
			}
			return new ExtAjaxResponse(true, "删除成功 ");
		} catch (Exception e) {
			return new ExtAjaxResponse(true, "删除失败");
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) {
		try {
			if (ids!=null) {
				saleService.deleteAll(ids);
				
				for (int i = 0; i < ids.length; i++) {
					saleService.deleteDetailBySaleId(ids[i]);
				}
			}
			return new ExtAjaxResponse(true, "deletes success");
		} catch (Exception e) {
			// TODO: handle exception
			return new ExtAjaxResponse(true, "deletes fail");
		}
	}
	
}
