package com.kyle.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.kyle.product.dao")
public class ProductInventorySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductInventorySystemApplication.class, args);
	}

}
