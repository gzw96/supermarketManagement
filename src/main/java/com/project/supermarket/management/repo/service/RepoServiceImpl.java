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
	public List<RepoQueryDTO> findAll(Specification<RepoQueryDTO> spec);
	
	public void deleteAll(Long[] ids);
	public Specification<RepoQueryDTO> getRepo() ;
	public Specification<RepoQueryDTO> setRepo() ;
	public Specification<RepoQueryDTO> getNeedinit() ;
	
	//hql查询
	public String getRepoUserId(String userName,Long workNum);
	public void  findUserById(String userid,String repoName);
	public void updateRoleById(String userid,Long repoId);
	public List<User> findAllActiveUserRealName();
}
