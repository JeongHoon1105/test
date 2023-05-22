package com.tapanda.tapanda.controller.api.admin;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tapanda.tapanda.exception.PortfolioUpdateException;
import com.tapanda.tapanda.exception.SpecRegisterException;
import com.tapanda.tapanda.exception.UserNotFoundException;
import com.tapanda.tapanda.model.Portfolio;
import com.tapanda.tapanda.payload.ApiResponse;
import com.tapanda.tapanda.requestDto.PortfolioRequestDto;
import com.tapanda.tapanda.responseDto.PortfolioAdminResponseDto;
import com.tapanda.tapanda.service.PortfolioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/seller_portfolio")
@Api(value = "관리자 프리랜서 포트폴리오 관리 Rest api")
@RequiredArgsConstructor
public class PortfolioAdminController {

	private final PortfolioService portService;

	@PostMapping(value = "/{user_id}/register", headers = "content-type=multipart/*", consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	@ApiOperation(value = "포트폴리오를 등록한다.")
	public ResponseEntity<?> registerPortpolio(
			@ApiParam(value = "스킬 DTO") @Valid @RequestPart(value = "request") PortfolioRequestDto request,
			@PathVariable("user_id") Long userId, @RequestPart(value = "file", required = false) MultipartFile file) {
		return portService.registerPortfolio(request, userId, file).map(skill -> {
			return ResponseEntity.ok(new ApiResponse(true, "포트폴리오 등록에 성공하였습니다."));
		}).orElseThrow(() -> new SpecRegisterException(request.getDetail(), "포트폴리오 등록에 실패하였습니다.", null));

	}

    @GetMapping("/{port_id}")
    @ApiOperation(value = "프리랜서 포트폴리오 조회 api")
    public ResponseEntity<?> getPortpolio(@PathVariable("port_id")Long port_id) {
        Portfolio port = portService.findByPortId(port_id)
                .orElseThrow(() -> new UserNotFoundException("해당 포트폴리오를 찾을 수 없습니다.", null, null));
        PortfolioAdminResponseDto portDto = new PortfolioAdminResponseDto(port);
        return ResponseEntity.ok(portDto);
    }

    @DeleteMapping(value = "/{port_id}")
    @ApiOperation(value = "프리랜서 포트폴리오 삭제 api")
    public ResponseEntity<?> qualDelete(@PathVariable("port_id")Long port_id) {
        portService.deleteById(port_id);
        return ResponseEntity.ok(new ApiResponse(true, "프리랜서 포트폴리오 삭제완료"));
    }
    
	@PutMapping(value = "/{port_id}", headers = "content-type=multipart/*", consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	@ApiOperation(value = "포트폴리오를 수정한다.")
	public ResponseEntity<?> updatePortpolio(
			@ApiParam(value = "포트폴리오 DTO") @Valid @RequestPart(value = "request") PortfolioRequestDto request,
			@PathVariable("port_id") Long portId, @RequestPart(value = "file", required = false) MultipartFile file) {
		return portService.updatePortfolio(request, portId, file).map(skill -> {
			return ResponseEntity.ok(new ApiResponse(true, "포트폴리오 수정에 성공하였습니다."));
		}).orElseThrow(() -> new PortfolioUpdateException(request.getDetail(), "포트폴리오 수정에 실패하였습니다.", null));

	}

}
