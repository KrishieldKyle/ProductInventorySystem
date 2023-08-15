package com.kyle.product.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.product.dto.request.ProductRequestDTO;
import com.kyle.product.dto.response.AllProductsResponseDTO;
import com.kyle.product.dto.response.DeleteProductResponsDTO;
import com.kyle.product.dto.response.ProductResponseDTO;
import com.kyle.product.service.ProductService;
import com.kyle.product.validation.ProductInputValidatorService;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductInputValidatorService inputValidator;
	
	@PostMapping("/")
	public ResponseEntity<?> addOrUpdateProduct(@RequestBody ProductRequestDTO productRequestDTO){
		
		Map<String, Object> message = inputValidator.validateProductInput(productRequestDTO);
		
		// Check if there is an invalid input from client
		if (!message.isEmpty()) {
			message.put("success", false);
			return ResponseEntity.unprocessableEntity().body(message);
		}
				
		ProductResponseDTO response = productService.addOrUpdateProduct(productRequestDTO);
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllProducts(){
		
		AllProductsResponseDTO response = productService.getAllProducts();
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable int productId){
		
		ProductResponseDTO response = productService.getProduct(productId);
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable int productId){
		
		DeleteProductResponsDTO response = productService.deleteProduct(productId);
		
		return ResponseEntity.status(response.getHttpStatus()).body(response);
	}

}
