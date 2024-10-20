package com.project.Ecommerce.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.Ecommerce.model.Product;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	public ResponseEntity<String> handleException(Exception ex) {
		log.error("Exception occurred");
		ex.printStackTrace();
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<String> handleDataAccessException(DataAccessException dae) {
		log.error("DataAccessException occurred");
		dae.printStackTrace();
		return new ResponseEntity<String>(dae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<String> handleNotFoundException(Exception nfe) {
		// TODO Auto-generated method stub
		log.error("Record not found for the given ID");
		nfe.printStackTrace();
		return new ResponseEntity<String>(nfe.getMessage(), HttpStatus.NOT_FOUND);
	}

}
