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

import com.example.demo.model.serialize.CustomUserSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "permtypes")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PermType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "permname", unique = true)
	private String permname;

	@OneToMany(mappedBy = "permtype", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonSerialize(using = CustomUserSerializer.class)
	private List<User> user = new ArrayList<>();

	public PermType() {
		super();
	}

	public PermType(String permname) {
		super();
		this.permname = permname;
	}

	private PermType(Integer id, String permname, List<User> user) {
		super();
		this.id = id;
		this.permname = permname;
		this.user = user;
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

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public static class Builder {
		private Integer id;
		private String permname;
		private List<User> user = new ArrayList<>();

		public Builder withId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder withPermName(String permname) {
			this.permname = permname;
			return this;
		}

		public Builder withUsers(List<User> user) {
			this.user = user;
			return this;
		}

		public PermType createPerm() {
			return new PermType(id, permname, user);
		}

	}

	@Override
	public String toString() {
		return "PermType [id=" + id + ", permname=" + permname + "]";
	}

}
