package com.at.library.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.at.library.dao.UserDao;
import com.at.library.dto.UserDTO;
import com.at.library.enums.UserEnum;
import com.at.library.exceptions.NoDTOException;
import com.at.library.exceptions.NoUserException;
import com.at.library.model.Rent;
import com.at.library.model.User;
import com.at.library.service.rent.RentService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RentService rentservice;

	@Autowired
	private DozerBeanMapper dozer;
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);


	@Override
	public List<UserDTO> findAll() {
		final Iterable<User> findAll = userDao.findAll();
		final Iterator<User> iterator = findAll.iterator();
		final List<UserDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final User u = iterator.next();
			final UserDTO uDTO = transform(u);
			res.add(uDTO);
		}
		return res;
	}
	
	@Override
	public void setStatus(User user, UserEnum stat) throws NoUserException {
		
		if(user==null){throw new NoUserException();}
		else{
			user.setStatus(stat);
			userDao.save(user);
		}
	}
	
	@Override
	@Scheduled(cron = "0 0 * * * *")
	public void penalize() throws NoUserException{
		
		final Iterable<Rent> ir = rentservice.findBehind();
		final Iterator<Rent> it = ir.iterator();
		
		DateTime d = new DateTime(new Date());
		
		while(it.hasNext()){
			
			final User u = it.next().getUser();
			
			if(u.getForgiveDate() == null){
				
				Rent r = it.next();
				
				//vemos los dias que hay que penalizar: 1 semana por cada dia
				DateTime actualDate = new DateTime(new Date());
				DateTime finalDate = new DateTime(r.getEndDate());
				Integer senteceDays = Days.daysBetween(actualDate, finalDate).getDays();
				if(senteceDays == 0){senteceDays = 1;}
				Date senteceDate = d.plusDays(7*senteceDays).toDate();
				
				//Gastigamos el usuario y aplicamos la pena
				log.debug(String.format("Castigamos al usuario."));
				setStatus(u, UserEnum.UNABLE);
				u.setPunishDate(new Date());
				u.setForgiveDate(senteceDate);
			
			}
		}
	}
	
	@Override
	//Cada minuto al segundo 45
	@Scheduled(cron = "15 0 * * * *")
	public void forgive() {
		
		final Iterable<User> punishedUsers = userDao.findUnable();
		final Iterator<User> iterator = punishedUsers.iterator();
		
		while(iterator.hasNext()){
			
			final User u = iterator.next();
			
			//Perdonamos a los usuarios
			log.debug(String.format("Buscamos a quien perdonar."));
			u.setPunishDate(null);
			u.setForgiveDate(null);
			u.setStatus(UserEnum.ABLE);
		}
	}
	
	
	@Override
	public UserDTO transform(User user) {
		return dozer.map(user, UserDTO.class);
	}

	@Override
	public User transform(UserDTO user) {
		return dozer.map(user, User.class);
	}
	
	@Override
	public List<UserDTO> transform(List<User> users) {	
		
		final Iterator<User> it = users.iterator();
		final List<UserDTO> usersDTO = new ArrayList<>();
		
		while (it.hasNext()) {
			final User u = it.next();
			final UserDTO uDTO = transform(u);
			usersDTO.add(uDTO);
		}
		
		return usersDTO;
	}

	@Override
	public UserDTO create(UserDTO user) throws NoDTOException{
		
		if(user == null){
			throw new NoDTOException();
		}else{
			User u = transform(user);
			final Date d = new Date();
			u.setStatus(UserEnum.ABLE);
			u.setStartDate(d);
			return transform(userDao.save(u));
		}
	}

	@Override
	public void deleteById(Integer id) {
		final User u = userDao.findOne(id);
		u.setStatus(UserEnum.ERASED);
		userDao.save(u);
	}
	
	@Override
	public void delete(UserDTO user) throws NoUserException{
		final User u = userDao.findOne(user.getId());
		if(u == null){
			throw new NoUserException();
		}else{
			u.setStatus(UserEnum.ERASED);
			userDao.save(u);
		}
	}

	@Override
	public UserDTO findById(Integer id) {
		final User u = userDao.findOne(id);
		return transform(u);
	}
	
	@Override
	public List<UserDTO> findByParams(String dni, String name, Pageable pages) throws NoUserException{
		
		List<UserDTO> users;
		
		if (pages.getPageSize()>10){
			
			users = transform(userDao.find(dni,name,new PageRequest(pages.getPageNumber(),10)));
			
		}else{
			
			users = transform(userDao.find(dni,name,pages));
			
		}
		
		if(users.isEmpty()){
			
			throw new NoUserException();
			
		}else{
			
			final Iterator<UserDTO> it = users.iterator();
			final List<UserDTO> usersDTO = new ArrayList<>();
			
			while (it.hasNext()) {
				
				final UserDTO uDTO = it.next();
				
				if (uDTO.getStatus() != UserEnum.ERASED){
					usersDTO.add(uDTO);
				}
			}
			
			return usersDTO;
		}
	}
}