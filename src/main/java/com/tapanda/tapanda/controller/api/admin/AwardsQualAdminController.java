package com.tapanda.tapanda.controller.api.admin;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tapanda.tapanda.exception.AwardsQualUpdateException;
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

import com.tapanda.tapanda.exception.AwardsRegisterException;
import com.tapanda.tapanda.exception.SpecRegisterException;
import com.tapanda.tapanda.exception.UserNotFoundException;
import com.tapanda.tapanda.model.Awards;
import com.tapanda.tapanda.payload.ApiResponse;
import com.tapanda.tapanda.requestDto.AwardsRequestDto;
import com.tapanda.tapanda.requestDto.SpecialityRequestDto;
import com.tapanda.tapanda.responseDto.AwardsQualAdminResponseDto;
import com.tapanda.tapanda.service.AwardsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/seller_a_q")
@Api(value = "관리자 판매자 수상경력 관리 Rest api")
@RequiredArgsConstructor
public class AwardsQualAdminController {

    private final AwardsService awardsService;

    @GetMapping("/{awards_qual_id}")
    @ApiOperation(value = "판매자 수상경력 호출 api")
    public ResponseEntity<?> getAwardsQual(@PathVariable("awards_qual_id")Long awardsQualId) {
        Awards awards = awardsService.findByAwardsQualId(awardsQualId)
                .orElseThrow(() -> new UserNotFoundException("해당 수상경력을 찾을 수 없습니다.", null, null));
        AwardsQualAdminResponseDto awardsQualDto = new AwardsQualAdminResponseDto(awards);
        return ResponseEntity.ok(awardsQualDto);
    }

	@PostMapping("/{user_id}/register")
    @ApiOperation(value = "수상경력 등록한다.")
    public ResponseEntity<?> registerSpeciality(
            @ApiParam(value = "수상경력 DTO") @Valid @RequestBody AwardsRequestDto request, @PathVariable("user_id") Long userId) {
        return awardsService.registerAwards(request, userId).map(skill -> {
            return ResponseEntity.ok(new ApiResponse(true, "수상경력 등록에 성공하였습니다."));
        }).orElseThrow(() -> new AwardsRegisterException(request.getDate(), "수상경력 등록에 실패하였습니다.", null));

    }

    @DeleteMapping(value = "/{awards_qual_id}")
    @ApiOperation(value = "판매자 수상경력 삭제 api")
    public ResponseEntity<?> awardsDelete(@PathVariable("awards_qual_id")Long awardsQualId) {
        awardsService.deleteById(awardsQualId);
        return ResponseEntity.ok(new ApiResponse(true, "프리랜서 수상경력 삭제완료"));
    }

    @PutMapping(value = "/{awards_qual_id}")
    @ApiOperation(value = "판매자 수상경력 수정 api")
    public ResponseEntity<?> awardsUpdate(@ApiParam(value = "수상경력 DTO") @Valid @RequestBody AwardsRequestDto request,
            @PathVariable("awards_qual_id") Long awardsQualId) {
        return awardsService.updateAwardsQual(request, awardsQualId).map(awards -> {
            return ResponseEntity.ok(new ApiResponse(true, "수상경력 수정에 성공하였습니다."));
        }).orElseThrow(() -> new AwardsQualUpdateException(request.getTitle(), "수상경력 수정에 실패하였습니다.", null));
    }
}
