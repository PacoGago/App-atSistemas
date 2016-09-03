package com.at.library.dto;

import java.io.Serializable;

public class RentDTO implements Serializable{


	private static final long serialVersionUID = 1462949654282073004L;

	private Integer book;
	
	private Integer user;

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
