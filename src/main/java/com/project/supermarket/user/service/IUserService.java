package com.project.supermarket.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.project.supermarket.user.entity.User;
import com.project.supermarket.user.entity.UserDTO;

public interface IUserService {
	public User save(User entity);
	public Optional<User> findById(String id);
	public boolean existsById(String id);
	public long count();
	public Page<UserDTO> findAll(Specification<User> specification,Pageable pageable);
	public void deleteAll(String []ids);
	public List<User> findAll();
	public void delete(String id);
	public void updateUserPassword(String userId,String newPassword);
	public String  getUserIdByUserName(String userName);
	public String getPassword(String id);
}
