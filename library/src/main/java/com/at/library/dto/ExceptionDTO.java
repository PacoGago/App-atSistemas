package com.at.library.dto;

import java.io.Serializable;

public class ExceptionDTO implements Serializable{
	
	private static final long serialVersionUID = 8670170908906612834L;
	private Integer code;
	private String msg;
	
	public ExceptionDTO(){
		super();
	}
	
	public ExceptionDTO(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
