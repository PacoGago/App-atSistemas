package com.at.library.exceptions;

public class NoRentException extends Exception{


	private static final long serialVersionUID = 6155548857648539501L;
	
	private static final String msg = "Rent not found.";
	
	public NoRentException(){
		super(msg);
	}
}