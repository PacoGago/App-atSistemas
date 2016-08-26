package com.at.library.dto;

import java.io.Serializable;

public class RentDTO implements Serializable{
	
	private static final long serialVersionUID = -1665259051800304339L;

	private Integer idLibro;
	
	private Integer idUser;
	
	private Integer idEmployee;

	public Integer getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Integer idLibro) {
		this.idLibro = idLibro;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}
	
}
