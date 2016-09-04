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

import com.at.library.dto.RentDTO;
import com.at.library.exceptions.NoBookException;
import com.at.library.exceptions.NoDTOException;
import com.at.library.exceptions.NoRentException;
import com.at.library.exceptions.NoUserException;
import com.at.library.exceptions.RentedBookException;
import com.at.library.exceptions.UnableUserException;
import com.at.library.service.rent.RentImportService;
import com.at.library.service.rent.RentService;

@RestController
@RequestMapping(value = "/rent")
public class RentController {
	
	@Autowired
	private RentService rentservice;
	
	@Autowired
	private RentImportService rentimportservice;
	
	private static final Logger log = LoggerFactory.getLogger(RentController.class);

	@RequestMapping(method = { RequestMethod.GET })
	public List<RentDTO> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			 					@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) throws NoRentException{
		return rentservice.findAll(new PageRequest(page,size));
	}
	
	//TODO: Alquiler
	@RequestMapping(method = { RequestMethod.POST })
	public RentDTO create(@RequestBody RentDTO rent) throws NoBookException, RentedBookException, NoUserException, UnableUserException, NoDTOException{
		log.debug(String.format("Vamos a crear el alquiler siguiente: %s", rent));
		return rentservice.create(rent);
	}
	
	//TODO: Devolver
	@RequestMapping(value = "/{idBook}", method = {RequestMethod.DELETE})
	public void delete(@PathVariable("idBook") Integer id) throws NoBookException, NoRentException{
		log.debug(String.format("Eliminamos un alquiler por el id del libro: %s", id));
		rentservice.delete(id);
	}
	
	//TODO: Informe de alquileres
	@RequestMapping(value = "/import", method = {RequestMethod.GET})
	public void imp(){
		log.debug(String.format("Informe de alquileres."));
		rentimportservice.imp();
	}
	
	//TODO: Alquileres de un libro
	@RequestMapping(value="/{id}",method={RequestMethod.GET})
	public List<RentDTO> get(@PathVariable("id") Integer id) throws NoBookException, NoRentException{
		log.debug(String.format("Historial de un alquiler por su id: %s", id));
		return rentservice.getByBookId(id);
		//return null;
	}

}
