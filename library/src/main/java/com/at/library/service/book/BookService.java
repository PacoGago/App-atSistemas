package com.at.library.service.book;

import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.exceptions.NoBookException;
import com.at.library.model.Book;

public interface BookService {

	/**
	 * Realiza la busqueda de todos los libros existentes
	 * 
	 * @return listado de libros
	 */
	List<BookDTO> findAll();

	/**
	 * Transfrma un libro en un libroDTO
	 * 
	 * @param book
	 * @return
	 */
	BookDTO transform(Book book);

	/**
	 * Transforma un libroDTO en un libro
	 * 
	 * @param book
	 * @return
	 */
	Book transform(BookDTO book);
	
	
	/**
	 * Transforma una lista de libros a libros DTO
	 * 
	 * @param List<Book> books
	 * @return List<BookDTO>
	 */
	List<BookDTO> transform(List<Book> books);
	
	/**
	 * Crea un libro.
	 * 
	 * @param book
	 * @return
	 */
	BookDTO create(BookDTO book);
	
	/**
	 * Buscamos por id.
	 * 
	 * @param id
	 * @return
	 */
	BookDTO findById(Integer id) throws NoBookException;
	
	/**
	 * Actualizamos el libro.
	 * 
	 * @param book
	 * @return
	 */
	void update(BookDTO book);
	
	/**
	 * Eliminamos el libro.
	 * 
	 * @param id
	 * @return
	 */
	void delete(Integer id);

	/**
	 * Eliminamos el libro.
	 * 
	 * @param id
	 * @return
	 */
	boolean available(Integer id);

	/**
	 * Mostramos el estado.
	 * 
	 * @param book
	 * @return estado
	 */
	boolean getStatus(Book b);
	
	/**
	 * Cambia el estado de un libro.
	 * 
	 * @param book
	 */
	void Status(Book b);
	
	/**
	 * Buscamos por titulo, autor e isbn.
	 * 
	 * @param dni, name
	 * @return List<UserDTO>
	 */
	List<BookDTO> findByParams(String title, String author, String isbn);

	


}
