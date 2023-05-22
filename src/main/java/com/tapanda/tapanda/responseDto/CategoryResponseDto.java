package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryResponseDto {

	private String name;
	
    private String parentCode;

	private String code;
	
	private Long seq;

	public CategoryResponseDto(Category category) {
		this.name = category.getName();
		this.parentCode = category.getParentCode();
		this.code = category.getCode();
		this.seq = category.getSeq();
	}

}