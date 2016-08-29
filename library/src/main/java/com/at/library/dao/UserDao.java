package com.at.library.dao;

//import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	
	//@Query(value = "SELECT new com.at.library.dto.UserDTO() from User as u where u.id in (SELECT r.id from Rent as r.pk.book.id where r.endDate is null", nativeQuery = true)
	//List<User> find(String dni, String name);

}
