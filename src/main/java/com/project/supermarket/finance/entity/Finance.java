package com.project.supermarket.finance.entity;

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
import com.project.supermarket.user.entity.User;

@Entity
@Table(name="finance")
public class Finance {
	private Long id;
	private double expense;
	private Date expenseTime;
	private User expenseUser;
	private Date noteTime;
	private String remark;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@Column(nullable=false)
	public double getExpense() {
		return expense;
	}
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
	@Column(nullable=false)
	public Date getExpenseTime() {
		return expenseTime;
	}
	
	@ManyToOne
	@JoinColumn(name="expenseUser")
	public User getExpenseUser() {
		return expenseUser;
	}
	
	@Column(nullable=false)
	public Date getNoteTime() {
		return noteTime;
	}
	public String getRemark() {
		return remark;
	}
	
	//setter
	public void setId(Long id) {
		this.id = id;
	}
	public void setExpense(double expense) {
		this.expense = expense;
	}
	public void setExpenseTime(Date expenseTime) {
		this.expenseTime = expenseTime;
	}
	public void setExpenseUser(User expenseUser) {
		this.expenseUser = expenseUser;
	}
	public void setNoteTime(Date noteTime) {
		this.noteTime = noteTime;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
