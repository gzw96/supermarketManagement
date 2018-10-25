package com.project.supermarket.user.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class UserQueryDTO {
	
	private String id;
	private String userRealName;
	private Long phone;
	private Long workNum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public Long getWorkNum() {
		return workNum;
	}
	public void setWorkNum(Long workNum) {
		this.workNum = workNum;
	}
	
	@SuppressWarnings({ "serial"})
	public static Specification<User> getWhereClause(final UserQueryDTO userQueryDTO) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(userQueryDTO.getUserRealName())) {
					predicate.add(criteriaBuilder.like(root.get("userRealName").as(String.class),
							"%" + userQueryDTO.getUserRealName() + "%"));
				}
				if (null!=userQueryDTO.getWorkNum()) {
					predicate.add(criteriaBuilder.like(root.get("workNum").as(String.class),
							"%" +userQueryDTO.getWorkNum()+ "%"));
				}
				if (null!=userQueryDTO.getPhone()) {
					predicate.add(criteriaBuilder.like(root.get("phone").as(String.class),
							"%" +userQueryDTO.getPhone()+ "%"));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	
	@SuppressWarnings({ "serial"})
	public static Specification<UserDTO> getWhereClauseDTO(final UserQueryDTO userQueryDTO) {
		return new Specification<UserDTO>() {
			@Override
			public Predicate toPredicate(Root<UserDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(userQueryDTO.getUserRealName())) {
					predicate.add(criteriaBuilder.like(root.get("userRealName").as(String.class),
							"%" + userQueryDTO.getUserRealName() + "%"));
				}
				if (null!=userQueryDTO.getWorkNum()) {
					predicate.add(criteriaBuilder.like(root.get("workNum").as(String.class),
							"%" +userQueryDTO.getWorkNum()+ "%"));
				}
				if (null!=userQueryDTO.getPhone()) {
					predicate.add(criteriaBuilder.like(root.get("phone").as(String.class),
							"%" +userQueryDTO.getPhone()+ "%"));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
