package com.kyle.product.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyle.product.model.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	
	public Optional<Product> findByName(String productName);

}
