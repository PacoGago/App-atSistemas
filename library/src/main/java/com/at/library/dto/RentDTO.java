package com.at.library.dto;

import java.io.Serializable;

public class RentDTO implements Serializable{


	private static final long serialVersionUID = 8809918185684486443L;

	private Integer book;
	
	private Integer user;

	public Integer getIdBook() {
		return book;
	}

	public void setIdBook(Integer book) {
		this.book = book;
	}

	public Integer getIdUser() {
		return user;
	}

	public void setIdUser(Integer user) {
		this.user = user;
	}
}
