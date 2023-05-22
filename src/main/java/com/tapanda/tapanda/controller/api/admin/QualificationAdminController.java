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

import com.tapanda.tapanda.exception.QualRegisterException;
import com.tapanda.tapanda.exception.QualUpdateException;
import com.tapanda.tapanda.exception.UserNotFoundException;
import com.tapanda.tapanda.model.Qualification;
import com.tapanda.tapanda.payload.ApiResponse;
import com.tapanda.tapanda.requestDto.QualificationRequestDto;
import com.tapanda.tapanda.responseDto.QualificationAdminResponseDto;
import com.tapanda.tapanda.service.QualificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/seller_qualification")
@Api(value = "관리자 프리랜서 자격 관리 Rest api")
@RequiredArgsConstructor
public class QualificationAdminController {

    private final QualificationService qualService;

    @GetMapping("/{qual_id}")
    @ApiOperation(value = "프리랜서 자격 호출 api")
    public ResponseEntity<?> getQual(@PathVariable("qual_id")Long qualId) {
        Qualification qual = qualService.findByQualId(qualId)
                .orElseThrow(() -> new UserNotFoundException("해당 자격을 찾을 수 없습니다.", null, null));
        QualificationAdminResponseDto qualDto = new QualificationAdminResponseDto(qual);
        return ResponseEntity.ok(qualDto);
    }
    
	@PostMapping("/{user_id}/register")
    @ApiOperation(value = "자격를 등록한다.")
    public ResponseEntity<?> registerQualification(
            @ApiParam(value = "자격 DTO") @Valid @RequestBody QualificationRequestDto request, @PathVariable("user_id") Long userId) {
        return qualService.registerQualification(request, userId).map(skill -> {
            return ResponseEntity.ok(new ApiResponse(true, "자격 등록에 성공하였습니다."));
        }).orElseThrow(() -> new QualRegisterException(request.getName(), "자격 등록에 실패하였습니다.", null));
	}


    @DeleteMapping(value = "/{qual_id}")
    @ApiOperation(value = "프리랜서 경력 삭제 api")
    public ResponseEntity<?> qualDelete(@PathVariable("qual_id")Long qualId) {
        qualService.deleteById(qualId);
        return ResponseEntity.ok(new ApiResponse(true, "프리랜서 경력 삭제완료"));
    }
    
    @PutMapping(value = "/{qual_id}")
    @ApiOperation(value = "판매자 스킬 수정 api")
    public ResponseEntity<?> qualUpdate(@ApiParam(value = "자격 DTO") @Valid @RequestBody QualificationRequestDto request,
            @PathVariable("qual_id") Long qualId) {
        return qualService.updateQual(request, qualId).map(qual -> {
            return ResponseEntity.ok(new ApiResponse(true, "자격 수정에 성공하였습니다."));
        }).orElseThrow(() -> new QualUpdateException(request.getName(), "자격 수정에 실패하였습니다.", null));
    }


}
