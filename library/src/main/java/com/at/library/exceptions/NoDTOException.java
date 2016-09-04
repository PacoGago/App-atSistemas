package com.at.library.exceptions;

public class NoDTOException extends Exception{

	private static final long serialVersionUID = 8067907704740227714L;
	
	private static final String msg = "DTO is null.";
	
	public NoDTOException(){
		super(msg);
	}
}
