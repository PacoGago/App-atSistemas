package com.at.library.service.rent;

import java.util.ArrayList;
import java.util.Date;
import org.joda.time.DateTime;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.at.library.dao.RentDao;
import com.at.library.dto.RentDTO;
import com.at.library.dto.enums.StatusBook;
import com.at.library.dto.enums.StatusRent;
import com.at.library.enums.UserEnum;
import com.at.library.exceptions.NoBookException;
import com.at.library.exceptions.NoDTOException;
import com.at.library.exceptions.NoRentException;
import com.at.library.exceptions.NoUserException;
import com.at.library.exceptions.RentedBookException;
import com.at.library.exceptions.UnableUserException;
import com.at.library.model.Book;
//import com.at.library.model.Employee;
import com.at.library.model.Rent;
import com.at.library.model.RentPK;
import com.at.library.model.User;
import com.at.library.service.book.BookService;
//import com.at.library.service.employee.EmployeeService;
import com.at.library.service.user.UserService;

@Service
public class RentServiceImpl implements RentService{
	
	@Autowired
	private RentDao rentDao;

	@Autowired
	private BookService bookservice;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	//@Autowired
	//private EmployeeService employeeservice;
	
	@Override
	public List<RentDTO> findAll(Pageable pages) throws NoRentException{
		
		List<RentDTO> rents;
		
		if(pages.getPageSize()>10){
			
			rents = transform(rentDao.findAll(new PageRequest(pages.getPageNumber(),10)));
			
		}else{
			rents = transform(rentDao.findAll(pages));
		}
		
		if(rents.isEmpty()){
			
			throw new NoRentException();
			
		}else{
			
			return rents;
			
		}
	}

	
	@Override
	public RentDTO transform(Rent rent) {
		
		return dozer.map(rent, RentDTO.class);

	}
	
	@Override
	public Rent transform(RentDTO rent) throws NoBookException{
		
		return dozer.map(rent, Rent.class);	
	}
	
	@Override
	public List<RentDTO> transform(List<Rent> rents) {	
		
		final Iterator<Rent> it = rents.iterator();
		final List<RentDTO> rentsDTO = new ArrayList<>();
		
		while (it.hasNext()) {
			final Rent r = it.next();
			final RentDTO rDTO = transform(r);
			rentsDTO.add(rDTO);
		}
		
		return rentsDTO;
	}
	
	@Override
	public RentDTO create(RentDTO rent) throws NoBookException, RentedBookException, NoUserException, UnableUserException, NoDTOException{
		
		if(rent!=null){
			
			Book b = bookservice.getById(rent.getIdBook());
			
			if (b != null){
					
				if (bookservice.getStatus(b)){
					
					final User user = userservice.transform(userservice.findById(rent.getIdUser()));
						
					if (user != null){
						
						if(user.getStatus() == UserEnum.ABLE){
							
							RentPK rentPK = new RentPK();
							rentPK.setBook(b);
							Date d = new Date();
							rentPK.setStartDate(d);
							
							Rent r = new Rent();
							r.setRentpk(rentPK);
							r.setUser(user);
							DateTime date = new DateTime(d);
							r.setEndDate(date.plusDays(7).toDate());
							rentDao.save(r);
							
							b.setStatus(StatusBook.RENTED);
							bookservice.update(bookservice.transform(b));
							
							RentDTO rDTO = new RentDTO();
							rDTO.setIdBook(r.getRentpk().getBook().getId());
							rDTO.setIdUser(r.getUser().getId());
							
							return rDTO;
							
						}else{throw new UnableUserException();}
				
					}else{throw new NoUserException();}
						
				}else{throw new RentedBookException();}
					
			}else{throw new NoBookException();}
			
		}else{throw new NoDTOException();}	
	}
		
	@Override
	public Rent findById(Integer id) {
		
		final Iterable<Rent> findAll = rentDao.findAll();
		final Iterator<Rent> iterator = findAll.iterator();
		
		while (iterator.hasNext()) {
			final Rent r = iterator.next();
			if(r.getRentpk().getBook().getId() == id && r.getEndDate() == null){
				return r;
			}			
		}
		/*Es mejor devolver null que devolver un objeto vacio */
		return null;
	}
	
	@Override
	public List<RentDTO> getByBookId(Integer bookId){
		
		final List<RentDTO> rDTOs = transform(rentDao.findBookById(bookId));
		
		if(!rDTOs.isEmpty()){
			
			return rDTOs;
			
		}else{
			
			//TODO: Lanzar excepción no hay alquileres de ese libro
			return null;
		}
	}
	
	@Override
	public List<Rent> findBehind() {
		return rentDao.behind();
	}

	@Override
	public void delete(Integer id) throws NoBookException{
		
		Rent r = findById(id);
		
		if(r != null){
			
			Book b = bookservice.transform(bookservice.findById(id));
			bookservice.Status(b);
			r.setStatus(StatusRent.COMPLETED);
			rentDao.save(r);
			
		}
	}	
}
