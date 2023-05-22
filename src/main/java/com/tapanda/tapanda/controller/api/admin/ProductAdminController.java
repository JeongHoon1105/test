package com.tapanda.tapanda.controller.api.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.tapanda.tapanda.exception.ProductNotFoundException;
import com.tapanda.tapanda.exception.ProductUpdateException;
import com.tapanda.tapanda.model.Product;
import com.tapanda.tapanda.model.User;
import com.tapanda.tapanda.payload.ApiResponse;
import com.tapanda.tapanda.repository.ProductRepository;
import com.tapanda.tapanda.requestDto.PaidOptionRequestDto;
import com.tapanda.tapanda.requestDto.ProductRequestDto;
import com.tapanda.tapanda.responseDto.ProductAdminResponseDto;
import com.tapanda.tapanda.requestDto.ProductAdminRequestDto;
import com.tapanda.tapanda.service.PaidOptionService;
import com.tapanda.tapanda.service.ProductService;
import com.tapanda.tapanda.validator.ProductAdminValidator;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/product")
@Api(value = "관리자 서비스 제품 등록 API")
@RequiredArgsConstructor
public class ProductAdminController {

    private final ProductService productService;
    private final PaidOptionService paidOptionService;
    private final PaidOptionRequestDto paidOptionRequestDto;
    private final ProductRepository productRepository;
    private final ProductAdminValidator productValid;


    @PostMapping(value = "/register", headers = "content-type=multipart/*", consumes = {
                         MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    @ApiOperation(value = "서비스 등록")
    public ResponseEntity<?> registerProduct(@AuthenticationPrincipal User user,
                                             @Valid @RequestPart(value = "request") ProductAdminRequestDto request,
                                             @RequestPart(value = "file", required = false) List<MultipartFile> fileList,
                                             BindingResult result) {

        productValid.validate(request, result);
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }
        Long userId = user.getId();

        try {

            return productService.registerProduct(request, userId, fileList)
                    .map(notice -> ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ApiResponse(true, "서비스를 등록하였습니다.")))
                    .orElseThrow(() -> new ProductNotFoundException(request.getTitle(), "서비스 등록에 실패하였습니다.", null));
        } catch (Exception e) {
            // 파일 업로드 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "파일 업로드에 실패하였습니다."));
        }
    }


    @PutMapping(value = "/{product_id}")
    @ApiOperation(value = "서비스 수정")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductAdminRequestDto request,
                                          @PathVariable("product_id") Long productId) {
        return productService.updateProduct(request, productId)
                .map(product -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse(true, "공지사항을 수정하였습니다.")))
                .orElseThrow(() -> new ProductUpdateException(request.getTitle(), "공지사항 수정에 실패하였습니다.", null));
    }

    @DeleteMapping(value = "/{product_id}")
    @ApiOperation(value = "서비스 삭제")
    public ResponseEntity<?> deleteProduct(@PathVariable("product_id") Long productId) {
        productRepository.deleteById(productId);
        return ResponseEntity.ok(new ApiResponse(true, "서비스를 삭제하였습니다."));
    }

    @GetMapping(value = "/all")
    @ApiOperation(value = "서비스 목록")
    public ResponseEntity<?> getProductList() {
        List<ProductAdminResponseDto> productDtoList = productRepository.findAll().stream().map(
                product -> new ProductAdminResponseDto(product)).collect(Collectors.toList());
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping(value = "/{product_id}")
    @ApiOperation(value = "서비스 내용")
    public ResponseEntity<?> getProduct(@PathVariable("product_id") Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("해당 을 찾을 수 없습니다.", null, null));
        ProductAdminResponseDto productDto = new ProductAdminResponseDto(product);
        return ResponseEntity.ok(productDto);
    }
}