package com.project.Ecommerce.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Ecommerce.model.Product;
import com.project.Ecommerce.service.ProductService;

@Controller
@CrossOrigin("*")
public class ProductController {
	
	@Autowired
	ProductService productService;

	
	@GetMapping("/")
	public String displayDefaultPage() {
		System.out.println("Index");
		return "index.html";
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		product.setTimestamp(LocalDateTime.now());
		ResponseEntity<String> productResponse = null;
		productResponse = productService.addProduct(product);
		System.out.println(product);
		return productResponse;
	}
	@GetMapping("/getProducts")
	public ResponseEntity<List<Product>> getAllProducts(){
		return productService.getAllProducts();
	}
	
	
	@GetMapping("/getProducts/{id}")
	public ResponseEntity<Object> getProductById(@PathVariable int id){
		return productService.getProductById(id);
	}
}
