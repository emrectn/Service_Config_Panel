package com.example.demo.exception;

public class DemoAppException extends RuntimeException {

	private static final long serialVersionUID = 8132169727962425351L;
	
	public DemoAppException(String message) {
		super(message);
	}
	
	public DemoAppException(String message, Exception ex) {
		super(message, ex);
	}

}
