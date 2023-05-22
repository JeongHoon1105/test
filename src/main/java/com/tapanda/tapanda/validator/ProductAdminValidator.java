package com.tapanda.tapanda.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.tapanda.tapanda.requestDto.ProductAdminRequestDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductAdminValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return ProductAdminRequestDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // TODO Auto-generated method stub
        ProductAdminRequestDto dto = (ProductAdminRequestDto) target;
    }
}