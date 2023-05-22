package com.tapanda.tapanda.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tapanda.tapanda.annotation.RegisterMatchPassword;
import com.tapanda.tapanda.requestDto.UserAdminRequestDto;

public class RegisterMatchPasswordValidatorAdmin
		implements ConstraintValidator<RegisterMatchPassword, UserAdminRequestDto> {

	private Boolean allowNull;

	@Override
	public void initialize(RegisterMatchPassword constraintAnnotation) {
// TODO Auto-generated method stub
		allowNull = constraintAnnotation.allowNull();
	}

	@Override
	public boolean isValid(UserAdminRequestDto dto, ConstraintValidatorContext context) {
// TODO Auto-generated method stub
		String password = dto.getPassword();
		String confirmPassword = dto.getPasswordCheck();
		if (allowNull) {
			return null == password && null == confirmPassword;
		}
		return password.equals(confirmPassword);
	}

}