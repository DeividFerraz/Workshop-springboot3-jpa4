package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.DataBaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice//Isso vai interceptar as esseções que acontecerem, para que esse objeto possa executar o tratamento
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)//usei essa anotation para ela interceptar qualquer essção que for lançada desse tipo, e ser afeito o tratamento abaixo
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest req){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataBaseException.class)//usei essa anotation para ela interceptar qualquer essção que for lançada desse tipo, e ser afeito o tratamento abaixo
	public ResponseEntity<StandardError> resourceNotFound(DataBaseException e, HttpServletRequest req){
		String error = "Data base error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
