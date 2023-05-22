package com.tapanda.tapanda.service.Impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tapanda.tapanda.exception.CategoryNotFoundException;
import com.tapanda.tapanda.exception.ResourceExistsException;
import com.tapanda.tapanda.model.Category;
import com.tapanda.tapanda.repository.CategoryRepository;
import com.tapanda.tapanda.requestDto.CategoryRequestDto;
import com.tapanda.tapanda.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> findByCode(String code) {
		// TODO Auto-generated method stub
		return categoryRepository.findByCode(code);
	}

	@Override
	public Boolean existsByCode(String code) {
		// TODO Auto-generated method stub
		return categoryRepository.existsByCode(code);
	}

	private Boolean isExistsParentCode(String parentCode) {
		// TODO Auto-generated method stub
		return parentCode == null ? false : !categoryRepository.existsByCode(parentCode);
	}

	@Override
    public Optional<Category> registerCategory(@Valid CategoryRequestDto request) {
        // TODO Auto-generated method stub
        request.setParentCode(!StringUtils.hasText(request.getParentCode()) ? null : request.getParentCode());
        String parentCode = request.getParentCode();
        if (isExistsParentCode(parentCode)) {
            throw new ResourceExistsException("parentCode", "parentCode", parentCode);
        }
        Long seq = categoryRepository.findFirstByParentCodeOrderBySeqDesc(parentCode).map(Category::getSeq).orElse(0L);
        Category category = categoryRepository.save(request.toEntity(seq + 1));
        return Optional.ofNullable(category);
    }
	
    @Override
    public void deleteByCode(String code) {
        // TODO Auto-generated method stub
        Category category = findByCode(code)
                .orElseThrow(() -> new CategoryNotFoundException("카테고리를 찾을수 없습니다.", null, null));
        categoryRepository.delete(category);
    }

    @Override
    public Optional<Category> updateCategory(String code, @Valid CategoryRequestDto request) {
        // TODO Auto-generated method stub
        Category category = findByCode(code)
                .orElseThrow(() -> new CategoryNotFoundException("카테고리를 찾을수 없습니다.", null, null));
        category.update(request.getName(), request.getCode(), request.getParentCode());
        return Optional.ofNullable(categoryRepository.save(category));
    }

    @Override
    public Long findIdByCategoryCode(String categoryCode) {
        Long categoryId = categoryRepository.findIdByCategoryCode(categoryCode);
        if (categoryId == null) {
            throw new CategoryNotFoundException("해당 카테고리 ID를 찾을 수 없습니다.", null, null);
        }
        return categoryId;
    }
}