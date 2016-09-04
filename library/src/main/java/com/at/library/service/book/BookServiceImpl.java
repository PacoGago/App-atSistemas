package com.at.library.service.book;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.enums.StatusBook;
import com.at.library.exceptions.NoBookException;
import com.at.library.exceptions.NoRentException;
import com.at.library.model.Book;
import com.at.library.service.rent.RentService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
		b.setStatus(StatusBook.OK);
		final BookDTO bDTO = transform(bookDao.save(b));
		findGoogleApi(bDTO);
		return bDTO;
	}
	
	@Override
	public Book getById(Integer id) throws NoBookException {
		
		final Book b = bookDao.findOne(id);
		
		if (b==null){
			throw new NoBookException();
		}else{
			
			return b;
		}	
	}
	
	@Override
	public BookDTO findById(Integer id) throws NoBookException {
		
		final Book b = bookDao.findOne(id);
		
		if (b==null){
			throw new NoBookException();
		}else{
			BookDTO bDTO = transform(b);
			findGoogleApi(bDTO);
			return bDTO;
		}	
	}
	
	@Override
	public BookDTO update(BookDTO bDTO) throws NoBookException{
		
		Book b = bookDao.findOne(bDTO.getId());
		
		if (b==null){
			throw new NoBookException();
		}else{
			b = transform(bDTO);
			bookDao.save(b);
			findGoogleApi(bDTO);
			return bDTO;
		}
		
		//final Book b = transform(book);
		//bookDao.save(b);	
	}
	
	@Override
	public void delete(BookDTO bDTO) throws NoBookException {
		
		Book b = bookDao.findOne(bDTO.getId());
		if (b == null){
			
			throw new NoBookException();
			
		}else{bookDao.delete(bDTO.getId());}
	}
	
	@Override
	public void deleteById(Integer id) throws NoBookException {
		bookDao.delete(id);	
	}
	
	@Override
	public boolean available(Integer id){
		final boolean exist = bookDao.exists(id);
		if (exist){
			final Book b = bookDao.findOne(id);
			if (b.getStatus() == StatusBook.OK){
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
		if(b.getStatus() == StatusBook.OK){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void Status(Book b) {
		if (b.getStatus() == StatusBook.OK){
			b.setStatus(StatusBook.RENTED);
		}else{
			b.setStatus(StatusBook.OK);
		}
		bookDao.save(b);
	}

	@Override
	public List<BookDTO> findByParams(String title, String author, String isbn, Pageable pages) throws NoBookException{
		
		List<BookDTO> books;
		
		if (pages.getPageSize()>10){
			books = transform(bookDao.find(title, author, isbn, new PageRequest(pages.getPageNumber(),10)));
		}else{
			books = transform(bookDao.find(title, author, isbn, pages));
		}
		
		
		if(books.isEmpty()){
			
			throw new NoBookException();
			
		}else{
			
			Iterator<BookDTO> it = books.iterator();
			while(it.hasNext()){
				//Añadimos la información de Google
				findGoogleApi(it.next());
			}
			return books;
		}
		
		//List<BookDTO> books = bookDao.findByTAI(title, author, isbn);
		//return books;
	}
	
	@Override
	public List<RentDTO> getHistory(Integer bookId) throws NoBookException, NoRentException{
		
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
	
	public void findGoogleApi(BookDTO bDTO){
		
		RestTemplate rest = new RestTemplate();
		String URL = "https://www.googleapis.com/books/v1/volumes?startIndex=0&maxResults=1&q=";

		ObjectNode objNode = rest.getForObject(URL+bDTO.getTitle(), ObjectNode.class);
		JsonNode img = objNode.get("items").get(0).get("volumeInfo").get("imageLinks").get("thumbnail");
		JsonNode date = objNode.get("items").get(0).get("volumeInfo").get("publishedDate");
		JsonNode des = objNode.get("items").get(0).get("volumeInfo").get("description");
		
		//Campos a null 
		if (date != null){
			String text = date.asText();
			
			if(text.length()>4){
				
				text = text.substring(0, 4);
			}
			
			bDTO.setYear(Integer.parseInt(text));}
		
		if (des != null){
			bDTO.setDescription(des.asText());
		}
		
		if (img != null){
			bDTO.setImage(img.asText());
		}
	}
	

}
