package com.at.library.service.employee;

import java.util.List;

import com.at.library.dto.EmployeeDTO;
import com.at.library.model.Employee;

public interface EmployeeService {
	
	/**
	 * Realiza una búsqueda de todos los usuarios
	 * 
	 * @return listado de usuario
	 */
	List<EmployeeDTO> findAll();

	/**
	 * Transfrma un user en un UserDTO
	 * 
	 * @param user
	 * @return
	 */
	EmployeeDTO transform(Employee employee);

	/**
	 * Transforma un UserDTO en un usuario
	 * 
	 * @param user
	 * @return
	 */
	Employee transform(EmployeeDTO employee);
	
	/**
	 * Buscamos por id.
	 * 
	 * @param id
	 * @return
	 */
	EmployeeDTO findById(Integer id);

}
