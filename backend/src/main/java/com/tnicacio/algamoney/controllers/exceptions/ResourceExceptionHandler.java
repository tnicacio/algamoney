package com.tnicacio.algamoney.controllers.exceptions;

import com.tnicacio.algamoney.services.exceptions.DatabaseException;
import com.tnicacio.algamoney.services.exceptions.InvalidUniqueIdentifierException;
import com.tnicacio.algamoney.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

	private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
	private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
	private static final HttpStatus UNPROCESSABLE_ENTITY = HttpStatus.UNPROCESSABLE_ENTITY;

	private final MessageSource messageSource;

	@Autowired
	public ResourceExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(NOT_FOUND.value());
		err.setError(messageSource.getMessage("exception.resource_not_found", null, LocaleContextHolder.getLocale()));
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(NOT_FOUND).body(err);
	}	
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(BAD_REQUEST.value());
		err.setError(messageSource.getMessage("exception.database_exception", null, LocaleContextHolder.getLocale()));
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(BAD_REQUEST).body(err);
	}

	@ExceptionHandler(InvalidUniqueIdentifierException.class)
	public ResponseEntity<StandardError> uniqueIdentifier(InvalidUniqueIdentifierException e, HttpServletRequest request) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(BAD_REQUEST.value());
		err.setError(messageSource.getMessage("exception.invalid_unique_identifier", null, LocaleContextHolder.getLocale()));
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError err = new ValidationError();
		err.setTimestamp(Instant.now());
		err.setStatus(UNPROCESSABLE_ENTITY.value());
		err.setError(messageSource.getMessage("exception.validation", null, LocaleContextHolder.getLocale()));
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());

		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}

		return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(err);
	}
}
