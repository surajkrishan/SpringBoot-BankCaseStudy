package com.mybank.su40013388.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CidNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetails cidNotFound(CidNotFoundException ex) {

		return new CustomErrorDetails(new Date(), "From @RestControllerAdvice NOT FOUND", ex.getMessage());
	}

}
