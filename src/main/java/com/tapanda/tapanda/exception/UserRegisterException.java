package com.tapanda.tapanda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
@Getter
public class UserRegisterException extends RuntimeException {

    private static final long serialVersionUID = -7963139384359440944L;

    private final String resourceName;
    private final String errorMessage;
    private final transient Object fieldValue;

    public UserRegisterException(String resourceName, String errorMessage, Object fieldValue) {
        super(String.format("UserNotFoundException resourceName: [%s], errorMessage: [%s], fieldValue: [%s]",
                resourceName, errorMessage, fieldValue));
        this.resourceName = resourceName;
        this.errorMessage = errorMessage;
        this.fieldValue = fieldValue;
    }
}