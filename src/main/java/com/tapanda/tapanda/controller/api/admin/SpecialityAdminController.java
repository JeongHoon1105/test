package com.tapanda.tapanda.controller.api.admin;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tapanda.tapanda.exception.SpecUpdateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tapanda.tapanda.payload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tapanda.tapanda.exception.SpecRegisterException;
import com.tapanda.tapanda.exception.UserNotFoundException;
import com.tapanda.tapanda.model.Speciality;
import com.tapanda.tapanda.payload.ApiResponse;
import com.tapanda.tapanda.requestDto.SpecialityRequestDto;
import com.tapanda.tapanda.responseDto.SpecialityResponseDto;
import com.tapanda.tapanda.service.SpecialityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/seller_spec")
@Api(value = "관리자 프리랜서 기술 관리 Rest api")
@RequiredArgsConstructor
public class SpecialityAdminController {

    private final SpecialityService specService;

    @GetMapping("/{spec_id}")
    @ApiOperation(value = "프리랜서 기술 호출 api")
    public ResponseEntity<?> getSpeciality(@PathVariable("spec_id")Long specId) {
        Speciality spec = specService.findBySpecId(specId)
                .orElseThrow(() -> new UserNotFoundException("해당 기술을 찾을 수 없습니다.", null, null));
        SpecialityResponseDto specDto = new SpecialityResponseDto(spec);
        return ResponseEntity.ok(specDto);
    }
    
	@PostMapping("/{user_id}/register")
    @ApiOperation(value = "기술을 등록한다.")
    public ResponseEntity<?> registerSpeciality(
            @ApiParam(value = "스킬 DTO") @Valid @RequestBody SpecialityRequestDto request, @PathVariable("user_id") Long userId) {
        return specService.registerSpeciality(request, userId).map(skill -> {
            return ResponseEntity.ok(new ApiResponse(true, "기술 등록에 성공하였습니다."));
        }).orElseThrow(() -> new SpecRegisterException(request.getDetail(), "기술 등록에 실패하였습니다.", null));
    }

    @PutMapping(value = "/{seller_spec_id}")
    @ApiOperation(value = "판매자 기술 수정 api")
    public ResponseEntity<?> userUpdate(@ApiParam(value = "기술 DTO") @Valid @RequestBody SpecialityRequestDto request,
            @PathVariable("seller_spec_id") Long specId) {
        return specService.updateSpeciality(request, specId).map(spec -> {
            return ResponseEntity.ok(new ApiResponse(true, "기술 수정에 성공하였습니다."));
        }).orElseThrow(() -> new SpecUpdateException(request.getDetail(), "기술 수정에 실패하였습니다.", null));
    }

    @DeleteMapping(value = "/{spec_id}")
    @ApiOperation(value = "프리랜서 기술 삭제 api")
    public ResponseEntity<?> specDelete(@PathVariable("spec_id")Long specId) {
        specService.deleteById(specId);
        return ResponseEntity.ok(new ApiResponse(true, "프리랜서 기술 삭제완료"));
    }


}
