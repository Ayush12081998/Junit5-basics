package com.example.junit5.exception.service.custom;

import com.example.junit5.exception.service.ServiceException;

public class EmployeeNotFound extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFound() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFound(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFound(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
