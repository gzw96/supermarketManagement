package com.project.supermarket.menu.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.supermarket.menu.entity.Menu;
import com.project.supermarket.menu.entity.TreeNode;
import com.project.supermarket.menu.repository.MenuRepository;



@Service
@Transactional
public class MenuService implements IMenuService{

	@Autowired
	private MenuRepository menuDao;
	
	@Override
	public List<TreeNode> findNodes(Long parentId) 
	{
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		
		List<Menu> lists;
		if(parentId==null) {
			lists =  menuDao.findParentNodes();
		}else {
			lists =  menuDao.findChildNodes(parentId);
		}
		
		for(Menu c : lists) {
			TreeNode node  = new TreeNode();
			
			node.setId(c.getId());
			node.setText(c.getName());
			node.setIconCls(c.getIcon());
			node.setViewType(c.getView());
			
			if(c.getChildNodes()!=null) {
				if(c.getChildNodes().size()>0) {
					node.setLeaf(false);//设置为父节点
				}else {
					node.setLeaf(true);//设置为子节点
				}
			}
			nodeList.add(node);
		}
		return nodeList;
	}

}
