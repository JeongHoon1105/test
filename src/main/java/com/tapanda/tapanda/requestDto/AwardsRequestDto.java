package com.tapanda.tapanda.requestDto;

import java.time.Instant;

import com.tapanda.tapanda.enum_.AwardsCategory;
import com.tapanda.tapanda.enum_.Gender;
import com.tapanda.tapanda.enum_.SkillLevel;
import com.tapanda.tapanda.model.Awards;
import com.tapanda.tapanda.model.Seller;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "프리랜서 수상경력 DTO")
@AllArgsConstructor
public class AwardsRequestDto {

	private String title;
	
	private String date;
	
	private String awardsCategory;
	
    public Awards toEntity(Seller seller, Instant date) {
		// TODO Auto-generated method stub
		return Awards.builder().seller(seller).date(date).title(title).awardsCategory(AwardsCategory.valueOf(awardsCategory)).build();
	}
}
