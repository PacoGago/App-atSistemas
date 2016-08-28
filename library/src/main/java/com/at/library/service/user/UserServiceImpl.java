package com.at.library.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.UserDao;
import com.at.library.dto.UserDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private DozerBeanMapper dozer;

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
	public UserDTO transform(User user) {
		return dozer.map(user, UserDTO.class);
	}

	@Override
	public User transform(UserDTO user) {
		return dozer.map(user, User.class);
	}

	@Override
	public UserDTO create(UserDTO user) {
		final User u = transform(user);
		u.setStatus(StatusEnum.ACTIVE);
		final Date d = new Date();
		u.setStartDate(d);
		return transform(userDao.save(u));
	}

	@Override
	public void delete(Integer id) {
		final User u = userDao.findOne(id);
		u.setStatus(StatusEnum.DISABLE);
		userDao.save(u);
	}

	@Override
	public UserDTO findById(Integer id) {
		final User u = userDao.findOne(id);
		return transform(u);
	}
	
}
