package com.at.library.service.rent;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.RentDTO;
import com.at.library.exceptions.NoBookException;
import com.at.library.exceptions.NoDTOException;
import com.at.library.exceptions.NoRentException;
import com.at.library.exceptions.NoUserException;
import com.at.library.exceptions.RentedBookException;
import com.at.library.exceptions.UnableUserException;
import com.at.library.model.Rent;

public interface RentService {
	
	/**
	 * Realiza la busqueda de todos los alquileres
	 * 
	 * @return listado de alquileres
	 */
	List<RentDTO> findAll(Pageable pages) throws NoRentException;
	
	/**
	 * Transfrma un alquiler en un alquilerDTO
	 * 
	 * @param rent
	 * @return
	 */
	RentDTO transform(Rent rent);

	/**
	 * Transforma un alquilerDTO en un alquiler
	 * 
	 * @param rent
	 * @return
	 */
	Rent transform(RentDTO rent) throws NoBookException;
	
	public List<RentDTO> transform(List<Rent> rents);
	
	/**
	 * Realizamos un alquiler.
	 * 
	 * @param book
	 * @return RentDTO
	 */
	RentDTO create(RentDTO rent) throws NoBookException, RentedBookException, NoUserException, UnableUserException, NoDTOException;
	
	/**
	 * Buscamos un alquiler por el id del libro
	 * 
	 * @param id
	 * @return Rent
	 */
	Rent findById(Integer id) ;
	
	/**
	 * Devolvemos el libro.
	 * 
	 * @param id
	 * @return
	 */
	void delete(Integer id) throws NoBookException, NoRentException;

	/**
	 * Devolvemos los alquileres atrasados.
	 * 
	 * @return List<Rent>
	 */
	List<Rent> findBehind();
	
	/**
	 * Historial de alquileres de un libro.
	 * 
	 * @return List<RentDTO>
	 */
	List<RentDTO> getByBookId(Integer bookId) throws NoRentException;



}
