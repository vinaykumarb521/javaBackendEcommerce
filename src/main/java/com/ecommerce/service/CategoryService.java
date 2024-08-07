package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Category;
import com.ecommerce.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public boolean saveOrUpdateCategory(Category category) {
		Optional<Category> existingCategory = categoryRepository.findByCategory(category.getCategory());
		if (existingCategory.isPresent()) {
			Category cat = existingCategory.get();
			cat.setCategory(category.getCategory()); 
			categoryRepository.save(cat);
			return true; 
		} else {
			categoryRepository.save(category);
			return false; 
		}
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
}
