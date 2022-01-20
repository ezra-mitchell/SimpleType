package com.afs.ezra.simpleType.common;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionControllerAdvice {

	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Void> recordNotFound(){
		return ResponseEntity.notFound().build();
	}
}
