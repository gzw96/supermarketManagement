package com.project.supermarket.management.repo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.management.repo.entity.RepoQueryDTO;
import com.project.supermarket.user.entity.User;

@Repository
public interface RepoRepository extends JpaSpecificationExecutor<RepoQueryDTO>,PagingAndSortingRepository<Repo, Long>{
	@Query("select u.id from User u where u.userRealName like ?1 and u.workNum like ?2")
	public String getRepoUserId(String userRealName,Long workNum);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update repository set role_id = ?1 where repo_name = ?2 ",nativeQuery=true)
	public void findUserById(String userid,String repoName);

	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update repository set role_id = ?1 where id = ?2 ",nativeQuery=true)
	public void updateRoleById(String userid,Long repoId);
	
	@Query("from User u where u.status = 'yes' ")
	public List<User> findAllActiveUserRealName();
}
