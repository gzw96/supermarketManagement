package com.project.supermarket.menu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.supermarket.menu.entity.TreeNode;
import com.project.supermarket.menu.service.IMenuService;
import com.project.supermarket.common.web.SessionUtil;


public class MenuController {

	@Autowired
    private IMenuService menuService;
	
	@RequestMapping("/findNodes")
	public @ResponseBody List<TreeNode> findNodesByParentId(HttpSession session)
	{
		System.out.println("1111");
		System.out.println(SessionUtil.getGroupNames(session));
		if(SessionUtil.getGroupNames(session)=="SuperAdmin") {
			return menuService.findNodes(null);
		}
		else if(SessionUtil.getGroupNames(session)=="Purchase")
		{
			return menuService.findNodes(Long.parseLong("2"));
		}
		else if(SessionUtil.getGroupNames(session)=="Sale")
		{
			return menuService.findNodes(Long.parseLong("3"));
		}
		else if(SessionUtil.getGroupNames(session)=="Stock")
		{
			return menuService.findNodes(Long.parseLong("4"));
		}
		else if(SessionUtil.getGroupNames(session)=="Admin")
		{
			return menuService.findNodes(null);
		}
		else
			return null;
	}
}
