package com.at.library.exceptions;

public class RentedBookException extends Exception{


	private static final long serialVersionUID = -7700725198146775999L;
	
	private static final String msg = "Book is rented.";
	
	public RentedBookException(){
		super(msg);
	}
}
