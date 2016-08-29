package com.at.library.dto;

import java.io.Serializable;

import com.at.library.enums.StatusEnum;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = -5483268118618932343L;

	private Integer id;
	
	private String dni;

	private String name;
	
	private StatusEnum status;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
}
