package com.kyle.product.service;

import com.kyle.product.dto.request.ProductRequestDTO;
import com.kyle.product.dto.response.AllProductsResponseDTO;
import com.kyle.product.dto.response.DeleteProductResponsDTO;
import com.kyle.product.dto.response.ProductResponseDTO;

public interface ProductService {
	
	public ProductResponseDTO addOrUpdateProduct(ProductRequestDTO productRequestDTO);
	public AllProductsResponseDTO getAllProducts();
	public ProductResponseDTO getProduct(int productId);
	public DeleteProductResponsDTO deleteProduct(int productId);

}
