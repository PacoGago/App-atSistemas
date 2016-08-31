package com.at.library.dao;

import java.io.Serializable;
import java.util.Date;

public class HistoryRentDTO implements Serializable {

	private static final long serialVersionUID = 8153660578879129487L;
	
	private Date init;
	private Date end;
	private String title;
	private Integer idBook;
	
	public Integer getIdBook() {
		return idBook;
	}
	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
	}

	public String getTitle() {
		return title;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}

	public Date getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}
	public Date getInit() {
		return init;
	}
	public void setInit(Date init) {
		this.init = init;
	}

}
