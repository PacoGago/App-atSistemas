package com.at.library.service.employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.EmployeeDao;
import com.at.library.dto.EmployeeDTO;
import com.at.library.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private DozerBeanMapper dozer;
	
	@Override
	public EmployeeDTO findById(Integer id) {
		final Employee e = employeeDao.findOne(id);
		return transform(e);
	}

	@Override
	public List<EmployeeDTO> findAll() {
		final Iterable<Employee> findAll = employeeDao.findAll();
		final Iterator<Employee> iterator = findAll.iterator();
		final List<EmployeeDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Employee e = iterator.next();
			final EmployeeDTO eDTO = transform(e);
			res.add(eDTO);
		}
		return res;
	}

	@Override
	public EmployeeDTO transform(Employee employee) {
		return dozer.map(employee, EmployeeDTO.class);
	}

	@Override
	public Employee transform(EmployeeDTO employee) {
		return dozer.map(employee, Employee.class);
	}

}
