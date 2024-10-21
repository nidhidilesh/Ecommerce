package com.project.Ecommerce.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.Ecommerce.exception.ResourceNotFoundException;
import com.project.Ecommerce.exceptions.GlobalExceptionHandler;
import com.project.Ecommerce.model.Product;
import com.project.Ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepo;
	
	HttpStatus httpStatus;
	
	@Autowired
	GlobalExceptionHandler exceptionHandler;

	public ResponseEntity<String> addProduct(Product product){
		if(!product.getName().isEmpty() && product.getPrice()>0 && !product.getCategory().isEmpty() && (product.isInStock() == true || product.isInStock() == false) ){
		try {
			productRepo.save(product);
			return new ResponseEntity<>(httpStatus.OK);
		}
		catch(DataAccessException dae) {
			return exceptionHandler.handleDataAccessException(dae);
		}
		catch (Exception e) {
			return exceptionHandler.handleException(e);
		} 
		}
		return new ResponseEntity<>(httpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Product>> getAllProducts() {
		ResponseEntity<List<Product>> listOfProducts;
		try {
			listOfProducts = new ResponseEntity<List<Product>>(productRepo.findAll(), httpStatus.OK);
			return listOfProducts;
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Product>>(httpStatus.NOT_FOUND);
		}
		
	}

	public ResponseEntity<Object> getProductById(int id) {
		Optional<Product> productById = productRepo.findById(id);
		if(!productById.isEmpty()) {
			return new ResponseEntity<>(productById.get(),HttpStatus.OK);
		}else {
			Exception nfe = new RuntimeException("Record not found for the given ID");
			return exceptionHandler.handleNotFoundException(nfe);
		}
	}

}
