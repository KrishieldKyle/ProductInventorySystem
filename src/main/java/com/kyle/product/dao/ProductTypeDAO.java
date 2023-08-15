package com.kyle.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyle.product.model.ProductType;

public interface ProductTypeDAO extends JpaRepository<ProductType, Integer>{

}
