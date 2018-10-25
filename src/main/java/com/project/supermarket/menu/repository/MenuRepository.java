package com.project.supermarket.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.supermarket.menu.entity.Menu;


@Repository
public interface MenuRepository extends CrudRepository<Menu, Long>{
	@Query("from Menu m where m.parentNode.id = null")
	public List<Menu> findParentNodes();
	
	@Query("from Menu m where m.parentNode.id = ?1")
	public List<Menu> findChildNodes(Long parentId);


}