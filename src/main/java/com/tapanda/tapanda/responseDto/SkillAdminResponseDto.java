package com.tapanda.tapanda.responseDto;

import java.util.stream.Collectors;

import com.tapanda.tapanda.model.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collector;

@Getter
@Setter
@AllArgsConstructor
public class SkillAdminResponseDto {

    private Long skillId;

    private String title;

    private String experience;

    private String skillLevel;

    private String detail;

    private Long sellerId;

    public SkillAdminResponseDto(Skill skill) {
        this.skillId = skill.getSkillId();
        this.title = skill.getTitle();
        this.experience = skill.getExperience().toString();
        this.skillLevel = skill.getSkillLevel().toString();
        this.detail = skill.getDetail();
        this.sellerId = skill.getSeller().getId();
    }


}
