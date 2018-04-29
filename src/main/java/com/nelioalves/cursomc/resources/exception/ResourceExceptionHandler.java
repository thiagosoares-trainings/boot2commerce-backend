package com.nelioalves.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.nelioalves.cursomc.services.exceptions.AuthorizationException;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.FileException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrityException(DataIntegrityException e, HttpServletRequest request) {
        
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erros de Validacao", System.currentTimeMillis());
        
        for(FieldError errField : e.getBindingResult().getFieldErrors()) {
          err.addError(errField.getField(), errField.getDefaultMessage());
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
	
	@ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorizationException(AuthorizationException e, HttpServletRequest request) {
        
        StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
	
	@ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> fileException(FileException e, HttpServletRequest request) {
        
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
	
	@ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonServiceException(AmazonServiceException e, HttpServletRequest request) {
        
	    HttpStatus status = HttpStatus.valueOf(e.getErrorCode());
	  
        StandardError err = new StandardError(status.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(status).body(err);
    }
	
	@ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClientException(AmazonClientException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
	
	@ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3Exception(AmazonS3Exception e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
