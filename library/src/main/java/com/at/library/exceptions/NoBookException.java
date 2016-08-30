package com.at.library.exceptions;

public class NoBookException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7690605272364684447L;
	
	private static final String msg = "Book not found.";
	
	public NoBookException(){
		super(msg);
	}

}
