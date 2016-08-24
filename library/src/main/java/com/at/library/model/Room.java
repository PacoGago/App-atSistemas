package com.at.library.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.OneToMany;

@Entity
public class Room implements Serializable{

	private static final long serialVersionUID = -7112342700691944339L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	//@OneToMany
	private List<Bookshelves> bookshelves;

	public List<Bookshelves> getBookshelves() {
		return bookshelves;
	}

	public void setBookshelves(List<Bookshelves> bookshelves) {
		this.bookshelves = bookshelves;
	}
	
}
