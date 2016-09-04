package com.at.library.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
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
	//@Query(value="SELECT rent from Rent as rent where rent.endDate < CURRENT_DATE")
	//public List<Rent> behind();
	
	//@Query(value="SELECT new com.at.library.dto.RentDTO(idLibro, idUser, idEmployee) from Rent as r where rent.rentpk.book.id = ?1", nativeQuery=false)
	//public List<RentDTO> findBookById(Integer id);
	
	@Query(value="SELECT rent from Rent as rent where rent.rentpk.book.id=?1")
	public List<Rent> findBookById(Integer id);
	
	@Query(value="SELECT r from Rent as r where r.user.id=?1")
	public List<Rent> findUserById(Integer id);
	
	@Query(value="SELECT * from Rent")
	public List<Rent> findAll(Pageable pages);
	

}
