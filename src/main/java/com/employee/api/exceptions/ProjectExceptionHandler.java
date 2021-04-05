package com.employee.api.exceptions;

import java.util.Date;
import java.util.logging.ErrorManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProjectExceptionHandler  {
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(value=NullPointerException.class)
	public ResponseEntity<?>  nullPointerExceptionHandler(Exception ex)
	{
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),"Contact support !!!");
		return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
		
	}
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<?>  allExceptionHandler(Exception ex)
	{
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),"Contact support !!!");
		return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
