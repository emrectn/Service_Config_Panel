package com.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_service")
 public class ServiceConfig {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "value")
	private String value;

	@Column(name = "isActive")
	private boolean isActive;

	@Column(name = "applicationName")
	private String applicationName;
	
	public ServiceConfig() {
		super();
	}

	public ServiceConfig(String name, String type, String value, boolean isActive, String applicationName) {
		super();
		this.id = -1;
		this.name = name;
		this.type = type;
		this.value = value;
		this.isActive = isActive;
		this.applicationName = applicationName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean getisIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	@Override
	public String toString() {
		return String.format("ServiceConfig [id=%5d | name=%5s | type=%7s | value=%5s ]", id,name,type,value);
	}
	
	
}
