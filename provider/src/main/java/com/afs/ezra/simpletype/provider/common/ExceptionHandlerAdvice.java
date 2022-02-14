package com.afs.ezra.simpletype.provider.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(HttpException.class)
	public ResponseEntity<Void> handleHttpException(HttpException e){
		return new ResponseEntity<Void>(e.getStatusCode());
	}

}
