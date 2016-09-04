package com.at.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.at.library.dto.ExceptionDTO;
import com.at.library.exceptions.NoBookException;
import com.at.library.exceptions.NoDTOException;
import com.at.library.exceptions.NoRentException;
import com.at.library.exceptions.NoUserException;
import com.at.library.exceptions.RentedBookException;
import com.at.library.exceptions.UnableUserException;


@ControllerAdvice(basePackages= {"com.at.library.controller"})
public class  ExceptionController {

	@ResponseBody
	@ExceptionHandler(NoBookException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionDTO error(NoBookException e){
		return new ExceptionDTO(404,e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(NoUserException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionDTO error(NoUserException e){
		return new ExceptionDTO(404,e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(NoRentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionDTO error(NoRentException e){
		return new ExceptionDTO(404,e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(NoDTOException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDTO error(NoDTOException e){
		return new ExceptionDTO(400,e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(UnableUserException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDTO error(UnableUserException e){
		return new ExceptionDTO(400,e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(RentedBookException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ExceptionDTO error(RentedBookException e){
		return new ExceptionDTO(500,e.getMessage());
	}
	
}
