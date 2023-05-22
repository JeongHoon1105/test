package com.tapanda.tapanda.service.Impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.tapanda.tapanda.exception.SpecNotFoundException;
import org.springframework.stereotype.Service;

import com.tapanda.tapanda.exception.CategoryNotFoundException;
import com.tapanda.tapanda.exception.SellerNotFoundException;
import com.tapanda.tapanda.exception.SpecRegisterException;
import com.tapanda.tapanda.model.Category;
import com.tapanda.tapanda.model.Seller;
import com.tapanda.tapanda.model.Speciality;
import com.tapanda.tapanda.repository.SpecialityRepository;
import com.tapanda.tapanda.requestDto.SpecialityRequestDto;
import com.tapanda.tapanda.service.CategoryService;
import com.tapanda.tapanda.service.SellerService;
import com.tapanda.tapanda.service.SpecialityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpecialityServiceImpl implements SpecialityService {

    private final SpecialityRepository specRepository;
    
    private final SellerService sellerService;
    
    private final CategoryService categoryService;
    
    @Override
    public List<Speciality> findAllByUserId(Long userId) {
        return specRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<Speciality> findBySpecId(Long specId) {
        if (!existsBySpecId(specId)) {
            throw new SpecNotFoundException("해당 기술을 찾을수 없습니다.", null, null);
        }
        return specRepository.findBySpecId(specId);
    }

    @Override
    public Boolean existsBySpecId(Long specId) {
        return specRepository.existsBySpecId(specId);
    }


	@Override
	public Optional<Speciality> registerSpeciality(@Valid SpecialityRequestDto request, Long userId) {
		// TODO Auto-generated method stub
		if (specRepository.countAllByUserId(userId) > 2) {
			throw new SpecRegisterException("해당 기술을 2건까지 등록 할수 없습니다.", null, null);
		}
		Seller seller = sellerService.findByUserId(userId)
				.orElseThrow(() -> new SellerNotFoundException("해당 판매자를 찾을수 없습니다.", null, null));
		Category category = categoryService.findByCode(request.getCategoryCode())
				.orElseThrow(() -> new CategoryNotFoundException("범주를 찾을수 없습니다.", null, null));
		Speciality spec = specRepository.save(request.toEntity(seller, category));
		return Optional.ofNullable(spec);
	}

    @Override
    public void deleteById(Long specId) {
        if (!existsBySpecId(specId)) {
            throw new SpecNotFoundException("해당 기술을 찾을수 없습니다.", null, null);
        }
        specRepository.deleteById(specId);
    }

    @Override
    public Optional<Speciality> updateSpeciality(SpecialityRequestDto request, Long specId) {
		Speciality spec = findBySpecId(specId).orElseThrow(() -> new SpecNotFoundException("해당 기술을 찾을수 없습니다.", null, null));
		Category category = categoryService.findByCode(request.getCategoryCode())
				.orElseThrow(() -> new CategoryNotFoundException("범주를 찾을수 없습니다.", null, null));
		spec.update(request.getDetail(), request.getMinPrice(), request.getDescription(), category);
		return Optional.ofNullable(specRepository.save(spec));
    }

    @Override
    public void deleteBySellerId(Long sellerId) {
        List<Speciality> spec = specRepository.findAllBySellerId(sellerId);
        specRepository.deleteAll(spec);
    }

}
