package com.project.supermarket.management.repo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.project.supermarket.common.util.BeanUtils;
import com.project.supermarket.common.web.ExtAjaxResponse;
import com.project.supermarket.common.web.ExtjsPageRequest;
import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.management.repo.entity.RepoQueryDTO;
import com.project.supermarket.management.repo.service.RepoServiceImpl;
import com.project.supermarket.user.entity.User;

@RestController
@RequestMapping("/repo")
public class RepoController {
	
	@Autowired
	private RepoServiceImpl repoService;
	
	@GetMapping
	public Page<RepoQueryDTO> getPage(RepoQueryDTO repoQueryDTO,ExtjsPageRequest pageRequest) {
		return repoService.findAll(repoQueryDTO.getWhereClause(repoQueryDTO), pageRequest.getPageable());
	}
	
	@GetMapping(value="{id}")
	public Repo getOne(@PathVariable("id") Long id) {
		return repoService.findById(id).get();
	}
	
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) {
		try {
			if (id!=null) {
				repoService.deleteById(id);
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
				repoService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	
	@GetMapping("/getUninit")
	public Page<RepoQueryDTO> getUninit(ExtjsPageRequest pageable) 
	{
		RepoQueryDTO dto=new RepoQueryDTO();
		Page<RepoQueryDTO> page=repoService.findAll(repoService.getNeedinit(), pageable.getPageable());
		return 	page;
	}
	
	@PutMapping(value="{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse update(@PathVariable("id") Long myId,@RequestBody RepoQueryDTO dto) {//修改
		try {
			Repo repo = repoService.findById(myId).get();//获取对应id的entity
			if (repo!=null) {
				//把dto的属性复制到entity中，并保存到数据库
				BeanUtils.copyProperties(dto, repo);
				String uName = dto.getUserRealName();
				Long num = dto.getWorkNum();
				
				
				Long uId = repoService.getRepoUserId(uName, num);
				
				repoService.save(repo);
				repoService.updateRoleById(uId, myId);
				
			}
			return new ExtAjaxResponse(true,"update success");
		} catch (Exception e) {
			// TODO: handle exception
			return new ExtAjaxResponse(true,"update fail");
		}
	}
	
	@PostMapping(consumes="application/json")
	public ExtAjaxResponse save(@RequestBody RepoQueryDTO repo) {
		try {
			//repo.init(repo);
			Long num = repo.getWorkNum();
			String uName = repo.getUserRealName();
			Long uId;
			uId = repoService.getRepoUserId(uName, num);
			
	        //System.out.println(uId);
	        
	        repo.setRoleId(uId);
			Repo repo1 = new Repo();
			repo.dtoToEntity(repo, repo1);
			String repoName = repo.getRepoName();
			
			//System.out.println(repoName);
			
			Repo newrepo = new Repo();
			BeanUtils.copyProperties(repo1, newrepo);
			repoService.save(newrepo);//保存完之后外键为空。。。
			repoService.findUserById(uId,repoName);//把外键设置好
			//查找对应userid,并保存
			
			return new ExtAjaxResponse(true,"save success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ExtAjaxResponse(true,"save fail");
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/getusers")
	@ResponseBody
	public String getAllUser(){
		List<User> nameString = repoService.findAllActiveUserRealName();
		
		Map<String,Object> map = new HashMap<>();
	    map.put("root",nameString);
	    
	    String jsonString = JSON.toJSONString(map);
		return jsonString;
	}
}
