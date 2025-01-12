package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByBrand(String brand);

	List<Product> findByCategoryName(String category);
	
	List<Product> findByCategoryNameAndBrand(String category, String brand);

	List<Product> findByBrandAndName(String brand, String name);

	List<Product> findByName(String name);

	Long countByBrandAndName(String brand, String name);
	
}
