package com.tapanda.tapanda.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.tapanda.tapanda.validator.NameDuplicateValidator;

@Documented
@Constraint(validatedBy = NameDuplicateValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NameDuplicate {

	String message() default "이 성명 이미 등록되어 있습니다.";
    Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}