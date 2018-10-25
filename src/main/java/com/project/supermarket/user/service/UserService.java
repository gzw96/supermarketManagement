package com.project.supermarket.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.supermarket.user.entity.Role;
import com.project.supermarket.user.entity.User;
import com.project.supermarket.user.entity.UserDTO;
import com.project.supermarket.user.repository.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public User save(User entity) {
		return userRepository.save(entity);
	}
	
	@Override
	public void  delete(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return userRepository.existsById(id);
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public List<User> findAll(){
		return (List<User>) userRepository.findAll();
	}
	
	@Override 
	public Page<UserDTO> findAll(Specification<User> spec, Pageable pageable) {
		List<User> listuser = userRepository.findAll(spec);
		List<UserDTO> listuserdto = new ArrayList<UserDTO>();
		for(User u : listuser){
			UserDTO dto = new UserDTO();
			BeanUtils.copyProperties(u,dto);
			Set<Role> role = u.getRole();
			for(Role r:role)
			{
				dto.setGroupName((r.getRoleName()).toString());
			}
			listuserdto.add(dto);
		}
		return new PageImpl<UserDTO> (listuserdto, pageable, null!=listuserdto?listuserdto.size():0);
	}


	@Override
	public void deleteAll(String[] ids) {
		List<String> idLists = new ArrayList<String>( Arrays.asList(ids));
		List<User> orders = (List<User>) userRepository.findAllById(idLists);
		if(orders!=null) {
			System.out.println("222");
			userRepository.deleteAll(orders);
		}
	}
	
	@Override
	public void updateUserPassword(String userId,String newPassword) {
		userRepository.updateUserPassword(newPassword, userId);
	}

	@Override
	public String  getUserIdByUserName(String userName) {
		return  userRepository.getUserIdByUserName(userName);
	}

	@Override
	public String getPassword(String id) {
		return userRepository.getPassword(id);
	}

}
