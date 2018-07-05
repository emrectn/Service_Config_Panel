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
@Table(name="costtypes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CostType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "costname", unique=true)
	private String costname;
	
	@OneToMany(mappedBy = "costType", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonSerialize(using = CustomRegisterSerializer.class)
	private List<Register> registers = new ArrayList<>();

	public CostType(){}
	
	public CostType(String costname, List<Register> registers) {
		super();
		this.costname = costname;
		this.registers = registers;
	}
	
	public CostType(String costname) {
		super();
		this.costname = costname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCostname() {
		return costname;
	}

	public void setCostname(String costname) {
		this.costname = costname;
	}

	public List<Register> getRegisters() {
		return registers;
	}

	public void setRegisters(List<Register> registers) {
		this.registers = registers;
	}

	@Override
	public String toString() {
		return "CostType [id=" + id + ", costname=" + costname + "]";
	}
	
	

}
