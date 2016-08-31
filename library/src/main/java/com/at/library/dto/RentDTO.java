package com.at.library.dto;

import java.io.Serializable;

public class RentDTO implements Serializable{
	
	private static final long serialVersionUID = -1665259051800304339L;

	private Integer book;
	
	private Integer user;
	
	public RentDTO(){
		super();
	}
	
	public RentDTO(Integer idLibro, Integer idUser, Integer idEmployee) {
		super();
		this.book = idLibro;
		this.user = idUser;
	}

	public Integer getIdBook() {
		return book;
	}

	public void setIdBook(Integer idLibro) {
		this.book = idLibro;
	}

	public Integer getIdUser() {
		return user;
	}

	public void setIdUser(Integer idUser) {
		this.user = idUser;
	}
	
}
