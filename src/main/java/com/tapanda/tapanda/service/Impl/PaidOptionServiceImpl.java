package com.tapanda.tapanda.service.Impl;

import java.util.Optional;

import com.tapanda.tapanda.exception.ProductNotFoundException;
import com.tapanda.tapanda.model.PaidOption;
import com.tapanda.tapanda.model.Product;
import com.tapanda.tapanda.repository.PaidOptionRepository;
import com.tapanda.tapanda.repository.ProductRepository;
import com.tapanda.tapanda.requestDto.PaidOptionRequestDto;
import com.tapanda.tapanda.service.PaidOptionService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaidOptionServiceImpl implements PaidOptionService {

    private final ProductRepository productRepository;
    private final PaidOptionRepository paidOptionRepository;

    @Override
    public Optional<PaidOption> registerPaidOption(PaidOptionRequestDto request, Long productId) {

        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = productOptional.orElseThrow(() -> new ProductNotFoundException("해당 서비스를 찾을수 없습니다.", null, null));

        PaidOption paidOption = paidOptionRepository.save(request.toEntity(product));

        return Optional.ofNullable(paidOption);
    }
}
