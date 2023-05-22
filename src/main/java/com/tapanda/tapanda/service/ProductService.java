package com.tapanda.tapanda.service;

import java.util.List;
import java.util.Optional;

import com.tapanda.tapanda.model.Product;
import com.tapanda.tapanda.requestDto.ProductAdminRequestDto;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

public interface ProductService {

    Optional<Product> registerProduct(@Valid ProductAdminRequestDto request, Long userId, List<MultipartFile> fileList);

    Optional<Product> updateProduct(@Valid ProductAdminRequestDto request, Long productId);
}