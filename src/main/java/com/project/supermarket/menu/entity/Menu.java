package com.project.supermarket.menu.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="menu")

public class Menu {
	private Long id;
	private String name;
	private Menu parentNode;
	private List<Menu> childNodes = new ArrayList<Menu>();
	private String icon;
	private String view;
	
	@Id
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Menu getParentNode() {
		return parentNode;
	}
	
	@OneToMany(mappedBy="parentNode" ,cascade=CascadeType.ALL)
	public List<Menu> getChildNodes() {
		return childNodes;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setParentNode(Menu parentNode) {
		this.parentNode = parentNode;
	}
	public void setChildNodes(List<Menu> childNodes) {
		this.childNodes = childNodes;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
}
