package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="permtypes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PermType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "permname", unique=true)
	private String permname;
	
	@OneToMany(mappedBy = "permtype", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<User> user = new ArrayList<User>();
	
	public PermType() {
		super();
	}

	public PermType(String permname) {
		super();
		this.permname = permname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermname() {
		return permname;
	}

	public void setPermname(String permname) {
		this.permname = permname;
	}
	
	@JsonIgnore
	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "PermType [id=" + id + ", permname=" + permname + "]";
	}
	
	

}
