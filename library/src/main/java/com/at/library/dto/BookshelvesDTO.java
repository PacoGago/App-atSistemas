package com.at.library.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.at.library.model.Book;

public class BookshelvesDTO implements Serializable{

	private static final long serialVersionUID = 4037476675583213795L;
	
	private String code;
	
	private List<Book> books = new ArrayList<Book>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
