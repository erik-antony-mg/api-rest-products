package com.apiproducts.entities.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    private Integer code;
    private String message;
}

