package com.kyle.product.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kyle.product.dao.ProductDAO;
import com.kyle.product.dao.ProductTypeDAO;
import com.kyle.product.dto.ProductDTO;
import com.kyle.product.dto.request.ProductRequestDTO;
import com.kyle.product.dto.request.ProductTypeDTO;
import com.kyle.product.dto.response.AllProductsResponseDTO;
import com.kyle.product.dto.response.DeleteProductResponsDTO;
import com.kyle.product.dto.response.ProductResponseDTO;
import com.kyle.product.model.Product;
import com.kyle.product.model.ProductType;
import com.kyle.product.util.converter.ConverterService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ProductTypeDAO productTypeDAO;

	@Autowired
	private ConverterService convert;

	@Override
	public ProductResponseDTO addOrUpdateProduct(ProductRequestDTO productRequestDTO) {		

		Set<ProductType> productTypes = new HashSet<>();
		
		ProductType productType = null;
		
		// Convert all ProductTypeDTO to ProductType model
		for(ProductTypeDTO typeDto : productRequestDTO.getTypes()) {
			
			productType = new ProductType();
			productType.setProductType(typeDto.getProductType());
			productType.setProductTypeId(typeDto.getProductTypeId());
			
			productTypes.add(productType);

		}
		
		// Save the new Product Types
		productTypeDAO.saveAll(productTypes);
		
		// Construct the Product
		Product newProduct = new Product();
		newProduct.setDescription(productRequestDTO.getDescription());
		newProduct.setName(productRequestDTO.getName());
		newProduct.setPrice(Double.parseDouble(productRequestDTO.getPrice()));
		newProduct.setQuantity(Integer.parseInt(productRequestDTO.getQuantity()));
		newProduct.setTypes(productTypes);
		
		// Get the product via product name if the product exist, do something to just update the product
		Optional<Product> maybeProduct = productDAO.findByName(productRequestDTO.getName());
		
		// check if the product already exists base on its product name
		if(maybeProduct.isPresent()) {
			
			// Product is already in the database, add the ID of the product so JPA will just update it
			Product product = maybeProduct.get();
			
			// set the ID of the product to perform update
			newProduct.setProductId(product.getProductId());
			
		}
		
		// Save the Product to the database
		Product savedProduct = productDAO.save(newProduct);

		// Construct a Product DTO object to return to the client, we don't want to return the actual model class to the client for security purposes.
		ProductDTO productDTO = convert.convertToProductDTO(savedProduct);
		
		return new ProductResponseDTO(productDTO, true, "The Product has successfully saved!", HttpStatus.OK);
	}

	@Override
	public AllProductsResponseDTO getAllProducts() {
		
		List<Product> products = productDAO.findAll();
		
		if(products.isEmpty()) {
			return new AllProductsResponseDTO(null,true,"There are no saved Products as of now",HttpStatus.ACCEPTED);
		}
		
		// Convert all Product Entity into Product DTO so we can expose the product to the client safely
		Set<ProductDTO> productDtos = convert.convertToProductDTOSet(products);
		
		
		return new AllProductsResponseDTO(productDtos,true,"Products successfully loaded",HttpStatus.OK);
	}

	@Override
	public ProductResponseDTO getProduct(int productId) {
		
		Optional<Product> maybeProduct = productDAO.findById(productId);
		
		
		
		if(maybeProduct.isPresent()) {
			
			Product product = maybeProduct.get();
			
			ProductDTO productDto = convert.convertToProductDTO(product);
			
			return new ProductResponseDTO(productDto, true, "Product successfully loaded!", HttpStatus.OK);
			
		}
		
		
		
		return new ProductResponseDTO(null, true, "Product does not exists", HttpStatus.ACCEPTED);
	}

	@Override
	public DeleteProductResponsDTO deleteProduct(int productId) {
		
		Optional<Product> maybeProduct = productDAO.findById(productId);
		
		if(maybeProduct.isPresent()) {
			
			productDAO.delete(maybeProduct.get());
			
			return new DeleteProductResponsDTO(true, "Product successfully deleted", HttpStatus.OK);
			
		}
		
		return new DeleteProductResponsDTO(false, "Product with given product id does not exists", HttpStatus.BAD_REQUEST);
	}

	
}
