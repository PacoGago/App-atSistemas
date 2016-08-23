package com.at.library.service.user;

import java.util.List;

import com.at.library.dto.UserDTO;
import com.at.library.model.User;

public interface UserService {
	
	/**
	 * Realiza una búsqueda de todos los usuarios
	 * 
	 * @return listado de usuario
	 */
	List<UserDTO> findAll();

	/**
	 * Transfrma un user en un UserDTO
	 * 
	 * @param user
	 * @return
	 */
	UserDTO transform(User user);

	/**
	 * Transforma un UserDTO en un usuario
	 * 
	 * @param user
	 * @return
	 */
	User transform(UserDTO user);

}
