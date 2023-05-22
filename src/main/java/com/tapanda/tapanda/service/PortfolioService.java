package com.tapanda.tapanda.service;

import com.tapanda.tapanda.model.Portfolio;
import com.tapanda.tapanda.requestDto.PortfolioRequestDto;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

public interface PortfolioService {

    List<Portfolio> findAllByUserId(Long userId);

    Optional<Portfolio> findByPortId(Long portId);

	Optional<Portfolio> registerPortfolio(@Valid PortfolioRequestDto request, Long userId, MultipartFile file);

    void deleteById(Long portId);

	Optional<Portfolio> updatePortfolio(@Valid PortfolioRequestDto request, Long userId, MultipartFile file);

}
