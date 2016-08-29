package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Rent;

@Repository
public interface RentDao extends CrudRepository<Rent, Integer>{

	/**
	 * Alquilieres atrasados. Si han pasado 3 dias.
	 * 
	 * @return List<Rent>
	 */
	@Query(value="SELECT rent from Rent as rent where rent.endDate < CURRENT_DATE")
	public List<Rent> behind();
	
}
