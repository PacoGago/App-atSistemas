package com.at.library.dto;

import java.io.Serializable;
import java.util.Date;

import com.at.library.model.Employee;
import com.at.library.model.RentPK;
import com.at.library.model.User;

public class RentDTO implements Serializable{
	
	private static final long serialVersionUID = -1665259051800304339L;

	private RentPK rentpk;
	
	private User user;
	
	private Employee employee;
	
	private Date endDate;

	public RentPK getRentpk() {
		return rentpk;
	}

	public void setRentpk(RentPK rentpk) {
		this.rentpk = rentpk;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
