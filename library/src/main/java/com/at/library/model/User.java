package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.at.library.enums.UserEnum;

@Entity
public class User implements Serializable{


	private static final long serialVersionUID = -8002584413790572270L;

	@Id
	@GeneratedValue
	private Integer id;

	private String dni;

	private String name;
	
	@Enumerated(EnumType.STRING)
	private UserEnum status;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date punishDate;
	
	@Temporal(TemporalType.DATE)
	private Date forgiveDate;
	
	public Date getPunishDate() {
		return punishDate;
	}

	public void setPunishDate(Date punishDate) {
		this.punishDate = punishDate;
	}

	public Date getForgiveDate() {
		return forgiveDate;
	}

	public void setForgiveDate(Date forgiveDate) {
		this.forgiveDate = forgiveDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public UserEnum getStatus() {
		return status;
	}

	public void setStatus(UserEnum status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
