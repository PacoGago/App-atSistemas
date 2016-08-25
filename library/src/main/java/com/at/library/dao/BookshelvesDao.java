package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Bookshelves;

@Repository
public interface BookshelvesDao extends CrudRepository<Bookshelves, Integer>{

	
}
