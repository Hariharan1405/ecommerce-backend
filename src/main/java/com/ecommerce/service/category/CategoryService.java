package com.ecommerce.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("Category not found")
				);
	}

	@Override
	public Category getCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category addCategory(Category category) {
		return null;
	}

	@Override
	public Category updateCategory(Category category) {
		return null;
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.findById(id)
		.ifPresentOrElse(categoryRepository::delete, 
				() -> { 
					throw new ResourceNotFoundException("Category "+ id +" not found");
				});
	}
	
	public CategoryService() {
		super();
	}

}
