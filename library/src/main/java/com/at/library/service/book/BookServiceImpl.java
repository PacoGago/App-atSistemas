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
import com.at.library.dto.RentDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.exceptions.NoBookException;
import com.at.library.model.Book;
import com.at.library.service.rent.RentService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private RentService rentservice;

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
	public List<BookDTO> transform(List<Book> books) {	
		
		final Iterator<Book> it = books.iterator();
		final List<BookDTO> booksDTO = new ArrayList<>();
		
		while (it.hasNext()) {
			final Book b = it.next();
			final BookDTO bDTO = transform(b);
			booksDTO.add(bDTO);
		}
		
		return booksDTO;
	}
	
	@Override
	public BookDTO create(BookDTO book){
		final Book b = transform(book);
		final Date d = new Date();
		b.setStartDate(d);
		return transform(bookDao.save(b));
	}
	
	@Override
	public BookDTO findById(Integer id) throws NoBookException {
		
		final Book b = bookDao.findOne(id);
		
		if (b==null){
			throw new NoBookException();
		}else{
			return transform(b);
		}	
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

	@Override
	public List<BookDTO> findByParams(String title, String author, String isbn) {
		
		List<BookDTO> books = transform(bookDao.find(title, author, isbn));
		return books;
		
		//List<BookDTO> books = bookDao.findByTAI(title, author, isbn);
		//return books;
	}
	
	@Override
	public List<RentDTO> getHistory(Integer bookId) throws NoBookException {
		
		//TODO: Capturar la excepción cuando se haga del noRent
		Book b = bookDao.findOne(bookId);
		
		if(b==null){
			throw new NoBookException ();
		}
		else{
			List<RentDTO> rDTOs = rentservice.getByBookId(bookId);
			return rDTOs;
		}
	}
	

}
