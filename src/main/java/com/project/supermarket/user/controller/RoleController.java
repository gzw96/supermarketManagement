package com.project.supermarket.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.supermarket.user.entity.Role;
import com.project.supermarket.user.entity.User;
import com.project.supermarket.user.service.IRoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	
	@PostMapping()
	public String add(@RequestBody Role role) 
	{
		try {
			roleService.save(role);
			return "success  "+role.toString();
		}catch (Exception e) {
			return "false";
		}
	}
	
	@DeleteMapping
	public String delete(@RequestParam(name="id")String id) 
	{
		try {
			roleService.deleteById(id);
			return "success";
		}catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
}
