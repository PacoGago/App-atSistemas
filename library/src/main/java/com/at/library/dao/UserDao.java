package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

//import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import com.at.library.dto.UserDTO;
import com.at.library.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	
	//@Query(value = "SELECT new com.at.library.dto.UserDTO() from User as u where u.id in (SELECT r.id from Rent as r.pk.book.id where r.endDate is null", nativeQuery = true)
	//List<User> find(String dni, String name);
	
	/**
	 * Realiza una búsqueda de usuarios por dni y nombre
	 * 
	 * @return listado de usuarios
	 */
	@Query(value = "SELECT u from User as u where (u.dni like %:dni% OR :dni is null) AND (u.name like %:name% OR :name is null)")
	List<User> find(@Param(value="dni") String dni, @Param(value="name") String name);
	
	//@Query(value = "SELECT new com.at.library.dto.UserDTO() from User as u where (u.dni like %:dni% OR :dni is null) AND (u.name like %:name% OR :name is null)")
	//List<UserDTO> findByDniName(@Param(value="dni") String dni, @Param(value="name") String name);

}
