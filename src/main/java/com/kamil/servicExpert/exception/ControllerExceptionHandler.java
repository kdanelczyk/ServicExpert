package com.kamil.servicExpert.exception;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ErrorMessage> handleResourceNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		
		return new ResponseEntity<ErrorMessage>(ErrorMessage
				.builder()
				.timestamp(new Date())
				.message(ex.getMessage())
				.description(request.getDescription(false))
				.build(), 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ErrorMessage> handleBadRequestException(Exception ex, WebRequest request)
			throws Exception {
		
		return new ResponseEntity<ErrorMessage>(ErrorMessage
				.builder()
				.timestamp(new Date())
				.message(ex.getMessage())
				.description(request.getDescription(false))
				.build(), 
				HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, WebRequest request) throws Exception {

		return new ResponseEntity<ErrorMessage>(ErrorMessage.builder()
				.timestamp(new Date())
				.message(ex.getMessage())
				.description(request.getDescription(false)).build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		return new ResponseEntity<Object>(ErrorMessage.builder()
				.timestamp(new Date())
				.message(ex.getFieldError().getDefaultMessage())
				.description(request.getDescription(false))
				.build(), 
				HttpStatus.BAD_REQUEST);
	}
	
}
