package com.store.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
 

@RestControllerAdvice
public class GlobalExceptionHandlerController {
	
	
	  @ExceptionHandler(CustomException.class)
	  public ResponseEntity<String> handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
	    //res.sendError(ex.getHttpStatus().value(), ex.getMessage()); 
	    return ResponseEntity.status(ex.getHttpStatus().value()).body(ex.getMessage()); 
	  }
	  

}
