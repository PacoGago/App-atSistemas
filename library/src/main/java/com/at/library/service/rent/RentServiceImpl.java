package com.at.library.service.rent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.RentDao;
import com.at.library.dto.RentDTO;
import com.at.library.exceptions.NoBookException;
import com.at.library.model.Book;
import com.at.library.model.Employee;
import com.at.library.model.Rent;
import com.at.library.model.RentPK;
import com.at.library.model.User;
import com.at.library.service.book.BookService;
import com.at.library.service.employee.EmployeeService;
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
	private EmployeeService employeeservice;
	
	

	@Override
	public List<RentDTO> findAll() {
		final Iterable<Rent> findAll = rentDao.findAll();
		final Iterator<Rent> iterator = findAll.iterator();
		final List<RentDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Rent r = iterator.next();
			final RentDTO rDTO = transform(r);
			res.add(rDTO);
		}
		return res;
	}

	
	@Override
	public RentDTO transform(Rent rent) {
		
		final RentDTO rentdto = new RentDTO();
		rentdto.setIdEmployee(rent.getEmployee().getId());
		rentdto.setIdBook(rent.getRentpk().getBook().getId());
		rentdto.setIdUser(rent.getUser().getId());
		
		return rentdto;
	}
	
	@Override
	public Rent transform(RentDTO rent) throws NoBookException{
		
		final Rent r = new Rent();
		Book b = bookservice.transform(bookservice.findById(rent.getIdBook()));
		User u = userservice.transform(userservice.findById(rent.getIdUser()));
		Employee e = employeeservice.transform(employeeservice.findById(rent.getIdEmployee()));
		
		RentPK rentpk = new RentPK();
		rentpk.setBook(b);
		
		r.setRentpk(rentpk);
		r.setEmployee(e);
		r.setUser(u);
		
		return r;
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
	public RentDTO create(RentDTO rent) throws NoBookException{
		
		final Rent r = transform(rent);
		Book b = bookservice.transform(bookservice.findById(rent.getIdBook()));
		
		if (bookservice.getStatus(b)){
			
			Date d = new Date();
			
			bookservice.Status(b);
		
			
			Calendar cal = Calendar.getInstance();
			r.getRentpk().setStartDate(cal.getTime());
			cal.setTime(d);
			cal.add(Calendar.DATE, 3);
			d = cal.getTime();
			
			r.setEndDate(d);
			
			return transform(rentDao.save(r));
			
			
		}else{
			
			return null;
		}
		
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
	public List<Rent> findBehind() {
		return rentDao.behind();
	}

	@Override
	public void delete(Integer id) throws NoBookException{
		
		Rent r = findById(id);
		
		if(r != null){
			
			r.setEndDate(Calendar.getInstance().getTime());
			Book b = bookservice.transform(bookservice.findById(id));
			bookservice.Status(b);
			rentDao.save(r);
			
		}
		
	}


	@Override
	public List<RentDTO> findByParams(Integer id) {
		
		List<RentDTO> rents = transform(rentDao.findBookById(id));
		return rents;
	}

}
