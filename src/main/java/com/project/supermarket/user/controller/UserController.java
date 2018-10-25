package com.project.supermarket.user.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;
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
import com.project.supermarket.user.entity.Role;
import com.project.supermarket.user.entity.User;
import com.project.supermarket.user.entity.UserDTO;
import com.project.supermarket.user.entity.UserQueryDTO;
import com.project.supermarket.user.service.IRoleService;
import com.project.supermarket.user.service.IUserService;
import com.project.supermarket.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userSerivce;
	
	@Autowired
	private IRoleService roleService;
	
	@GetMapping
	public Page<UserDTO> getPage(UserQueryDTO userQueryDTO , ExtjsPageRequest pageRequest) 
	{
		return userSerivce.findAll(UserQueryDTO.getWhereClause(userQueryDTO),pageRequest.getPageable());
	}
	
	/*@RequestMapping("/data")
	public List<User> find()
	{
		return userSerivce.findAll();
	}*/
	
	@PutMapping(value="{id}")
	public ExtAjaxResponse update(@PathVariable("id") String myId,@RequestBody UserDTO userdto) 
	{
		
		User user = userSerivce.findById(myId).get();
		Optional<Role> role = roleService.findById(userdto.getGroupName());
		System.out.println("111");
		System.out.println(userdto);
		Set<Role> sets = new HashSet<Role>();
		sets.add(role.get());
		try {
			BeanUtils.copyProperties(userdto,user);
			user.setRole(sets);
			userSerivce.save(user);
			return new ExtAjaxResponse(true,"删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtAjaxResponse(true,"删除失败！");
		}
	}
	
	@PostMapping()
	public ExtAjaxResponse add(UserDTO userdto) 
	{
		User user = new User();
		Optional<Role> role = roleService.findById(userdto.getGroupName());
		System.out.println("111");
		System.out.println(userdto.getBirthday());
		Set<Role> sets = new HashSet<Role>();
		sets.add(role.get());
		try {
			BeanUtils.copyProperties(userdto,user);
			user.setPassword(DigestUtils.md5DigestAsHex(userdto.getPassword().getBytes("UTF8")));
			user.setRole(sets);
			userSerivce.save(user);
			return new ExtAjaxResponse(true,"添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtAjaxResponse(true,"添加失败！");
		}
		
	}
	
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse deleteRow(@PathVariable(name="id") String id) 
	{
		try {
			if(id!=null) {
				userSerivce.delete(id);
			}
			return new ExtAjaxResponse(true,"删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"删除失败！");
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") String[] ids) 
	{
		try {
			if(ids!=null) {
				userSerivce.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"删除失败！");
		}
	}
	
	@PostMapping("/changePassword")
	public ExtAjaxResponse changePassword(@RequestParam(name="userName")String userName,@RequestParam(name="newpassword")String password,@RequestParam(name="oldpassword")String oldpassword)
	{
		try {
			String userId = userSerivce.getUserIdByUserName(userName);
			String p = userSerivce.getPassword(userId);
			//System.out.println(p);
			if(p!= null)
			{
				if(p.equals(oldpassword))
				{
					userSerivce.updateUserPassword(userId, DigestUtils.md5DigestAsHex(password.getBytes("UTF8")));
					return new ExtAjaxResponse(true,"密码修改成功！");
				}
				else {
					return new ExtAjaxResponse(true,"原密码输入错误");
				}
			}
			else
				return new ExtAjaxResponse(true,"密码修改失败！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"密码修改失败！");
		}
	}
}
