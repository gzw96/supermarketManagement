package com.project.supermarket.stock.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.user.entity.User;

@Entity
@Table(name="tranlog")
@JsonIgnoreProperties(value={"tranDetail"})
public class Tranlog {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade= {CascadeType.ALL})
	private Repo repoFrom;
	@ManyToOne(cascade= {CascadeType.ALL})
	private Repo repoTo;
	private int moveNum;
	@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date moveDate;
	@OneToMany(cascade= {CascadeType.ALL},mappedBy="tranlog")
	private Set<TranDetail> tranDetail = new HashSet<TranDetail>();//外键,一对多 
	
	public Long getId() {
		return id;
	}
	public Repo getRepoFrom() {
		return repoFrom;
	}
	public Repo getRepoTo() {
		return repoTo;
	}
	public int getMoveNum() {
		return moveNum;
	}
	public Date getMoveDate() {
		return moveDate;
	}
	public Set<TranDetail> getTranDetail() {
		return tranDetail;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setRepoFrom(Repo repoFrom) {
		this.repoFrom = repoFrom;
	}
	public void setRepoTo(Repo repoTo) {
		this.repoTo = repoTo;
	}
	public void setMoveNum(int moveNum) {
		this.moveNum = moveNum;
	}
	public void setMoveDate(Date moveDate) {
		this.moveDate = moveDate;
	}
	public void setTranDetail(Set<TranDetail> tranDetail) {
		this.tranDetail = tranDetail;
	}
	
	
	
	
}
