package com.employee.api.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectExceptionHandler  {
	
	private static final String EXCEPTION_SOLUTION="Contact support !!!";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(value=NullPointerException.class)
	public ResponseEntity<ErrorDetails>  nullPointerExceptionHandler(Exception ex)
	{
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),EXCEPTION_SOLUTION);
		return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(value=RuntimeException.class)
	public ResponseEntity<ErrorDetails> runtimeExceptionHandeller(Exception ex)
	{
		return new ProjectExceptionHandler().allExceptionHandler(ex);		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<ErrorDetails>  allExceptionHandler(Exception ex)
	{
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),EXCEPTION_SOLUTION);
		return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
