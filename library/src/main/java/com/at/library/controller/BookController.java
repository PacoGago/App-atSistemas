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

import com.at.library.dto.BookDTO;
import com.at.library.exceptions.NoBookException;
import com.at.library.service.book.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookservice;
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@RequestMapping(method = { RequestMethod.GET })
	public List<BookDTO> getAll() {
		log.debug(String.format("Mostramos todos los libros."));
		return bookservice.findAll();
	}
	
	//TODO: Crear
	@RequestMapping(method = { RequestMethod.POST })
	public BookDTO create(@RequestBody BookDTO book){
		log.debug(String.format("Vamo a crear el libro siguiente: %s", book));
		return bookservice.create(book);
	}
	//TODO: Recuperar uno
	@RequestMapping(value = "/{id}", method = {RequestMethod.GET})
	public BookDTO findOne(@PathVariable("id") Integer id) throws NoBookException {
		log.debug(String.format("Recuperamos un libro por id: %s", id));
		return bookservice.findById(id);
	}
	
	//TODO: Modificar
	@RequestMapping(value = "/{id}", method = {RequestMethod.PUT})
	public void update(@RequestBody BookDTO book){
		log.debug(String.format("Modificamo el libro."));
		bookservice.update(book);
	}
	
	//TODO: Borrar
	@RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
	public void delete(@PathVariable("id") Integer id){
		log.debug(String.format("Eliminamos un libro por su id: %s", id));
		bookservice.delete(id);
	}
	
}
