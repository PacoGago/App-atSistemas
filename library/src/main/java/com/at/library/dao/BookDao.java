package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {
	
	/* Duda cual de las dos Query es mejor?
	 * 
	 * Si es mejor con el dto, que es la que explicó en clase =>
	 * modificar la de user y borrar esta.
	 * 
	 * 
	 * */
	@Query(value = "SELECT b from Book as b where (b.title like %:title% OR :title is null) AND (b.author like %:author% OR :author is null) AND (b.isbn like %:isbn% OR :isbn is null)")
	List<Book> find(@Param(value="title") String title, @Param(value="author") String author, @Param(value="isbn") String isbn);
	
	
	@Query(value = "SELECT new com.at.library.dto.BookDTO(id, isbn, title, author) from Book as b where (b.title like %:title% OR :title is null) AND (b.author like %:author% OR :author is null) AND (b.isbn like %:isbn% OR :isbn is null)", nativeQuery=false)
	List<BookDTO> findByTAI(@Param(value="title") String title, @Param(value="author") String author, @Param(value="isbn") String isbn);
}
	
