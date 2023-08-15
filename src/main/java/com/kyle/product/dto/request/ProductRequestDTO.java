package com.kyle.product.dto.request;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int productId;
	private String name;
	private String description;
	private Set<ProductTypeDTO> types = new HashSet<>();
	private String quantity;
	private String price;

}
