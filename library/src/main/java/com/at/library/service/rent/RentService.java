package com.at.library.service.rent;

import java.util.List;

import com.at.library.dto.RentDTO;
import com.at.library.exceptions.NoBookException;
import com.at.library.model.Rent;

public interface RentService {
	
	/**
	 * Realiza la busqueda de todos los alquileres
	 * 
	 * @return listado de alquileres
	 */
	List<RentDTO> findAll();
	
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
	
	/**
	 * Realizamos un alquiler.
	 * 
	 * @param book
	 * @return RentDTO
	 */
	RentDTO create(RentDTO rent) throws NoBookException;
	
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
	void delete(Integer id) throws NoBookException;

	/**
	 * Devolvemos los alquileres atrasados.
	 * 
	 * @return List<Rent>
	 */
	List<Rent> findBehind();



}
