package com.tapanda.tapanda.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.tapanda.tapanda.exception.*;
import org.springframework.stereotype.Service;

import com.tapanda.tapanda.model.Qualification;
import com.tapanda.tapanda.model.Seller;
import com.tapanda.tapanda.repository.QualificationRepository;
import com.tapanda.tapanda.requestDto.QualificationRequestDto;
import com.tapanda.tapanda.service.QualificationService;
import com.tapanda.tapanda.service.SellerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QualificationServiceImpl implements QualificationService {

	private final QualificationRepository qualRepository;
	private final SellerService sellerService;

	@Override
	public List<Qualification> findAllByUserId(Long userId) {
		return qualRepository.findAllByUserId(userId);
	}

	@Override
	public Optional<Qualification> findByQualId(Long qualId) {
		if (!existsByQualId(qualId)) {
			throw new QualNotFoundException("해당 경력을 찾을수 없습니다.", null, null);
		}
		return qualRepository.findByQualId(qualId);
	}

	private Boolean existsByQualId(Long qualId) {
		return qualRepository.existsByQualId(qualId);
	}

	@Override
	public Optional<Qualification> registerQualification(@Valid QualificationRequestDto request, Long userId) {
		// TODO Auto-generated method stub
		if (qualRepository.countByUserId(userId) > 20) {
			throw new QualRegisterException("자격을 20건까지 등록 할수 없습니다.", null, null);
		}

		Seller seller = sellerService.findByUserId(userId)
				.orElseThrow(() -> new SellerNotFoundException("해당 판매자를 찾을수 없습니다.", null, null));
		try {
			Instant date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getDate()).toInstant();
			Qualification qual = qualRepository.save(request.toEntity(seller, date));
			return Optional.ofNullable(qual);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getDate(), "날짜형식이 옳바르지 않습니다.");
		}
	}

	@Override
	public void deleteById(Long qualId) {
		if (!existsByQualId(qualId)) {
			throw new QualNotFoundException("해당 경력을 찾을수 없습니다.", null, null);
		}
		qualRepository.deleteById(qualId);
	}

	@Override
	public void deleteBySellerId(Long sellerId) {
//        List<Qualification> qual = qualRepository.findAllBySellerId(sellerId);
//        qualRepository.deleteAll(qual);
	}

	@Override
	public Optional<Qualification> updateQual(@Valid QualificationRequestDto request, Long qualId) {
		// TODO Auto-generated method stub
		Qualification qual = findByQualId(qualId)
				.orElseThrow(() -> new QualNotFoundException("해당 경력을 찾을수 없습니다.", null, null));
		try {
			Instant date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getDate()).toInstant();
			qual.update(request.getName(), date);
			return Optional.ofNullable(qualRepository.save(qual));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getDate(), "날짜형식이 옳바르지 않습니다.");
		}
	}
}
