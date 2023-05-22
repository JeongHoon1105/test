package com.tapanda.tapanda.responseDto;

import com.tapanda.tapanda.model.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SkillResponseDto {

    private String title;

    private String experience;

    private String skillLevel;

    private String detail;

    public SkillResponseDto(Skill skill) {
        this.title = skill.getTitle();
        this.experience = skill.getExperience().toString();
        this.skillLevel = skill.getSkillLevel().toString();
        this.detail = skill.getDetail();
    }
}
