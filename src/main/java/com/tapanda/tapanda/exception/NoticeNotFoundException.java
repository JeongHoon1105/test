package com.tapanda.tapanda.exception;

public class NoticeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7963139384359440944L;

    private final String resourceName;
    private final String errorMessage;
    private final transient Object fieldValue;

    public NoticeNotFoundException(String resourceName, String errorMessage, Object fieldValue) {
        super(String.format("UserNotFoundException resourceName: [%s], errorMessage: [%s], fieldValue: [%s]",
                resourceName, errorMessage, fieldValue));
        this.resourceName = resourceName;
        this.errorMessage = errorMessage;
        this.fieldValue = fieldValue;
    }
}