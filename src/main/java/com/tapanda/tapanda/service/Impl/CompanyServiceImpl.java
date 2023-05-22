package com.tapanda.tapanda.service.Impl;

import com.tapanda.tapanda.exception.*;

import com.tapanda.tapanda.model.Company;
import com.tapanda.tapanda.model.Seller;
import com.tapanda.tapanda.repository.ComQualRepository;
import com.tapanda.tapanda.requestDto.CompanyQualRequestDto;
import com.tapanda.tapanda.service.CompanyService;
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
public class CompanyServiceImpl implements CompanyService {

	private final ComQualRepository comQualRepository;
	private final SellerService sellerService;

	@Override
	public List<Company> findAllByUserId(Long userId) {
		return comQualRepository.findAllByUserId(userId);
	}

	@Override
	public Optional<Company> findByComQualId(Long comQualId) {
		if (!existsByComQualId(comQualId)) {
			throw new CompanyQualNotFoundException("해당 직장경력을 찾을수 없습니다.", null, null);
		}
		return comQualRepository.findByComQualId(comQualId);
	}

	private Boolean existsByComQualId(Long comQualId) {
		return comQualRepository.existsByComQualId(comQualId);
	}

	@Override
	public Optional<Company> registerCompanyQual(@Valid CompanyQualRequestDto request, Long userId) {
		// TODO Auto-generated method stub
		if (comQualRepository.countByUserId(userId) > 20) {
			throw new CompanyQUalRegisterException("해당 직장경력을 찾을수 없습니다.", null, null);
		}

		Seller seller = sellerService.findByUserId(userId)
				.orElseThrow(() -> new SellerNotFoundException("해당 판매자를 찾을수 없습니다.", null, null));
		try {
			Instant startDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getStartDate()).toInstant();
			Instant endDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getFinishDate()).toInstant();
			Company company = comQualRepository.save(request.toEntity(seller, startDate, endDate));
			return Optional.ofNullable(company);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getStartDate(), "날짜형식이 옳바르지 않습니다.");
		}
	}

	@Override
	public void deleteById(Long comQualId) {
		if (!existsByComQualId(comQualId)) {
			throw new CompanyQualNotFoundException("해당 직장경력을 찾을수 없습니다.", null, null);
		}
		comQualRepository.deleteById(comQualId);
	}

	@Override
	public void deleteBySellerId(Long sellerId) {
		List<Company> company = comQualRepository.findAllBySellerId(sellerId);
		comQualRepository.deleteAll(company);
	}

	@Override
	public Optional<Company> updateCompanyQual(@Valid CompanyQualRequestDto request, Long comQualId) {
		// TODO Auto-generated method stub
		Company company = comQualRepository.findByComQualId(comQualId)
				.orElseThrow(() -> new CompanyQualNotFoundException("해당 직장경력을 찾을수 없습니다.", null, null));
		try {
			Instant startDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getStartDate()).toInstant();
			Instant endDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getFinishDate()).toInstant();
			company.update(request.getName(), request.getPosition(), startDate, endDate, request.getPosition());
			return Optional.ofNullable(comQualRepository.save(company));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getStartDate(), "날짜형식이 옳바르지 않습니다.");
		}
	}
}
