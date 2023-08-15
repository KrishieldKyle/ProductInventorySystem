package com.kyle.product.util.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.kyle.product.dto.ProductDTO;
import com.kyle.product.dto.request.ProductTypeDTO;
import com.kyle.product.model.Product;
import com.kyle.product.model.ProductType;

@Component
public class ConverterServiceImpl implements ConverterService {

	@Override
	public ProductType convertToProductTypeEntity(ProductTypeDTO productTypeDTO) {

		return new ProductType(productTypeDTO.getProductType(), productTypeDTO.getProductTypeId());
	}

	@Override
	public ProductTypeDTO convertToProductTypeDTO(ProductType productType) {
		
		return new ProductTypeDTO(productType.getProductTypeId(), productType.getProductType());
	}

	@Override
	public Set<ProductType> convertToProductTypeEntitySet(Set<ProductTypeDTO> productTypeDTOs) {
		
		Set<ProductType> productTypes = new HashSet<>();
		
		for(ProductTypeDTO productTypeDTO : productTypeDTOs) {
			productTypes.add(convertToProductTypeEntity(productTypeDTO));
		}

		return productTypes;
	}

	@Override
	public Set<ProductTypeDTO> convertToProductTypeDTOSet(Set<ProductType> productTypes) {
		Set<ProductTypeDTO> productTypeDTOs = new HashSet<>();
		
		for(ProductType productType : productTypes) {
			productTypeDTOs.add(convertToProductTypeDTO(productType));
		}

		return productTypeDTOs;
	}

	@Override
	public Product convertToProductEntity(ProductDTO productDTO) {
		
		return new Product(productDTO.getProductId(),productDTO.getName(),productDTO.getDescription(), convertToProductTypeEntitySet(productDTO.getTypes()), productDTO.getQuantity(), productDTO.getPrice());
	}

	@Override
	public ProductDTO convertToProductDTO(Product product) {
		
		return new ProductDTO(product.getProductId(),product.getName(),product.getDescription(), convertToProductTypeDTOSet(product.getTypes()), product.getQuantity(), product.getPrice());
	}

	@Override
	public Set<Product> convertToProductEntitySet(Set<ProductDTO> productDTOs) {
		
		Set<Product> products = new HashSet<>();
		
		for(ProductDTO productDTO : productDTOs) {
			products.add(convertToProductEntity(productDTO));
		}
		
		return products;
	}

	@Override
	public Set<ProductDTO> convertToProductDTOSet(Set<Product> products) {
		Set<ProductDTO> productDtos = new HashSet<>();
		
		for(Product product : products) {
			productDtos.add(convertToProductDTO(product));
		}
		
		return productDtos;
	}

	@Override
	public Set<ProductDTO> convertToProductDTOSet(List<Product> products) {
		Set<ProductDTO> productDtos = new HashSet<>();
		
		for(Product product : products) {
			productDtos.add(convertToProductDTO(product));
		}
		
		return productDtos;
	}

}
