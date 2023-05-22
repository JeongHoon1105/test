package com.tapanda.tapanda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;

import com.tapanda.tapanda.model.Company;
import com.tapanda.tapanda.requestDto.CompanyQualRequestDto;

public interface CompanyService {

    List<Company> findAllByUserId(Long userId);

    Optional<Company> findByComQualId(Long comQualId);

	Optional<Company> registerCompanyQual(@Valid CompanyQualRequestDto request, Long userId);

    void deleteById(Long comQualId);

    void deleteBySellerId(Long sellerId);

	Optional<Company> updateCompanyQual(@Valid CompanyQualRequestDto request, Long comQualId);

}
