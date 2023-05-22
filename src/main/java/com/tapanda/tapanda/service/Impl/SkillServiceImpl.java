package com.tapanda.tapanda.service.Impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.tapanda.tapanda.enum_.SkillLevel;
import org.springframework.stereotype.Service;

import com.tapanda.tapanda.exception.SellerNotFoundException;
import com.tapanda.tapanda.exception.SkillNotFoundException;
import com.tapanda.tapanda.exception.SkillRegisterException;
import com.tapanda.tapanda.model.Seller;
import com.tapanda.tapanda.model.Skill;
import com.tapanda.tapanda.repository.SkillRepository;
import com.tapanda.tapanda.requestDto.SkillRequestDto;
import com.tapanda.tapanda.service.SellerService;
import com.tapanda.tapanda.service.SkillService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

	private final SkillRepository skillRepository;
	private final SellerService sellerService;

	@Override
	public List<Skill> findAllByUserId(Long userId) {
		// TODO Auto-generated method stub
		return skillRepository.findAllByUserId(userId);
	}

	@Override
	public Optional<Skill> findBySkillId(Long skillId) {
		if (!existsBySkillId(skillId)) {
			throw new SkillNotFoundException("해당 스킬을 찾을수 없습니다.", null, null);
		}
		return skillRepository.findBySkillId(skillId);
	}

	@Override
	public Boolean existsBySkillId(Long skillId) {
		return skillRepository.existsBySkillId(skillId);
	}

	@Override
	public Optional<Skill> registerSkill(@Valid SkillRequestDto request, Long userId) {
		// TODO Auto-generated method stub
		if (skillRepository.countByUserId(userId) > 20) {
			throw new SkillRegisterException("스킬을 20건까지 등록 할수 없습니다.", null, null);
		}
		Seller seller = sellerService.findByUserId(userId)
				.orElseThrow(() -> new SellerNotFoundException("해당 판매자를 찾을수 없습니다.", null, null));
		Skill skill = skillRepository.save(request.toEntity(seller));
		return Optional.ofNullable(skill);
	}

    @Override
    public void deleteById(Long skillId) {
        if (!existsBySkillId(skillId)) {
            throw new SkillNotFoundException("해당 스킬을 찾을수 없습니다.", null, null);
        }
        skillRepository.deleteById(skillId);
    }

	@Override
	public Optional<Skill> updateSkill(SkillRequestDto request, Long skillId) {
		Skill skill = findBySkillId(skillId).orElseThrow(() -> new SkillNotFoundException("해당 스킬을 찾을수 없습니다.", null, null));
		skill.update(request.getTitle(), request.getExperience(), SkillLevel.valueOf(request.getSkillLevel()), request.getDetail());
		return Optional.ofNullable(skillRepository.save(skill));
	}

	@Override
    public void deleteBySellerId(Long sellerId) {
        List<Skill> skill = skillRepository.findAllBySellerId(sellerId);
        skillRepository.deleteAll(skill);
    }

}
