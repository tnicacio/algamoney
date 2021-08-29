package com.tnicacio.algamoney.services.exceptions;

public class InvalidUniqueIdentifierException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidUniqueIdentifierException(String message) {
		super(message);
	}

}
