package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.UserDTO;
import com.at.library.service.user.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userservice;
	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	
	@RequestMapping(value = "/user", method = { RequestMethod.GET })
	public List<UserDTO> getAll() {
		return userservice.findAll();
	}
	
	//TODO: Crear
	public UserDTO create(@RequestBody UserDTO user){
		log.debug(String.format("Vamo a crear el usuario siguiente: %s", user));
		return userservice.create(user);
	}

	//TODO: Recuperar uno

	//TODO: Modificar
	
	//TODO: Borrar


}
