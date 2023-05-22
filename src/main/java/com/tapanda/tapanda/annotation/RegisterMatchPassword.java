package com.tapanda.tapanda.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.tapanda.tapanda.validator.RegisterMatchPasswordValidatorAdmin;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegisterMatchPasswordValidatorAdmin.class)
@Documented
public @interface RegisterMatchPassword {
    String message() default "비밀번호와 비밀번호확인와 내용이 다릅니다.";

    Class<?>[] groups() default {};

    boolean allowNull() default false;

    Class<? extends Payload>[] payload() default {};
}