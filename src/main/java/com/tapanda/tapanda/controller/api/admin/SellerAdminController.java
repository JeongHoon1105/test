package com.tapanda.tapanda.controller.api.admin;

import javax.validation.Valid;

import com.tapanda.tapanda.exception.IndividualNotFoundException;
import com.tapanda.tapanda.exception.IndividualRegisterException;
import com.tapanda.tapanda.exception.IndividualUpdateException;
import com.tapanda.tapanda.model.Individual;
import com.tapanda.tapanda.payload.ApiResponse;
import com.tapanda.tapanda.requestDto.IndividualAdminRequestDto;
import com.tapanda.tapanda.responseDto.IndividualAdminResponseDto;
import com.tapanda.tapanda.service.IndividualService;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tapanda.tapanda.exception.CorpInfoRegisterException;
import com.tapanda.tapanda.exception.CorpInfoUpdateException;
import com.tapanda.tapanda.exception.SkillRegisterException;
import com.tapanda.tapanda.exception.UserNotFoundException;
import com.tapanda.tapanda.model.CorporationInfo;
import com.tapanda.tapanda.model.Seller;
import com.tapanda.tapanda.payload.ApiResponse;
import com.tapanda.tapanda.requestDto.CorpInfoRequestDto;
import com.tapanda.tapanda.requestDto.SkillRequestDto;
import com.tapanda.tapanda.responseDto.CorpInfoResponseDto;
import com.tapanda.tapanda.responseDto.SellerAdminResponseDto;
import com.tapanda.tapanda.service.CorpInfoService;
import com.tapanda.tapanda.service.SellerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/seller")
@Api(value = "관리자 판매자 관리 Rest API")
@RequiredArgsConstructor
public class SellerAdminController {

	private final SellerService sellerService;
	private final CorpInfoService corpInfoService;
	private final IndividualService individualService;


	@GetMapping(value = "/{user_uid}/corp_info")
	@ApiOperation(value = "프리랜서 법인 정보 호출 api")
	public ResponseEntity<?> getSellerCorpInfo(@PathVariable("user_uid") String userUid) {
		CorporationInfo corpInfo = corpInfoService.findByUserUid(userUid)
				.orElseThrow(() -> new UserNotFoundException("해당 프리랜서를 찾을 수 없습니다.", null, null));
		CorpInfoResponseDto corpInfoResponseDto = new CorpInfoResponseDto(corpInfo);
		return ResponseEntity.ok(corpInfoResponseDto);
	}
	
	@PutMapping(value = "/{user_uid}/corp_info")
	@ApiOperation(value = "프리랜서 법인 정보 수정")
	public ResponseEntity<?> updateSellerCorpInfo(@PathVariable("user_uid") String userUid,
			@ApiParam(value = "법인 DTO") @Valid @RequestBody CorpInfoRequestDto request) {
		return corpInfoService.updateCorpInfo(request, userUid).map(skill -> {
			return ResponseEntity.ok(new ApiResponse(true, "법인 수정에 성공하였습니다."));
		}).orElseThrow(() -> new CorpInfoUpdateException(request.getCompanyName(), "법인 수정에 실패하였습니다.", null));
	}

	@PostMapping(value = "/{user_uid}/corp_info/register")
	@ApiOperation(value = "프리랜서 법인 등록")
	public ResponseEntity<?> createSellerCorpInfo(@PathVariable("user_uid") String userUid,
			@ApiParam(value = "법인 DTO") @Valid @RequestBody CorpInfoRequestDto request) {
		return corpInfoService.registerCorpInfo(request, userUid).map(skill -> {
			return ResponseEntity.ok(new ApiResponse(true, "법인 등록에 성공하였습니다."));
		}).orElseThrow(() -> new CorpInfoRegisterException(request.getCompanyName(), "법인 등록에 실패하였습니다.", null));
	}
	
    @DeleteMapping(value = "/{uid}/corp_info")
    @ApiOperation(value = "프리랜서 법인  삭제 api")
    public ResponseEntity<?> SellerCorpInfoDelete(@PathVariable("corp_info_id") String uid) {
    	corpInfoService.deleteByUid(uid);
        return ResponseEntity.ok(new ApiResponse(true, "프리랜서 법인 삭제완료"));
    }

    @GetMapping(value = "/{user_id}")
    @ApiOperation(value = "판매자 정보 호출 api")
    public ResponseEntity<?> getSellerInfo(@PathVariable("user_id")Long userId) {
        Seller seller = sellerService.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 판매자를 찾을 수 없습니다.", null, null));
        SellerAdminResponseDto sellerDto = new SellerAdminResponseDto(seller);
        return ResponseEntity.ok(sellerDto);
    }

    @GetMapping(value = "/individual_info/{uid}")
    @ApiOperation(value = "출품정보 정보 호출 api")
    public ResponseEntity<?> getIndividualInfo(@PathVariable("uid")String uid) {
        Individual individual = individualService.findByUid(uid)
                .orElseThrow(() -> new IndividualNotFoundException("해당 출품정보를 찾을 수 없습니다.", null, null));
        IndividualAdminResponseDto individualDto = new IndividualAdminResponseDto(individual);
        return ResponseEntity.ok(individualDto);
    }

    @PostMapping(value = "/individual_info/{uid}/register", headers = "content-type=multipart/*", consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    @ApiOperation(value = "출품정보를 등록한다")
    public ResponseEntity<?> registerIndividual(@ApiParam(value = "출품정보 DTO") @Valid @RequestBody IndividualAdminRequestDto request,
                                                @PathVariable("uid") String uid, BindingResult result) {
        return individualService.registerIndividualByAdmin(request, uid).map(individual -> {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "출품정보 등록에 성공하였습니다."));
        }).orElseThrow(() -> new IndividualRegisterException(request.getName(), "출품정보 등록에 실패하였습니다.", result));
    }

    @DeleteMapping(value = "/individual_info/{uid}")
    @ApiOperation(value = "출품정보 삭제")
    public ResponseEntity<?> individualDelete(@PathVariable("uid") String uid) {
        individualService.deleteByUid(uid);
        return ResponseEntity.ok(new ApiResponse(true, "출품정보 삭제완료"));
    }

    @PutMapping(value = "/individual_info/{uid}")
    @ApiOperation(value = "출품정보 수정")
    public ResponseEntity<?> userUpdateIndividualInfo(@ApiParam(value = "사용자 DTO") @Valid @RequestBody IndividualAdminRequestDto request,
                                                      @PathVariable("uid") String uid) {
        return individualService.updateIndividualByAdmin(request, uid).map(individual -> {
            return ResponseEntity.ok(new ApiResponse(true, "출품정보 수정에 성공하였습니다."));
        }).orElseThrow(() -> new IndividualUpdateException(request.getName(), "출품정보 수정에 실패하였습니다.", null));
    }

}
