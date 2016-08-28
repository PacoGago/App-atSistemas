package com.at.library.dto;

import java.io.Serializable;

import com.at.library.enums.StatusEnum;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 3827455324138571715L;
	
	private Integer id;

	private String name;
	
	private String DNI;
	
	private StatusEnum status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
}
