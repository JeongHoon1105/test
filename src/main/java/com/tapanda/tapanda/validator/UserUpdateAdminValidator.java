package com.tapanda.tapanda.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tapanda.tapanda.requestDto.UserUpdateAdminRequestDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUpdateAdminValidator  implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserUpdateAdminRequestDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserUpdateAdminRequestDto dto = (UserUpdateAdminRequestDto) target;
	} 
	
}