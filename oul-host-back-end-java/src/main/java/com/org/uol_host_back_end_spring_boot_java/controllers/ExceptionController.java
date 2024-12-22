package com.org.uol_host_back_end_spring_boot_java.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.org.uol_host_back_end_spring_boot_java.models.dtos.ExceptionResponse;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		
		return errors;
	}
	
	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setLocalDateTime(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setPath(request.getRequestURI());
		response.setStatus(400);
		
        return ResponseEntity.status(400).body(response);
    }
	
	@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setLocalDateTime(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setPath(request.getRequestURI());
		response.setStatus(400);
		
        return ResponseEntity.status(400).body(response);
    }
}