package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.UserDTO;
import com.at.library.exceptions.NoDTOException;
import com.at.library.exceptions.NoUserException;
import com.at.library.service.user.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	
	@ApiOperation(value = "Create a Library User.",
		    notes = "Only a user.",
		    response = UserDTO.class,
		    responseContainer = "User")
	@RequestMapping(method = { RequestMethod.POST })
	public UserDTO create(@RequestBody UserDTO user)throws NoDTOException{
		log.debug(String.format("Crearmos el usuario siguiente: %s", user));
		return userservice.create(user);
	}
		
	//TODO: Borrar por id
	@RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
	public void deleteById(@PathVariable("id") Integer id){
		log.debug(String.format("Eliminamos un usuario por su id: %s", id));
		userservice.deleteById(id);
	}
	
	//TODO: Borrar por DTO
	@RequestMapping(method = {RequestMethod.DELETE})
	public void delete(@RequestBody UserDTO user) throws NoUserException{
		log.debug(String.format("Eliminamos un usuario por su id: %s", user));
		userservice.delete(user);
	}
	
	//TODO: Búsqueda por DNI y por nombre
	@RequestMapping(method={RequestMethod.GET})
	public List<UserDTO> get(@RequestParam(value="dni",required=false) String dni, 
							 @RequestParam(value="name",required=false) String name,
							 @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			 				 @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) throws NoUserException{
		log.debug(String.format("Búsqueda de usuario con dni: %s, nombre: %s",dni,name));
		return userservice.findByParams(dni,name,new PageRequest(page,size));
	}
}
