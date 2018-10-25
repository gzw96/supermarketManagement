package com.project.supermarket.user.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.supermarket.user.entity.Role;


public interface RoleRepository extends PagingAndSortingRepository<Role,String>,JpaSpecificationExecutor<Role>{

}
