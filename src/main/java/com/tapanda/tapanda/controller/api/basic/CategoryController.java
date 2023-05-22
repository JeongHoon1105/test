package com.tapanda.tapanda.controller.api.basic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tapanda.tapanda.responseDto.CategoryResponseDto;
import com.tapanda.tapanda.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/category")
@Api(value = "카테고리 Rest API")
@RequiredArgsConstructor
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@GetMapping(value = "/all")
	@ApiOperation(value = "카테고리 목록 호출 api")
	public ResponseEntity<?> getCategoryList() {
		List<CategoryResponseDto> categoryDtoList = categoryService.findAll().stream()
				.map(category -> new CategoryResponseDto(category)).collect(Collectors.toList());
		return ResponseEntity.ok(categoryDtoList);
	}
	
}