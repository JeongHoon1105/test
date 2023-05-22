package com.tapanda.tapanda.requestDto;

import com.tapanda.tapanda.model.Category;
import com.tapanda.tapanda.model.Seller;
import com.tapanda.tapanda.model.Speciality;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "특기분야 DTO")
@AllArgsConstructor
public class SpecialityRequestDto {

	private String detail;

	private Long minPrice;

	private String description;

	private String categoryCode;

	public Speciality toEntity(Seller seller, Category category) {
		// TODO Auto-generated method stub
		return Speciality.builder().detail(detail).description(description).minPrice(minPrice).seller(seller)
				.category(category).build();
	}
}
