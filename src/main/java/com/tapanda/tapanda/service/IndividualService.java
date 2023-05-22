package com.tapanda.tapanda.service;

import com.tapanda.tapanda.model.Individual;
import com.tapanda.tapanda.requestDto.IndividualAdminRequestDto;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface IndividualService {

    void deleteByUid(String uid);

    Optional<Individual> registerIndividualByAdmin(@Valid IndividualAdminRequestDto request, String uid);

    Optional<Individual> findByUid(String uid);

    Optional<Individual> updateIndividualByAdmin(@Valid IndividualAdminRequestDto request, String uid);

    Boolean existsBySellerId(Long sellerId);
}
