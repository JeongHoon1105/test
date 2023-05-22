package com.tapanda.tapanda.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.tapanda.tapanda.requestDto.UserAdminRequestDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserAdminValidator  implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserAdminRequestDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserAdminRequestDto dto = (UserAdminRequestDto) target;
	} 
	
}