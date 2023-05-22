package com.tapanda.tapanda.controller.api.basic;

import com.tapanda.tapanda.exception.UserNotFoundException;
import com.tapanda.tapanda.model.*;
import com.tapanda.tapanda.responseDto.*;
import com.tapanda.tapanda.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seller")
@Api(value = "판매자 Rest api")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;
    private final SkillService skillService;
    private final SpecialityService specService;
    private final PortfolioService portService;
    private final QualificationService qualService;
    private final AwardsService awardsService;
    private final CompanyService companyService;
    
    @GetMapping("/seller_a_q/{user_id}/all")
    @ApiOperation(value = "판매자 수상경력 List 호출 api")
    public ResponseEntity<?> getAwardsQualList(@PathVariable("user_id")Long userId) {
        List<AwardsQualAdminResponseDto> awardsQualDtoList = awardsService.findAllByUserId(userId).stream()
                .map(awards -> new AwardsQualAdminResponseDto(awards)).collect(Collectors.toList());
        return ResponseEntity.ok(awardsQualDtoList);
    }
    

    @GetMapping("/seller_c_q/{user_id}/all")
    @ApiOperation(value = "프리랜서 직장경력 List 호출 api")
    public ResponseEntity<?> getComapanyQualList(@PathVariable("user_id")Long userId) {
        List<ComQualAdminResponseDto> companyQualDtoList = companyService.findAllByUserId(userId).stream()
                .map(company -> new ComQualAdminResponseDto(company)).collect(Collectors.toList());
        return ResponseEntity.ok(companyQualDtoList);
    }
    
    @GetMapping("/portfolio/{user_id}/all")
    @ApiOperation(value = "프리랜서 포트폴리오 List 조회 api")
    public ResponseEntity<?> getPortfolioList(@PathVariable("user_id")Long userId) {
        List<PortfolioAdminResponseDto> portDtoList = portService.findAllByUserId(userId).stream()
                .map(port -> new PortfolioAdminResponseDto(port)).collect(Collectors.toList());
        return ResponseEntity.ok(portDtoList);
    }
    
    @GetMapping("/qualification/{user_id}/all")
    @ApiOperation(value = "프리랜서 자격 List 호출 api")
    public ResponseEntity<?> getQualList(@PathVariable("user_id") Long userId) {
        List<QualificationAdminResponseDto> qualDtoList = qualService.findAllByUserId(userId).stream()
                .map(qual -> new QualificationAdminResponseDto(qual)).collect(Collectors.toList());
        return ResponseEntity.ok(qualDtoList);
    }
    
	@GetMapping("/skill/{user_id}/all")
	@ApiOperation(value = "판매자 스킬 List 호출 api")
	public ResponseEntity<?> getSkillList(@PathVariable("user_id") Long userId) {
		List<SkillAdminResponseDto> skillDtoList = skillService.findAllByUserId(userId).stream()
				.map(skill -> new SkillAdminResponseDto(skill)).collect(Collectors.toList());
		return ResponseEntity.ok(skillDtoList);
	}
	

    @GetMapping("/speciality/{user_id}/all")
    @ApiOperation(value = "프리랜서 기술 List 호출 api")
    public ResponseEntity<?> getSpecList(@PathVariable("user_id")Long userId) {
        List<SpecialityResponseDto> specDtoList = specService.findAllByUserId(userId).stream()
                .map(spec -> new SpecialityResponseDto(spec)).collect(Collectors.toList());
        return ResponseEntity.ok(specDtoList);
    }


    @GetMapping(value = "/{user_id}")
    @ApiOperation(value = "판매자 정보 호출 api")
    public ResponseEntity<?> getSellerInfo(@PathVariable("user_id")Long userId) {
        Seller seller = sellerService.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 판매자를 찾을 수 없습니다.", null, null));
        SellerResponseDto sellerDto = new SellerResponseDto(seller);
        return ResponseEntity.ok(sellerDto);
    }

}
