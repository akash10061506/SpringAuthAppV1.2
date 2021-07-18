package com.connection.oracle.advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.connection.oracle.exception.EmptyFieldException;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	ResponseEntity<String> HandleNoSuchElementException(NoSuchElementException elementException) {
		return new ResponseEntity<String>("No Id value present in dp please change the value", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmptyFieldException.class)
	ResponseEntity<String> HandleEmptyFieldException(EmptyFieldException ef) {
		return new ResponseEntity<String>("Field can not be empty", HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<Object>("please change http method type  ", HttpStatus.BAD_REQUEST);
	}
}
