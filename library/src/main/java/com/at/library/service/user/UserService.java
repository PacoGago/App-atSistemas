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
	
	/**
	 * Crear un usuario.
	 * 
	 * @param user
	 * @return
	 */
	UserDTO create(UserDTO user);
	
	/**
	 * Eliminamos el usuario.
	 * 
	 * @param id
	 * @return
	 */
	void delete(Integer id);
	
	/**
	 * Buscamos por id.
	 * 
	 * @param id
	 * @return
	 */
	UserDTO findById(Integer id);
	
	/**
	 * Buscamos por dni y nombre.
	 * 
	 * @param dni, name
	 * @return List<UserDTO>
	 */
	List<UserDTO> findByParams(String dni, String name);

	/**
	 * Comprueba los usuarios a sancionar y los sanciona.
	 * 
	 * @param dni, name
	 * @return List<UserDTO>
	 */
	void penalize();

	
	/**
	 * Perdona a los usuarios.
	 * 
	 * @param dni, name
	 * @return List<UserDTO>
	 */
	void forgive();

}
