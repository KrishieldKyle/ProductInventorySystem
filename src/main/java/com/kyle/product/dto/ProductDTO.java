package com.kyle.product.dto;
import java.util.Set;

import com.kyle.product.dto.request.ProductTypeDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private int productId;
	private String name;
	private String description;
	private Set<ProductTypeDTO> types;
	private int quantity;
	private double price;

}
