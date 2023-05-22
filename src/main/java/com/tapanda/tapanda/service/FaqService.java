package com.tapanda.tapanda.service;

import java.util.Optional;

import com.tapanda.tapanda.model.Faq;
import com.tapanda.tapanda.requestDto.FaqRequestDto;

public interface FaqService {

    Optional<Faq> registerFaq(FaqRequestDto request, Long productId);
}