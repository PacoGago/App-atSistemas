package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {
	
	//Hay que crear un constructor en BookDTO para hacer esot BookDTO(todos los campos) 
	@Query(value = "SELECT new com.at.library.dto.BookDTO() from Book as b where b.id in (SELECT r.id from Rent as r.pk.book.id where r.endDate is null", nativeQuery = true)
	public List<BookDTO> findUnAva();
	
}
	
