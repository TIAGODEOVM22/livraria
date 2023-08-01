package com.tiago.bookstore.services.exception;

public class DataIntegrityVaiolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataIntegrityVaiolationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataIntegrityVaiolationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
