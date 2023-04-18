package com.hariyali.hariyali_project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class CustomException {

	private static final long serialVersionUID = 1L;
	private String message;
	public CustomException(String message, String details) {
		super();
		this.message = message;
		this.details = details;
	}
	
	public CustomException() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String details;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
