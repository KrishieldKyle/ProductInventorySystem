package com.kyle.product.validation;

import java.util.Map;

import com.kyle.product.dto.request.ProductRequestDTO;

public interface ProductInputValidatorService {
	
	public Map<String, Object> validateProductInput(ProductRequestDTO productRequestDto);

}
