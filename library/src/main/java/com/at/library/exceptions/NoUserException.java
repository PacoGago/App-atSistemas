package com.at.library.exceptions;

public class NoUserException extends Exception{

	private static final long serialVersionUID = -4264724038894464031L;
	
	private static final String msg = "User not found.";
	
	public NoUserException(){
		super(msg);
	}
}