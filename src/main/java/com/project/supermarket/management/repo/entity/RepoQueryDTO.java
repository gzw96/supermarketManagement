package com.project.supermarket.management.repo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.supermarket.management.repo.service.RepoServiceImpl;



public class RepoQueryDTO {
	@Autowired
	private static RepoServiceImpl repoService;
	//显示的信息
	private String repoName;
	private Date buildDate;
	private String address;
	private String repoPhone;
	private Long maxSize;
	private Long minSize;
	private String status;
	private String type;
	
	private Long roleId;
	private Long workNum;
	private String userRealName;
	
	public  void dtoToEntity(RepoQueryDTO dto , Repo repo) {
		
//		Long num = dto.getWorkNum();
//		String uName = dto.getUserRealName();
//		Long uId;
		//获取id,设置id后，再转换为entity(dto里没有id属性)
		/*SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        String hql = "select u.id from User u where u.userRealName like ? and u.workNum like ?";//查找出id
        Query query = s.createQuery(hql);
        query.setParameter(0, uName);
        query.setParameter(1, num);
        List<Long> list = query.list();
        uId=list.get(0);*/
//		uId = repoService.getRepoUserId(uName,num);
//        System.out.println(uId);
//        dto.setRoleId(uId);
        /*s.getTransaction().commit();
        s.close();
        sf.close();*/
        
		repo.setAddress(dto.getAddress());
		repo.setBuildDate(dto.getBuildDate());
		repo.setMaxSize(dto.getMaxSize());
		repo.setMinSize(dto.getMinSize());
		repo.setRepoName(dto.getRepoName());
		repo.setRepoPhone(dto.getRepoPhone());
		repo.setStatus(dto.getStatus());
		repo.setType(dto.getType());
		
	}
	public static void entityToDto(Repo repo , RepoQueryDTO dto) {
		BeanUtils.copyProperties(repo, dto);
//		Company company = repo.getCompany();
//		if(company!=null) {
//			dto.setCompanyId(company.getId());//把关系传递到View
//			dto.setCompanyName(company.getCompanyName());
//		}
	}
	
	
	//把dto的id值赋值
	public RepoQueryDTO init(RepoQueryDTO dto) {
		Long num = dto.getWorkNum();
		String uName = dto.getUserRealName();
		Long uId;
		
		/*SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        String hql = "select u.id from User u where u.userRealName like ? and u.workNum like ?";
        Query query = s.createQuery(hql);
        query.setParameter(0, uName);
        query.setParameter(1, num);
        List<Long> list = query.list();
        uId=list.get(0);*/
		uId = repoService.getRepoUserId(uName, num);
        System.out.println(uId);
        dto.setRoleId(uId);
        /*s.getTransaction().commit();
        s.close();
        sf.close();*/
        
		return dto;
	}
	
	public String getRepoName() {
		return repoName;
	}
	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}
	
	@JsonFormat(pattern="yyyy/MM/dd",timezone="GMT+8")
	public Date getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRepoPhone() {
		return repoPhone;
	}
	public void setRepoPhone(String repoPhone) {
		this.repoPhone = repoPhone;
	}
	public Long getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(Long maxSize) {
		this.maxSize = maxSize;
	}
	public Long getMinSize() {
		return minSize;
	}
	public void setMinSize(Long minSize) {
		this.minSize = minSize;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getWorkNum() {
		return workNum;
	}
	public void setWorkNum(Long workNum) {
		this.workNum = workNum;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	
	@SuppressWarnings({"serial"})
	public static Specification<RepoQueryDTO> getWhereClause(final RepoQueryDTO repoQueryDTO){
		return new Specification<RepoQueryDTO>() {
			public Predicate toPredicate(Root<RepoQueryDTO> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				System.out.println(repoQueryDTO.getUserRealName());
				if (StringUtils.isNotBlank(repoQueryDTO.getRepoName())) {
					predicate.add(criteriaBuilder.like(root.get("repoName").as(String.class),
							"%" + repoQueryDTO.getRepoName() + "%"));
				}
				
				if (StringUtils.isNotBlank(repoQueryDTO.getStatus())) {
					predicate.add(criteriaBuilder.like(root.get("status").as(String.class),
							 repoQueryDTO.getStatus() + "%"));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
