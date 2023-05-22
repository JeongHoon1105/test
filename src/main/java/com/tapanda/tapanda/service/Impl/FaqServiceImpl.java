package com.tapanda.tapanda.service.Impl;

import java.util.Optional;

import com.tapanda.tapanda.exception.PaidOptionNotFoundException;
import com.tapanda.tapanda.exception.ProductNotFoundException;
import com.tapanda.tapanda.model.Faq;
import com.tapanda.tapanda.model.PaidOption;
import com.tapanda.tapanda.model.Product;
import com.tapanda.tapanda.repository.FaqRepository;
import com.tapanda.tapanda.repository.PaidOptionRepository;
import com.tapanda.tapanda.repository.ProductRepository;
import com.tapanda.tapanda.requestDto.FaqRequestDto;
import com.tapanda.tapanda.requestDto.PaidOptionRequestDto;
import com.tapanda.tapanda.service.FaqService;

import com.tapanda.tapanda.service.PaidOptionService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final ProductRepository productRepository;
    private final FaqRepository faqRepository;

    @Override
    public Optional<Faq> registerFaq(FaqRequestDto request, Long productId) {

        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = productOptional.orElseThrow(() -> new ProductNotFoundException("해당 서비스를 찾을수 없습니다.", null, null));

        Faq faq = faqRepository.save(request.toEntity(product));

        return Optional.ofNullable(faq);
    }
}