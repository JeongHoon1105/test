package com.tapanda.tapanda.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.tapanda.tapanda.model.Skill;
import com.tapanda.tapanda.requestDto.SkillRequestDto;

public interface SkillService {

    List<Skill> findAllByUserId(Long userId);

    Optional<Skill> findBySkillId(Long skillId);

    Boolean existsBySkillId(Long skillId);
    
    Optional<Skill> registerSkill(@Valid SkillRequestDto request, Long userId);

    void deleteById(Long skillId);

    Optional<Skill> updateSkill(@Valid SkillRequestDto request, Long skillId);

    void deleteBySellerId(Long sellerId);

}
