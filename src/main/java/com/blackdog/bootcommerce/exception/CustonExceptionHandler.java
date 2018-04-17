package com.blackdog.bootcommerce.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustonExceptionHandler {

	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ExceptionData> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		ExceptionData body = new ExceptionData(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
}
