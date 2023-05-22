package com.tapanda.tapanda.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.tapanda.tapanda.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tapanda.tapanda.model.Category;
import com.tapanda.tapanda.model.Image;
import com.tapanda.tapanda.model.Portfolio;
import com.tapanda.tapanda.model.Seller;
import com.tapanda.tapanda.repository.PortfolioRepository;
import com.tapanda.tapanda.requestDto.PortfolioRequestDto;
import com.tapanda.tapanda.service.CategoryService;
import com.tapanda.tapanda.service.FileStorageService;
import com.tapanda.tapanda.service.ImageService;
import com.tapanda.tapanda.service.PortfolioService;
import com.tapanda.tapanda.service.SellerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

	private final PortfolioRepository portRepository;
	private final SellerService sellerService;
	private final CategoryService categoryService;
	private final FileStorageService fileStorageService;
	private final ImageService imageService;

	@Override
	public Optional<Portfolio> registerPortfolio(@Valid PortfolioRequestDto request, Long userId, MultipartFile file) {
		// TODO Auto-generated method stub
		Seller seller = sellerService.findByUserId(userId)
				.orElseThrow(() -> new SellerNotFoundException("해당 판매자를 찾을수 없습니다.", null, null));
		Category category = categoryService.findByCode(request.getCategoryCode())
				.orElseThrow(() -> new CategoryNotFoundException("범주를 찾을수 없습니다.", null, null));
		try {
			Instant date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getUpDate()).toInstant();
			Image fileUpload = null;
			if (file != null) {
				fileUpload = fileStorageService.portfolioFileUpload(file).toEntity();
			}
			Portfolio portfolio = portRepository.save(request.toEntity(seller, fileUpload, category, date));
			return Optional.ofNullable(portfolio);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getUpDate(), "날짜형식이 옳바르지 않습니다.");
		}
	}

	@Override
	public List<Portfolio> findAllByUserId(Long userId) {
		return portRepository.findAllByUserId(userId);
	}

	@Override
	public Optional<Portfolio> findByPortId(Long portId) {
		if (!existsByPortId(portId)) {
			throw new PortfolioNotFoundException("해당 포트폴리오를 찾을수 없습니다.", null, null);
		}
		return portRepository.findByPortId(portId);
	}

	private Boolean existsByPortId(Long portId) {
		return portRepository.existsByPortId(portId);
	}

	@Override
	public void deleteById(Long portId) {
		if (!existsByPortId(portId)) {
			throw new PortfolioNotFoundException("해당 포트폴리오를 찾을수 없습니다.", null, null);
		}
		portRepository.deleteById(portId);
	}

	@Override
	public Optional<Portfolio> updatePortfolio(@Valid PortfolioRequestDto request, Long portId, MultipartFile file) {
		// TODO Auto-generated method stub
		Portfolio portfolio = portRepository.findByPortId(portId)
				.orElseThrow(() -> new PortfolioNotFoundException("해당 포트폴리오를 찾을수 없습니다.", null, null));
		Category category = categoryService.findByCode(request.getCategoryCode())
				.orElseThrow(() -> new CategoryNotFoundException("범주를 찾을수 없습니다.", null, null));
		try {
			Instant date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getUpDate()).toInstant();
			Image fileUpload = null;
			if (file != null) {
				fileUpload = fileStorageService.portfolioFileUpload(file).toEntity();
			}
			if (portfolio.getImage() != null) {
				Image beforeImage = imageService.findByIid(portfolio.getImage().getIid().toString())
						.orElseThrow(() -> new ImageNotFoundException("사진을 찾을수 없습니다.", null, null));
				if (file != null) {
					fileStorageService.portfolioFileDelete(beforeImage);
					imageService.deleteById(beforeImage.getImageId());
				}
				else {
					fileUpload = beforeImage;
				}
			}
			portfolio.update(category, request.getTitle(), request.getDetail(), date, request.getYoutubeLink(),
					fileUpload);
			return Optional.ofNullable(portRepository.save(portfolio));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateParseException(request.getUpDate(), "날짜형식이 옳바르지 않습니다.");
		}
	}

}
