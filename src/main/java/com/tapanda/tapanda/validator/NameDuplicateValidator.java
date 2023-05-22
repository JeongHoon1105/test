package com.tapanda.tapanda.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tapanda.tapanda.annotation.NameDuplicate;
import com.tapanda.tapanda.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NameDuplicateValidator implements ConstraintValidator<NameDuplicate, String> {
	
	private final UserService userService;

	@Override
	public void initialize(NameDuplicate constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return !userService.checkNameDuplication(value);
	}

}