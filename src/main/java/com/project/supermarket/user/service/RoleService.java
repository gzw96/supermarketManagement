package com.project.supermarket.user.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.project.supermarket.user.entity.Role;
import com.project.supermarket.user.repository.RoleRepository;

@Service
@Transactional
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role save(Role entity) {
		return roleRepository.save(entity);
	}
	
	@Override
	public Optional<Role> findById(String id) {
		return roleRepository.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return roleRepository.existsById(id);
	}

	@Override
	public long count() {
		return roleRepository.count();
	}

	@Override
	public void deleteById(String id) {
		roleRepository.deleteById(id);
	}

}
