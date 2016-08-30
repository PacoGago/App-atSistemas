package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {
	
	@Query(value = "SELECT b from Book as b where (b.title like %:title% OR :title is null) AND (b.author like %:author% OR :author is null) AND (b.isbn like %:isbn% OR :isbn is null)")
	List<Book> find(@Param(value="title") String title, @Param(value="author") String author, @Param(value="isbn") String isbn);
	
}
	
