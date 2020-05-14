package com.test.microservice.currencyexchangeservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends 
ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex,WebRequest req) {
		ExceptionResponse response = new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(false));
		return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ExchangeValueNotFoundException.class)
	public final ResponseEntity<Object> handleExchangeValueNotFoundException(ExchangeValueNotFoundException ex,WebRequest req) {
		ExceptionResponse response = new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(false));
		return new ResponseEntity(response,HttpStatus.NOT_FOUND);
	}
	
}
