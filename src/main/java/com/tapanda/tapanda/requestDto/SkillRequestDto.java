package com.tapanda.tapanda.requestDto;

import com.tapanda.tapanda.enum_.SkillLevel;
import com.tapanda.tapanda.model.Seller;
import com.tapanda.tapanda.model.Skill;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "스킬 DTO")
@AllArgsConstructor
public class SkillRequestDto {

	private String title;

	private Integer experience;

	private String skillLevel;

	private String detail;

	public Skill toEntity(Seller seller) {
		// TODO Auto-generated method stub
		return Skill.builder().detail(detail).experience(experience).seller(seller)
				.skillLevel(SkillLevel.valueOf(skillLevel)).title(title).build();
	}

}
