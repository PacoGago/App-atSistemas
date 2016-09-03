package com.at.library.service.book;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.BookDTO;
import com.at.library.dto.RentDTO;
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
	 * @return BookDTO
	 */
	BookDTO update(BookDTO bDTO) throws NoBookException ;
	
	/**
	 * Eliminamos el libro.
	 * 
	 * @param id
	 */
	void deleteById(Integer id) throws NoBookException;
	
	/**
	 * Eliminamos el libro.
	 * 
	 * @param BookDTO
	 */
	void delete(BookDTO bDTO) throws NoBookException;

	/**
	 * Comprovamos si está disponible
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
	List<BookDTO> findByParams(String title, String author, String isbn, Pageable pages) throws NoBookException;

	/**
	 * Historial de alquileres de un libro.
	 * 
	 * @param bookId
	 * @return List<RentDTO>
	 */
	List<RentDTO> getHistory(Integer bookId) throws NoBookException;

	Book getById(Integer id) throws NoBookException;

	


}
