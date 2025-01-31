package com.ecommerce.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.request.AddProductRequest;
import com.ecommerce.request.ProductUpdateRequest;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Product addProduct(AddProductRequest request) {
		
		Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
				.orElseGet(() -> {
					Category newCategory = new Category(request.getCategory().getName());
					return categoryRepository.save(newCategory);
				});
		return createProduct(request, category);
	}
	
	private Product createProduct(AddProductRequest request, Category category) {
		return new Product(
				request.getName(),
				request.getBrand(),
				request.getPrice(),
				request.getQuantity(),
				request.getDescription(),
				category
				);
	}

	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
	}

	@Override
	public void deleteProductById(Long id) {
		productRepository.findById(id)
				.ifPresentOrElse(productRepository::delete, 
						() -> { throw new ResourceNotFoundException("Product not found!");
								}
						);
	}

	@Override
	public Product updateProduct(ProductUpdateRequest request, Long productId) {
		return productRepository.findById(productId)
				.map(existingProduct -> updateExistingProduct(existingProduct, request))
				.map(productRepository :: save)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
	}
	
	private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
		existingProduct.setName(request.getName());
		existingProduct.setBrand(request.getBrand());
		existingProduct.setPrice(request.getPrice());
		existingProduct.setQuantity(request.getQuantity());
		existingProduct.setDescription(request.getDescription());
		
		Category category = categoryRepository.findByName(request.getCategory().getName());
		existingProduct.setCategory(category);
		
		return existingProduct;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productRepository.findByCategoryName(category);
	}

	@Override
	public List<Product> getProductsByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}

	@Override
	public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
		return productRepository.findByCategoryNameAndBrand(category, brand);
	}

	@Override
	public List<Product> getProductsByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> getProductsByBrandAndName(String brand, String name) {
		return productRepository.findByBrandAndName(brand, name);
	}

	@Override
	public Long countProductsByBrandAndName(String brand, String name) {
		return productRepository.countByBrandAndName(brand, name);
	}
	
	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		super();
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

}
