package com.tapanda.tapanda.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tapanda.tapanda.annotation.EmailDuplicate;
import com.tapanda.tapanda.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailDuplicateValidator implements ConstraintValidator<EmailDuplicate, String> {
	
	private final UserService userService;

	@Override
	public void initialize(EmailDuplicate constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return !userService.checkEmailDuplication(value);
	}

}