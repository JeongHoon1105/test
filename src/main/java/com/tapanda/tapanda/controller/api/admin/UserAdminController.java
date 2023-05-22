package com.tapanda.tapanda.controller.api.admin;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tapanda.tapanda.exception.UserNotFoundException;
import com.tapanda.tapanda.exception.UserRegisterException;
import com.tapanda.tapanda.exception.UserUpdateException;
import com.tapanda.tapanda.model.User;
import com.tapanda.tapanda.payload.ApiResponse;
import com.tapanda.tapanda.requestDto.UserAdminRequestDto;
import com.tapanda.tapanda.requestDto.UserSelfInfoRequestDto;
import com.tapanda.tapanda.requestDto.UserUpdateAdminRequestDto;
import com.tapanda.tapanda.responseDto.UserAdminResponseDto;
import com.tapanda.tapanda.service.UserService;
import com.tapanda.tapanda.validator.UserAdminValidator;
import com.tapanda.tapanda.validator.UserUpdateAdminValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/user")
@Api(value = "사용자 Rest API")
@RequiredArgsConstructor
public class UserAdminController {

	private final UserService userService;
	private final UserAdminValidator userValid;
	private final UserUpdateAdminValidator userUpValid;

	@PostMapping(value = "/register")
	@ApiOperation(value = "사용자를 등록한다")
	public ResponseEntity<?> registerUser(@ApiParam(value = "사용자 DTO") @Valid @RequestBody UserAdminRequestDto request,
			BindingResult result) {
		userValid.validate(request, result);
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
		}
		return userService.registerUserByAdmin(request).map(user -> {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "사용자 등록에 성공하였습니다."));
		}).orElseThrow(() -> new UserRegisterException(request.getName(), "사용자 등록에 실패하였습니다.", result));
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "사용자정보 호출 api")
	public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
		User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException("사용자를 찾을수 없습니다.", null, null));
		UserAdminResponseDto userDto = new UserAdminResponseDto(user);
		return ResponseEntity.ok(userDto);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "사용자 삭제")
	public ResponseEntity<?> userDelete(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return ResponseEntity.ok(new ApiResponse(true, "사용자 삭제완료"));
	}

	@PutMapping(value = "/{id}/basic")
	@ApiOperation(value = "사용자 수정")
	public ResponseEntity<?> userUpdateBasicInfo(
			@ApiParam(value = "사용자 DTO") @Valid @RequestBody UserUpdateAdminRequestDto request, @PathVariable("id") Long id,
			BindingResult result) {
		userUpValid.validate(request, result);
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
		}
		return userService.updateUserBasicInfoByAdmin(request, id).map(user -> {
			return ResponseEntity.ok(new ApiResponse(true, "사용자 수정에 성공하였습니다."));
		}).orElseThrow(() -> new UserUpdateException(request.getEmail(), "사용자 수정에 실패하였습니다.", null));
	}

	@PutMapping(value = "/{id}/self", headers = "content-type=multipart/*", consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	@ApiOperation(value = "사용자 수정")
	public ResponseEntity<?> userUpdateSelfInfo(
			@ApiParam(value = "사용자 DTO") @Valid @RequestPart(value = "request") UserSelfInfoRequestDto request,
			@PathVariable("id") Long id, @RequestPart(value = "file", required = false) MultipartFile file) {
		return userService.updateUserSelfInfo(request, id, file).map(user -> {
			return ResponseEntity.ok(new ApiResponse(true, "사용자 수정에 성공하였습니다."));
		}).orElseThrow(() -> new UserUpdateException(request.getUsername(), "사용자 수정에 실패하였습니다.", null));
	}

	@GetMapping(value = "/all")
	@ApiOperation(value = "사용자 목록 호출 api")
	public ResponseEntity<?> getUserList() {
		List<UserAdminResponseDto> userDtoList = userService.findAll().stream()
				.map(user -> new UserAdminResponseDto(user)).collect(Collectors.toList());
		return ResponseEntity.ok(userDtoList);
	}

	@GetMapping(value = "/filter")
	@ApiOperation(value = "사용자 목록 호출 api")
	public ResponseEntity<?> getUserListByRole(@RequestParam("role") String role) {
		List<UserAdminResponseDto> userDtoList = userService.findAllByRoleName(role).stream()
				.map(user -> new UserAdminResponseDto(user)).collect(Collectors.toList());
		return ResponseEntity.ok(userDtoList);
	}

	@GetMapping(value = "/search")
	@ApiOperation(value = "사용자 목록 호출 api")
	public ResponseEntity<?> getUserListBySearchWord(@RequestParam("word") String word) {
		List<UserAdminResponseDto> userDtoList = userService.findAllBySearchWord(word).stream()
				.map(user -> new UserAdminResponseDto(user)).collect(Collectors.toList());
		return ResponseEntity.ok(userDtoList);
	}
}