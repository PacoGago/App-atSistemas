package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.RentDTO;
import com.at.library.service.rent.RentService;

@RestController
@RequestMapping(value = "/rent")
public class RentController {
	
	@Autowired
	private RentService rentservice;
	private static final Logger log = LoggerFactory.getLogger(RentController.class);

	@RequestMapping(method = { RequestMethod.GET })
	public List<RentDTO> getAll() {
		return rentservice.findAll();
	}
	
	//TODO: Alquiler
	@RequestMapping(method = { RequestMethod.POST })
	public RentDTO create(@RequestBody RentDTO rent){
		log.debug(String.format("Vamos a crear el alquiler siguiente: %s", rent));
		return rentservice.create(rent);
	}
	
	//TODO: Devolver
	@RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
	public void delete(@PathVariable("id") Integer id){
		log.debug(String.format("Eliminamos un alquio por su id: %s", id));
		rentservice.delete(id);
	}

}