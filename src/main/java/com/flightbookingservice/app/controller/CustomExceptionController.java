package com.flightbookingservice.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flightbookingservice.app.services.*;

@ControllerAdvice
public class CustomExceptionController {

	@ExceptionHandler(value=CustomBookingException.class)
	public ResponseEntity<Object> exception(CustomBookingException bookingexception)
	{
		return new ResponseEntity<>(CustomBookingException.message, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
