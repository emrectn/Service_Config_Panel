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

@Entity
@Table(name="costtypes")
public class CostType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "costname", unique=true)
	private String costname;
	
	@OneToMany(mappedBy = "costType", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Register> registers = new ArrayList<Register>();

	public CostType(Integer id, String costname) {
		super();
		this.id = id;
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
