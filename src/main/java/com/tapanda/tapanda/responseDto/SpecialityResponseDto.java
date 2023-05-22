package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SpecialityResponseDto {

	private Long sepcId;

	private String detail;

	private String minPrice;

	private String description;

	private String categoryName;

	public SpecialityResponseDto(Speciality spec) {
		this.sepcId = spec.getSpecId();
		this.detail = spec.getDetail();
		if (spec.getMinPrice() != null) {
			this.minPrice = spec.getMinPrice().toString();
		}
		this.description = spec.getDescription();
		if (spec.getCategory() != null) {
			this.categoryName = spec.getCategory().getName();
		}
	}
}
