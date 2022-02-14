package com.afs.ezra.simpletype.provider.common;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class HttpException extends Exception {

	private HttpStatus statusCode;
	
	public HttpException(HttpStatus status) {
		super();
		statusCode = status;
	}

}
