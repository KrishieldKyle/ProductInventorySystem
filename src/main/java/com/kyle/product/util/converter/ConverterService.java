package com.kyle.product.util.converter;

import java.util.List;
import java.util.Set;

import com.kyle.product.dto.ProductDTO;
import com.kyle.product.dto.request.ProductTypeDTO;
import com.kyle.product.model.Product;
import com.kyle.product.model.ProductType;

public interface ConverterService {
	
	public ProductType convertToProductTypeEntity(ProductTypeDTO productTypeDTO);
	public ProductTypeDTO convertToProductTypeDTO(ProductType productType);
	
	public Set<ProductType> convertToProductTypeEntitySet(Set<ProductTypeDTO> productTypeDTOs);
	public Set<ProductTypeDTO> convertToProductTypeDTOSet(Set<ProductType> productTypes);
	
	public Product convertToProductEntity(ProductDTO productDTO);
	public ProductDTO convertToProductDTO(Product product);
	
	public Set<Product> convertToProductEntitySet(Set<ProductDTO> productDTOs);
	public Set<ProductDTO> convertToProductDTOSet(Set<Product> products);
	public Set<ProductDTO> convertToProductDTOSet(List<Product> products);



}
