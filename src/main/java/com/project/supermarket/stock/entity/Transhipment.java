package com.project.supermarket.stock.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.supermarket.management.repo.entity.Repo;
import com.project.supermarket.user.entity.User;

@Entity
@Table(name="transhipment")
public class Transhipment {
	private Long id;
	private Long tranId;
	private Repo repoFrom;
	private Repo repoTo;
	private Long moveNum;
	private Date moveDate;
	private User tranPeople;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@Column(nullable=false)
	public Long getTranId() {
		return tranId;
	}
	
	@ManyToOne
	@JoinColumn(name="repoFrom")
	public Repo getRepoFrom() {
		return repoFrom;
	}
	
	@ManyToOne
	@JoinColumn(name="repoTo")
	public Repo getRepoTo() {
		return repoTo;
	}
	
	@Column(nullable=false)
	public Long getMoveNum() {
		return moveNum;
	}
	
	@Column(nullable=false)
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
	public Date getMoveDate() {
		return moveDate;
	}
	
	@ManyToOne
	@JoinColumn(name="tranPeopleId")
	public User getTranPeople() {
		return tranPeople;
	}
	
	//setter
	public void setId(Long id) {
		this.id = id;
	}
	public void setTranId(Long tranId) {
		this.tranId = tranId;
	}
	public void setRepoFrom(Repo repoFrom) {
		this.repoFrom = repoFrom;
	}
	public void setRepoTo(Repo repoTo) {
		this.repoTo = repoTo;
	}
	public void setMoveNum(Long moveNum) {
		this.moveNum = moveNum;
	}
	public void setMoveDate(Date moveDate) {
		this.moveDate = moveDate;
	}
	public void setTranPeople(User tranPeople) {
		this.tranPeople = tranPeople;
	}
	
	
}
