package com.at.library.service.book;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public List<BookDTO> findAll() {
		final Iterable<Book> findAll = bookDao.findAll();
		final Iterator<Book> iterator = findAll.iterator();
		final List<BookDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			final BookDTO bDTO = transform(b);
			res.add(bDTO);
		}
		return res;
	}

	@Override
	public BookDTO transform(Book book) {
		return dozer.map(book, BookDTO.class);
	}

	@Override
	public Book transform(BookDTO book) {
		return dozer.map(book, Book.class);
	}
	
	@Override
	public BookDTO create(BookDTO book){
		final Book b = transform(book);
		final Date d = new Date();
		b.setStartDate(d);
		return transform(bookDao.save(b));
	}
	
	@Override
	public BookDTO findById(Integer id){
		final Book b = bookDao.findOne(id);
		return transform(b);
	}
	
	@Override
	public void update(BookDTO book){
		final Book b = transform(book);
		bookDao.save(b);	
	}
	
	@Override
	public void delete(Integer id){
		bookDao.delete(id);	
	}
	
	@Override
	public boolean available(Integer id){
		final boolean exist = bookDao.exists(id);
		if (exist){
			final Book b = bookDao.findOne(id);
			if (b.getStatus() == StatusEnum.ACTIVE){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		
		}
	}
	
	@Override
	public boolean getStatus(Book b){
		if(b.getStatus() == StatusEnum.ACTIVE){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void Status(Book b) {
		if (b.getStatus() == StatusEnum.ACTIVE){
			b.setStatus(StatusEnum.DISABLE);
		}else{
			b.setStatus(StatusEnum.ACTIVE);
		}
		bookDao.save(b);
	}

}
