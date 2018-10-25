package com.project.supermarket.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.supermarket.user.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User,String>,JpaSpecificationExecutor<User>{
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update act_id_user set PWD_ = ?1 where ID_ = ?2 ",nativeQuery=true)
	public void updateUserPassword(String Password, String userId);
	
	@Query("select u.id from User u where u.userName = ?1")
	public String getUserIdByUserName(String userName);
	
	@Query("select u.password from User u where u.id = ?1")
	public String getPassword(String userId);
}
