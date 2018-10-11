package com.project.supermarket.management.repo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.supermarket.management.product.entity.Product;
import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.management.repo.entity.RepoQueryDTO;
import com.project.supermarket.management.repo.repository.RepoRepository;
import com.project.supermarket.user.entity.User;

@Service
public class RepoService implements RepoServiceImpl{

	@Autowired
	private RepoRepository repoRepository;
	
	@Override
	public Repo save(Repo entity) {
		// TODO Auto-generated method stub
		return repoRepository.save(entity);
	}

	@Override
	public Optional<Repo> findById(Long id) {
		// TODO Auto-generated method stub
		return repoRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return repoRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return repoRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repoRepository.deleteById(id);
	}

	@Override
	public Page<RepoQueryDTO> findAll(Specification<RepoQueryDTO> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return repoRepository.findAll(spec, pageable);
	}
	
	
	@Override
	public void deleteAll(Long[] ids) {
		// TODO Auto-generated method stub
		List<Long> idsList = new ArrayList<Long>(Arrays.asList(ids));
		List<Repo> Repos = (List<Repo>) repoRepository.findAllById(idsList);
		if(Repos!=null) {
			repoRepository.deleteAll(Repos);
		}
	}

	@Override
	public Long getRepoUserId(String userName,Long workNum) {
		// TODO Auto-generated method stub
		return repoRepository.getRepoUserId(userName,workNum);
	}

	@Override
	public void findUserById(Long userid, String repoName) {
		// TODO Auto-generated method stub
		repoRepository.findUserById(userid, repoName);
	}

	@Override
	public void updateRoleById(Long userid, Long repoId) {
		// TODO Auto-generated method stub
		repoRepository.updateRoleById(userid, repoId);
	}

	@Override
	public List<User> findAllActiveUserRealName() {
		// TODO Auto-generated method stub
		return repoRepository.findAllActiveUserRealName();
	}

	@Override
	public Specification<RepoQueryDTO> getNeedinit() {
		return new Specification<RepoQueryDTO>() {
			@Override
			public Predicate toPredicate(Root<RepoQueryDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				predicate.add(criteriaBuilder.isNull(root.get("stock")));
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
}
