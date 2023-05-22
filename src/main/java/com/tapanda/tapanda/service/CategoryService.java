package com.tapanda.tapanda.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.tapanda.tapanda.model.Category;
import com.tapanda.tapanda.requestDto.CategoryRequestDto;

public interface CategoryService {
	
	List<Category> findAll();
	
	Optional<Category> findByCode(String code);
	
	Boolean existsByCode(String code);
	
	Optional<Category> registerCategory(@Valid CategoryRequestDto request);

	Optional<Category> updateCategory(String code, @Valid CategoryRequestDto request);

	void deleteByCode(String code);

	Long findIdByCategoryCode(String categoryCode);
}