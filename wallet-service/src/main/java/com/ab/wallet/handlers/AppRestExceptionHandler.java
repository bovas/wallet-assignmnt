package com.ab.wallet.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ab.wallet.exception.PlayerNotFoundException;

/**
 * @author Bovas
 *
 */
@ControllerAdvice
public class AppRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = { UnsupportedOperationException.class })
	protected ResponseEntity<Object> handleUnsupportedOperationException(UnsupportedOperationException ex, WebRequest request) {
		
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}		
	
	@ExceptionHandler(value = { PlayerNotFoundException.class})
	protected ResponseEntity<Object> handlePlayerNotFoundException(PlayerNotFoundException ex, WebRequest request) {
		
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = { Exception.class})
	protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
		
		String bodyOfResponse = "Unexpected Error Happened, Please try after sometime.";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
