package com.tapanda.tapanda.controller.api.basic;

import javax.validation.Valid;

import com.tapanda.tapanda.exception.UserUpdateException;
import com.tapanda.tapanda.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.tapanda.tapanda.exception.UserRegisterException;
import com.tapanda.tapanda.payload.ApiResponse;
import com.tapanda.tapanda.requestDto.UserRequestDto;
import com.tapanda.tapanda.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
@Api(value = "사용자 Rest API")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping(value = "/register")
	@ApiOperation(value = "사용자를 등록한다")
	public ResponseEntity<?> registerUser(@ApiParam(value = "사용자 DTO") @Valid @RequestBody UserRequestDto request,
			BindingResult result) {
		return userService.registerUser(request).map(user -> {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "사용자 등록에 성공하였습니다."));
		}).orElseThrow(() -> new UserRegisterException(request.getName(), "사용자 등록에 실패하였습니다.", result));
	}

	@GetMapping(value = "/name/{name}/check")
	@ApiOperation(value = "사용자 이름 중복 check api")
	public ResponseEntity<?> checkNameDuplication(@PathVariable("name") String name) {
		return ResponseEntity.ok(userService.checkNameDuplication(name));
	}
	
	@GetMapping(value = "/email/{email}/check")
	@ApiOperation(value = "사용자 이메일 중복 check api")
	public ResponseEntity<?> checkEmailDuplication(@PathVariable("email") String email) {
		return ResponseEntity.ok(userService.checkEmailDuplication(email));
	}

	@PutMapping(value = "/cover", headers = "content-type=multipart/*", consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	@ApiOperation(value = "사용자 배경화면를 수정한다.")
	public ResponseEntity<?> updateCover(
			@ApiParam(value = "배경 DTO") @Valid
			@AuthenticationPrincipal User user, @RequestPart(value = "file", required = false) MultipartFile file) {
		return userService.updateUserCover(user, file).map(cover -> {
			return ResponseEntity.ok(new ApiResponse(true, "사용자 배경화면 수정에 성공하였습니다."));
		}).orElseThrow(() -> new UserUpdateException(null, "사용자 배경화면 수정에 실패하였습니다.", null));
	}
}