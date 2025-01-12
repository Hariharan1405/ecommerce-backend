package com.ecommerce.service.product;

import java.util.List;

import com.ecommerce.request.AddProductRequest;
import com.ecommerce.request.ProductUpdateRequest;
import com.ecommerce.model.Product;

public interface IProductService {

	Product addProduct(AddProductRequest product);
	
	Product getProductById(Long id);
	
	void deleteProductById(Long id);
	
	Product updateProduct(ProductUpdateRequest product, Long productId);
	
	List<Product> getAllProducts();
	
	List<Product> getProductsByCategory(String category);
	
	List<Product> getProductsByBrand(String brand);
	
	List<Product> getProductsByCategoryAndBrand(String category, String brand);
	
	List<Product> getProductsByName(String name);
	
	List<Product> getProductsByBrandAndName(String brand, String name);
	
	Long countProductsByBrandAndName(String brand, String name);
	
}
