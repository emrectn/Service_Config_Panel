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

import com.example.demo.model.serialize.CustomRegisterSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name="statustypes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StatusType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "statusname", unique=true)
	private String statusname;

	@OneToMany(mappedBy = "statusType", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonSerialize(using = CustomRegisterSerializer.class)
	private List<Register> registers = new ArrayList<>();


	public StatusType() {
		super();
	}

	public StatusType(String statusname) {
		super();
		this.statusname = statusname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	

	public List<Register> getRegisters() {
		return registers;
	}

	public void setRegisters(List<Register> registers) {
		this.registers = registers;
	}

	@Override
	public String toString() {
		return "StatusType [id=" + id + ", statusname=" + statusname + "]";
	}
	
}
