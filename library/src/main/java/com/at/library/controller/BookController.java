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

import com.at.library.dto.BookDTO;
import com.at.library.exceptions.NoBookException;
import com.at.library.service.book.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookservice;
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@RequestMapping(value = "all", method = { RequestMethod.GET })
	public List<BookDTO> getAll() {
		log.debug(String.format("Mostramos todos los libros."));
		return bookservice.findAll();
	}
	
	//TODO: *Obligatorios
	
	//TODO: *Crear
	@RequestMapping(method = { RequestMethod.POST })
	public BookDTO create(@RequestBody BookDTO book){
		log.debug(String.format("Vamo a crear el libro siguiente: %s", book));
		return bookservice.create(book);
	}
	
	//TODO: *Borrar
	@RequestMapping(method = {RequestMethod.DELETE})
	public void delete(@RequestBody BookDTO bDTO) throws NoBookException{
		log.debug(String.format("Eliminamos un libro con id: %s", bDTO.getId()));
		bookservice.delete(bDTO);
	}
	
	@RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
	public void delete(@PathVariable("id") Integer id) throws NoBookException{
		log.debug(String.format("Eliminamos un libro por su id: %s", id));
		bookservice.deleteById(id);
	}
	
	//TODO: *Buscar un libro por su titulo, autor e isbn
	@RequestMapping(method={RequestMethod.GET})
	public List<BookDTO> get(@RequestParam(value="title",required=false) String title, 
							 @RequestParam(value="author",required=false) String author,
							 @RequestParam(value="isbn",required=false) String isbn,
							 @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
							 @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) throws NoBookException{
		log.debug(String.format("Búsqueda de libro con titulo: %s, autor: %s, isbn: %s",title,author,isbn));
		return bookservice.findByParams(title,author,isbn,new PageRequest(page,size));
	}
	
	//TODO: *Modificar: Por DTO
	@RequestMapping(method = {RequestMethod.PUT})
	public BookDTO update(@RequestBody BookDTO bDTO) throws NoBookException{
		log.debug(String.format("Modificamo el libro."));
		return bookservice.update(bDTO);
	}
	
	//TODO: Recuperar uno
	@RequestMapping(value = "/{id}", method = {RequestMethod.GET})
	public BookDTO findOne(@PathVariable("id") Integer id) throws NoBookException {
		log.debug(String.format("Recuperamos un libro por id: %s", id));
		return bookservice.findById(id);
	}
	
}
