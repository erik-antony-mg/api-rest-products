package com.apiproducts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFountException extends RuntimeException   {
    public ProductNotFountException(String message) {
        super(message);
    }
}
