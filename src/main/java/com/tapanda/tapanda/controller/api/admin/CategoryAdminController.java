package com.tapanda.tapanda.controller.api.admin;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tapanda.tapanda.exception.CategoryRegisterException;
import com.tapanda.tapanda.exception.CategoryUpdateException;
import com.tapanda.tapanda.payload.ApiResponse;
import com.tapanda.tapanda.requestDto.CategoryRequestDto;
import com.tapanda.tapanda.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/category")
@Api(value = "카테고리 Rest API")
@RequiredArgsConstructor
public class CategoryAdminController {
	
	private final CategoryService categoryService;
	
    @PostMapping("/register")
    @ApiOperation(value = "카테고리를 등록한다.")
    public ResponseEntity<?> registerCategory(
            @ApiParam(value = "카테고리 DTO") @Valid @RequestBody CategoryRequestDto request) {
        return categoryService.registerCategory(request).map(category -> {
            return ResponseEntity.ok(new ApiResponse(true, "카테고리 등록에 성공하였습니다."));
        }).orElseThrow(() -> new CategoryRegisterException(request.getName(), "카테고리 등록에 실패하였습니다.", null));

    }
    
    @DeleteMapping("/{code}")
    @ApiOperation(value = "카테고리를 삭제한다.")
    public ResponseEntity<?> categoryDelete(@PathVariable("code") String code) {
        categoryService.deleteByCode(code);
        return ResponseEntity.ok(new ApiResponse(true, "카테고리 삭제완료"));
    }

    @PutMapping(value = "/{code}")
    @ApiOperation(value = "카테고리 수정")
    public ResponseEntity<?> categoryUpdate(
            @ApiParam(value = "카테고리 DTO") @Valid @RequestBody CategoryRequestDto request,
            @PathVariable("code") String code) {
        return categoryService.updateCategory(code, request).map(c -> {
            return ResponseEntity.ok(new ApiResponse(true, "카테고리 수정에 성공하였습니다."));
        }).orElseThrow(() -> new CategoryUpdateException(request.getName(), "카테고리 수정에 실패하였습니다.", code));
    }
}