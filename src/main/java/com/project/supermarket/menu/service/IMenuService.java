package com.project.supermarket.menu.service;

import java.util.List;

import com.project.supermarket.menu.entity.TreeNode;


public interface IMenuService {
	public List<TreeNode> findNodes(Long parentId);
}
