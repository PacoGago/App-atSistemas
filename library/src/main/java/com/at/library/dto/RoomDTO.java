package com.at.library.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.at.library.model.Bookshelves;

public class RoomDTO implements Serializable {


	private static final long serialVersionUID = 8900147020824277792L;
	
	private Integer code;
	
	private List<Bookshelves> bookshelves = new ArrayList<Bookshelves>();

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public List<Bookshelves> getBookshelves() {
		return bookshelves;
	}

	public void setBookshelves(List<Bookshelves> bookshelves) {
		this.bookshelves = bookshelves;
	}
	
}
