package com.apiproducts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MediaFileNotFoundException extends RuntimeException {
    public MediaFileNotFoundException(String message) {
        super(message);
    }
}