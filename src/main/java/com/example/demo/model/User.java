package com.example.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "email", unique=true)
	private String email;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "surname")
	private String surname;

	@Column(name = "userlog")
	private BigDecimal userlog;

	@Column(name = "userrole")
	private BigDecimal userrole;
	
	@OneToMany(mappedBy = "creater", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Register> createrRegisters = new ArrayList<Register>();

	@OneToMany(mappedBy = "updater", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Register> updaterRegisters = new ArrayList<Register>();

	// bi-directional many-to-one association to Permtype
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "perm_type_id")
	private PermType permtype;

	// bi-directional many-to-one association to Userteam
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "team_id")
	private UserTeam userteam;

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public BigDecimal getUserlog() {
		return userlog;
	}

	public void setUserlog(BigDecimal userlog) {
		this.userlog = userlog;
	}

	public BigDecimal getUserrole() {
		return userrole;
	}

	public void setUserrole(BigDecimal userrole) {
		this.userrole = userrole;
	}

	public PermType getPermtype() {
		return permtype;
	}

	public void setPermtype(PermType permtype) {
		this.permtype = permtype;
	}

	public UserTeam getUserteam() {
		return userteam;
	}

	public void setUserteam(UserTeam userteam) {
		this.userteam = userteam;
	}
	
	public List<Register> getCreaterRegisters() {
		return createrRegisters;
	}

	public void setCreaterRegisters(List<Register> createrRegisters) {
		this.createrRegisters = createrRegisters;
	}

	public List<Register> getUpdaterRegisters() {
		return updaterRegisters;
	}

	public void setUpdaterRegisters(List<Register> updaterRegisters) {
		this.updaterRegisters = updaterRegisters;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", surname="
				+ surname + ", userlog=" + userlog + ", userrole=" + userrole + ", permtype=" + permtype + ", userteam="
				+ userteam + "]";
	}
	
	
	

}
