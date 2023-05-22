package com.tapanda.tapanda.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.tapanda.tapanda.model.Awards;
import com.tapanda.tapanda.requestDto.AwardsRequestDto;

public interface AwardsService {

    List<Awards> findAllByUserId(Long userId);

    Optional<Awards> findByAwardsQualId(Long awardsQualId);

    Boolean existsByAwardsQualId(Long awardsQualId);

	Optional<Awards> registerAwards(@Valid AwardsRequestDto request, Long userId);

    void deleteById(Long awardsQualId);

    Optional<Awards> updateAwardsQual(@Valid AwardsRequestDto request, Long awardsQualId);

    void deleteBySellerId(Long sellerId);

}
