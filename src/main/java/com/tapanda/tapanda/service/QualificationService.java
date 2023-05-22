package com.tapanda.tapanda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;

import com.tapanda.tapanda.model.Qualification;
import com.tapanda.tapanda.requestDto.QualificationRequestDto;

public interface QualificationService {

    List<Qualification> findAllByUserId(Long userId);

    Optional<Qualification> findByQualId(Long qualId);

	Optional<Qualification> registerQualification(@Valid QualificationRequestDto request, Long userId);

    void deleteById(Long qualId);

    void deleteBySellerId(Long sellerId);

	Optional<Qualification> updateQual(@Valid QualificationRequestDto request, Long qualId);

}
