package com.at.library.exceptions;

public class UnableUserException extends Exception{

	private static final long serialVersionUID = -8609817281479781346L;
	
	private static final String msg = "Unable user.";
	
	public UnableUserException(){
		super(msg);
	}
}
