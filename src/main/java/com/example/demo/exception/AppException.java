package com.example.demo.exception;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 8132169727962425351L;
	
	public AppException(String message) {
		super(message);
	}
	
	public AppException(String message, Exception ex) {
		super(message, ex);
	}

}
