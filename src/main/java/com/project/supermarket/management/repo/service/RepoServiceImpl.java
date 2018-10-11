package com.project.supermarket.management.repo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.management.repo.entity.RepoQueryDTO;
import com.project.supermarket.user.entity.User;


public interface RepoServiceImpl {
	public Repo save(Repo entity);
	public Optional<Repo> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	public Page<RepoQueryDTO> findAll(Specification<RepoQueryDTO> spec, Pageable pageable);
	
	public void deleteAll(Long[] ids);
	
	//public Specification<Repo> getNeedinit() ;
	//hql查询
	public Long getRepoUserId(String userName,Long workNum);
	public void  findUserById(Long userid,String repoName);
	public void updateRoleById(Long userid,Long repoId);
	public List<User> findAllActiveUserRealName();
}
