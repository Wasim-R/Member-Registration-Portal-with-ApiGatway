package com.cognizant.user.exception;

public class DataMissingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataMissingException(String message) {
		super(message);
	}
}