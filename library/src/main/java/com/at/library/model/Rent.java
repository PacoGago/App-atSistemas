package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.at.library.dto.enums.StatusRent;

@Entity
public class Rent implements Serializable{

	private static final long serialVersionUID = -4158742374158942716L;
	
	@EmbeddedId
	private RentPK rentpk;
	
	@ManyToOne
	private User user;
	
	//Esto no es cierto para el modelo final
	//Pero actualmente no necesitamos un empleado
	//para hacer un alquiler. Podría establecerse un
	//empleado por defecto, es otra opción.
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Employee employee;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Enumerated(EnumType.STRING)
	private StatusRent status = StatusRent.ACTIVE;
	
	public StatusRent getStatus() {
		return status;
	}

	public void setStatus(StatusRent status) {
		this.status = status;
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

	public RentPK getRentpk() {
		return rentpk;
	}

	public void setRentpk(RentPK rentpk) {
		this.rentpk = rentpk;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
