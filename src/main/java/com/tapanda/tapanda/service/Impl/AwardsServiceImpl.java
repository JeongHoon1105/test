package com.tapanda.tapanda.service.Impl;

import com.tapanda.tapanda.enum_.AwardsCategory;
import com.tapanda.tapanda.exception.*;
import com.tapanda.tapanda.model.Awards;
import com.tapanda.tapanda.model.Seller;
import com.tapanda.tapanda.model.Skill;
import com.tapanda.tapanda.repository.AwardsQualRepository;
import com.tapanda.tapanda.requestDto.AwardsRequestDto;
import com.tapanda.tapanda.service.AwardsService;
import com.tapanda.tapanda.service.SellerService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class AwardsServiceImpl implements AwardsService {

    private final AwardsQualRepository awardsQualRepository;
    
    private final SellerService sellerService;

    @Override
    public List<Awards> findAllByUserId(Long userId) {
        return awardsQualRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<Awards> findByAwardsQualId(Long awardsQualId) {
        if (!existsByAwardsQualId(awardsQualId)) {
            throw new AwardsQualNotFoundException("해당 수상경력을 찾을수 없습니다.", null, null);
        }
        return awardsQualRepository.findByAwardsQualId(awardsQualId);
    }

    @Override
    public Boolean existsByAwardsQualId(Long awardsQualId)  {
        return awardsQualRepository.existsByAwardsQualId(awardsQualId);
    }

	@Override
	public Optional<Awards> registerAwards(@Valid AwardsRequestDto request, Long userId) {
		// TODO Auto-generated method stub
		if (awardsQualRepository.countByUserId(userId) > 20) {
			throw new AwardsRegisterException("해당 수상경력을 20건까지 등록 할수 없습니다.", null, null);
		}
		Seller seller = sellerService.findByUserId(userId)
				.orElseThrow(() -> new SellerNotFoundException("해당 판매자를 찾을수 없습니다.", null, null));
		try {
			Instant date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getDate()).toInstant();
			Awards awards = awardsQualRepository.save(request.toEntity(seller, date));
			return Optional.ofNullable(awards);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getDate(), "날짜형식이 옳바르지 않습니다.");
		}
	}

    @Override
    public void deleteById(Long awardsQualId) {
        if (!existsByAwardsQualId(awardsQualId)) {
            throw new AwardsQualNotFoundException("해당 수상경력을 찾을수 없습니다.", null, null);
        }
        awardsQualRepository.deleteById(awardsQualId);
    }

    @Override
    public Optional<Awards> updateAwardsQual(AwardsRequestDto request, Long awardsQualId) {
        Awards awards = findByAwardsQualId(awardsQualId).orElseThrow(() -> new SkillNotFoundException("해당 수상경력을 찾을수 없습니다.", null, null));
        try {
			Instant date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getDate()).toInstant();
			awards.update(request.getTitle(), date, AwardsCategory.valueOf(request.getAwardsCategory()));
			return Optional.ofNullable(awardsQualRepository.save(awards));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getDate(), "날짜형식이 옳바르지 않습니다.");
		}
    }

    @Override
    public void deleteBySellerId(Long sellerId) {
        List<Awards> awards = awardsQualRepository.findAllBySellerId(sellerId);
        awardsQualRepository.deleteAll(awards);
    }

}
