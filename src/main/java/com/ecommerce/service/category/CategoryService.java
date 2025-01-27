package com.ecommerce.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.exceptions.AlreadyExistsException;
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
		return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
				.map(categoryRepository :: save)
				.orElseThrow(() -> new AlreadyExistsException(category.getName() 
						+ " already exists"));
	}

	@Override
	public Category updateCategory(Category category, Long id) {
		return Optional.ofNullable(getCategoryById(id)).map(oldCategory -> {
			oldCategory.setName(category.getName());
			return categoryRepository.save(oldCategory);
		}).orElseThrow(() -> new ResourceNotFoundException("Category " + id + " is updated"));
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
