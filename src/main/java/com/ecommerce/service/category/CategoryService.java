package com.ecommerce.service.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

	@Override
	public Category getCategoryById(Long id) {
		return null;
	}

	@Override
	public Category getCategoryByName(String name) {
		return null;
	}

	@Override
	public List<Category> getAllCategories() {
		return null;
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
		
	}

}
