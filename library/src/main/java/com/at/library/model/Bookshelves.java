package com.at.library.model;

import java.io.Serializable;
import java.util.List;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//import org.hibernate.annotations.ManyToAny;


@Entity
public class Bookshelves implements Serializable{

	private static final long serialVersionUID = 6022960347036022772L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private List<Book> books;
	
	//@ManyToAny(metaColumn = @Column)
	private Room room;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
