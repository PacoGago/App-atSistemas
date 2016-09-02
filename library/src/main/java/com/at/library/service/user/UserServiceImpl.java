package com.at.library.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.at.library.dao.UserDao;
import com.at.library.dto.UserDTO;
import com.at.library.enums.UserEnum;
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
	@Scheduled(cron = "15 0/1 * * * ?")
	public void penalize() {
		//Este log lo ponemos cuando realmente se vaya penalizar a alguien.
		log.debug(String.format("Buscamos los usarios a penalizar."));
		
		final Iterable<Rent> ir = rentservice.findBehind();
		final Iterator<Rent> it = ir.iterator();
		
		while(it.hasNext()){
			
			final User u = it.next().getUser();
			
			if(u.getForgiveDate() == null){
				
				
				
			}else{
				
				
			}
		}
	}
	
	@Override
	//Cada minuto al segundo 45
	@Scheduled(cron = "45 0/1 * * * ?")
	public void forgive() {
		//Este log lo ponemos cuando realmente se vaya a perdonar.
		log.debug(String.format("Buscamos a quien perdonar."));
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
	public UserDTO create(UserDTO user) {
		User u = transform(user);
		final Date d = new Date();
		u.setStatus(UserEnum.ABLE);
		u.setStartDate(d);
		return transform(userDao.save(u));
	}

	@Override
	public void deleteById(Integer id) {
		final User u = userDao.findOne(id);
		u.setStatus(UserEnum.ERASED);
		userDao.save(u);
	}
	
	@Override
	public void delete(UserDTO user) {
		final User u = userDao.findOne(user.getId());
		u.setStatus(UserEnum.ERASED);
		userDao.save(u);
	}

	@Override
	public UserDTO findById(Integer id) {
		final User u = userDao.findOne(id);
		return transform(u);
	}
	
	@Override
	public List<UserDTO> findByParams(String dni, String name) {
		
		List<UserDTO> users = transform(userDao.find(dni,name));
		
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
