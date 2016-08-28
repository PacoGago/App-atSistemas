package com.at.library.service.rent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.RentDao;
import com.at.library.dto.RentDTO;
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
	
	@Autowired
	private DozerBeanMapper dozer;

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
		return dozer.map(rent, RentDTO.class);
	}

	@Override
	public Rent transform(RentDTO rent) {
		return dozer.map(rent, Rent.class);
	}

	@Override
	public RentDTO create(RentDTO rent) {
		
		Book b = bookservice.transform(bookservice.findById(rent.getIdBook()));
		
		if (bookservice.getStatus(b)){
			
		
			bookservice.Status(b);
		
			Date d = new Date();
			Rent r = new Rent();
			RentPK pk = new RentPK();
			
			pk.setBook(b);
			pk.setStartDate(d);
			
			User u = userservice.transform(userservice.findById(rent.getIdUser()));
			Employee e = employeeservice.transform(employeeservice.findById(rent.getIdEmployee()));
			r.setUser(u);
			r.setEmployee(e);
			r.setRentpk(pk);
			
			
			Calendar c = Calendar.getInstance(); 
			c.setTime(d); 
			c.add(Calendar.DATE, 3);
			d = c.getTime();
			r.setEndDate(d);
			
			
			return transform(rentDao.save(r));
			
			
		}else{
			
			return new RentDTO();
		}
		
	}

	@Override
	public void delete(Integer id) {
		rentDao.delete(id);
		
	}

}
