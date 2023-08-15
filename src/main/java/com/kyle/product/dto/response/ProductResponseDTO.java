package com.kyle.product.dto.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.kyle.product.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductDTO product;
	private boolean isSuccess;
	private String message;
	private HttpStatus httpStatus;

}
