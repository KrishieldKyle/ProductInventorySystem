package com.kyle.product.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class ProductType {

	
	public ProductType(String productType, int productTypeId) {
		productType = this.productType;
		productTypeId = this.productTypeId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productTypeId;
	
	@Column(unique = true, nullable = false)
	private String productType;
	
	@JsonIgnore
	@ManyToMany(mappedBy="types", fetch=FetchType.LAZY)
	private Set<Product> products;
	
}
