package com.at.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Room implements Serializable{

	private static final long serialVersionUID = -7112342700691944339L;
	
	@Id
	private Integer code;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Bookshelves> bookshelves = new ArrayList<Bookshelves>();

	public List<Bookshelves> getBookshelves() {
		return bookshelves;
	}

	public void setBookshelves(List<Bookshelves> bookshelves) {
		this.bookshelves = bookshelves;
	}

	public Integer getName() {
		return code;
	}

	public void setName(Integer code) {
		this.code = code;
	}

	
}
