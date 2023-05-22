package com.tapanda.tapanda.controller.api.admin;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tapanda.tapanda.exception.CompanyQUalRegisterException;
import com.tapanda.tapanda.exception.CompanyQUalUpdateException;
import com.tapanda.tapanda.exception.QualUpdateException;
import com.tapanda.tapanda.exception.UserNotFoundException;
import com.tapanda.tapanda.model.Company;
import com.tapanda.tapanda.payload.ApiResponse;
import com.tapanda.tapanda.requestDto.CompanyQualRequestDto;
import com.tapanda.tapanda.requestDto.QualificationRequestDto;
import com.tapanda.tapanda.responseDto.ComQualAdminResponseDto;
import com.tapanda.tapanda.service.CompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/seller_c_q")
@Api(value = "관리자 프리랜서 직장경력 관리 Rest api")
@RequiredArgsConstructor
public class CompanyQualAdminController {

    private final CompanyService companyService;

    @GetMapping("/{com_qual_id}")
    @ApiOperation(value = "프리랜서 직장경력 호출 api")
    public ResponseEntity<?> getCompanyQual(@PathVariable("com_qual_id")Long comQualId) {
        Company company = companyService.findByComQualId(comQualId)
                .orElseThrow(() -> new UserNotFoundException("해당 직장경력을 찾을 수 없습니다.", null, null));
        ComQualAdminResponseDto companyQualDto = new ComQualAdminResponseDto(company);
        return ResponseEntity.ok(companyQualDto);
    }
    
	@PostMapping("/{user_id}/register")
    @ApiOperation(value = "자격를 등록한다.")
    public ResponseEntity<?> registerCompanyQual(
            @ApiParam(value = "자격 DTO") @Valid @RequestBody CompanyQualRequestDto request, @PathVariable("user_id") Long userId) {
        return companyService.registerCompanyQual(request, userId).map(skill -> {
            return ResponseEntity.ok(new ApiResponse(true, "직장경력 등록에 성공하였습니다."));
        }).orElseThrow(() -> new CompanyQUalRegisterException(request.getName(), "직장경력 등록에 실패하였습니다.", null));

    }

    @DeleteMapping(value = "/{com_qual_id}")
    @ApiOperation(value = "프리랜서 직장경력 삭제 api")
    public ResponseEntity<?> comDelete(@PathVariable("com_qual_id")Long comQualId) {
        companyService.deleteById(comQualId);
        return ResponseEntity.ok(new ApiResponse(true, "프리랜서 직장경력 삭제완료"));
    }

    @PutMapping(value = "/{com_qual_id}")
    @ApiOperation(value = "판매자 스킬 수정 api")
    public ResponseEntity<?> comUpdate(@ApiParam(value = "프리랜서 직장경력 DTO") @Valid @RequestBody CompanyQualRequestDto request,
            @PathVariable("com_qual_id") Long comQualId) {
        return companyService.updateCompanyQual(request, comQualId).map(qual -> {
            return ResponseEntity.ok(new ApiResponse(true, "직장경력 수정에 성공하였습니다."));
        }).orElseThrow(() -> new CompanyQUalUpdateException(request.getName(), "직장경력 수정에 실패하였습니다.", null));
    }
}
