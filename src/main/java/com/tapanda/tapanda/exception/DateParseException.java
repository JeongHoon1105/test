package com.tapanda.tapanda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class DateParseException extends RuntimeException {
    private static final long serialVersionUID = -5489225859126123280L;

    private final String date;
    private final String message;

    public DateParseException(String date, String message) {
        super(String.format("DateParseException [%s], date: [%s]", date, message));
        this.date = date;
        this.message = message;
    }
}