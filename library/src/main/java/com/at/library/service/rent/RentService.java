package com.at.library.service.rent;

import java.util.List;

import com.at.library.dto.RentDTO;
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
	Rent transform(RentDTO rent);
	
	/**
	 * Realizamos un alquiler.
	 * 
	 * @param book
	 * @return RentDTO
	 */
	RentDTO create(RentDTO rent);
	
	/**
	 * Devolvemos el libro.
	 * 
	 * @param id
	 * @return
	 */
	void delete(Integer id);

}
