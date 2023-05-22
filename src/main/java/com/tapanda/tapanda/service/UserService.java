package com.tapanda.tapanda.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.tapanda.tapanda.model.User;
import com.tapanda.tapanda.requestDto.UserAdminRequestDto;
import com.tapanda.tapanda.requestDto.UserRequestDto;
import com.tapanda.tapanda.requestDto.UserSelfInfoRequestDto;
import com.tapanda.tapanda.requestDto.UserUpdateAdminRequestDto;

public interface UserService {
	
	void deleteByUid(String uid);
	
	Optional<User> registerUserByAdmin(@Valid UserAdminRequestDto request);

	Optional<User> registerUser(@Valid UserRequestDto request);
	
	Optional<User> updateUserBasicInfoByAdmin(@Valid UserUpdateAdminRequestDto request, Long id);

	UserDetails findUserByName(String name);

	Optional<User> findByUid(String uid);

	Boolean existsByUid(String uid);

	List<User> findAll();
	
	List<User> findAllByRoleName(String name);
	
	List<User> findAllBySearchWord(String word);

	Boolean checkNameDuplication(String name);

	Boolean checkEmailDuplication(String email);

	Optional<User> updateUserSelfInfo(@Valid UserSelfInfoRequestDto request, Long id, MultipartFile file);

	Optional<User> updateUserCover(User user, MultipartFile file);

	Optional<User> findById(Long id);

	void deleteById(Long id);
	
	Boolean isSeller(Long userId);
}