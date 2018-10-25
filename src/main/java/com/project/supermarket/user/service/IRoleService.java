package com.project.supermarket.user.service;

import java.util.Optional;


import com.project.supermarket.user.entity.Role;

public interface IRoleService {
	public Role save(Role entity);
	public Optional<Role> findById(String id);
	public boolean existsById(String id);
	public long count();
	public void deleteById(String id);
}
