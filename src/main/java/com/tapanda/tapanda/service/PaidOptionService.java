package com.tapanda.tapanda.service;

import com.tapanda.tapanda.model.PaidOption;
import com.tapanda.tapanda.requestDto.PaidOptionRequestDto;

import java.util.Optional;

public interface PaidOptionService {

    Optional<PaidOption> registerPaidOption(PaidOptionRequestDto request, Long productId);

}
