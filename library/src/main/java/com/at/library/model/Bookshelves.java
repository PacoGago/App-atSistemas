package com.at.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Bookshelves implements Serializable{

	private static final long serialVersionUID = 6022960347036022772L;

	@Id
	private String code;
	
	@OneToMany
	private List<Book> books = new ArrayList<Book>();
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getName() {
		return code;
	}

	public void setName(String code) {
		this.code = code;
	}
	
}
