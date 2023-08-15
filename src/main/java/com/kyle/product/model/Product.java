package com.kyle.product.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "product_product_type", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "productTypeId"))
	private Set<ProductType> types = new HashSet<>();
	
	@Column(nullable = false)
	private int quantity;
	
	@Column(nullable = false)
	private double price;

}
