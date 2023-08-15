package com.kyle.product.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.kyle.product.dto.request.ProductRequestDTO;
import com.kyle.product.dto.request.ProductTypeDTO;

@Component
public class ProductInputValidatorServiceImpl implements ProductInputValidatorService {
	
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	@Override
	public Map<String, Object> validateProductInput(ProductRequestDTO productRequestDto) {
		
		Map<String, Object> message = new HashMap<>();
		
		String name = Objects.toString(productRequestDto.getName(),"");
		String description = Objects.toString(productRequestDto.getDescription(),"");
		String price = Objects.toString(productRequestDto.getPrice(),"");
		String quantity = Objects.toString(productRequestDto.getQuantity(),"");
		Set<ProductTypeDTO> types = productRequestDto.getTypes();

		if(name.isEmpty() || name.equals(null)) {
			message.put("name", "Product Name is required");
		}
		
		if(description.isEmpty() || description.equals(null)) {
			message.put("description", "Description is required");
		}
		
		boolean isProductTypeValid = false;
		for(ProductTypeDTO typeDto : types) {
			if(!typeDto.getProductType().isEmpty() || !typeDto.getProductType().equals(null)) {
				isProductTypeValid = true;
				break;
			}
		}
		
		if(!isProductTypeValid || types.isEmpty()) {
			message.put("types", "Product Type is required");
		}
		
		if(price.isEmpty() || price.equals(null)) {
			message.put("price", "Product Price is required");
		}
		else if(!pattern.matcher(price).matches()) {
			message.put("price", "Product Price should be a numerical value");
		}
		
		if(quantity.isEmpty() || quantity.equals(null)) {
			message.put("quantity", "Product Quantity is required");
		}
		else if(!pattern.matcher(quantity).matches()) {
			message.put("quantity", "Product Quantity should be a numerical value");
		}
		
		
		return message;
	}

}
