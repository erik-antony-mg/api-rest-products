package com.apiproducts.configuration;

import com.apiproducts.entities.dto.ErrorDto;
import com.apiproducts.exceptions.MediaFileNotFoundException;
import com.apiproducts.exceptions.ProductNotFountException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductNotFountException.class)
    public ResponseEntity<ErrorDto> productNotFountException(ProductNotFountException ex){
        ErrorDto errorDto=ErrorDto.builder().code(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build();
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDto> badRequestException(BadRequestException ex){
        ErrorDto errorDto=ErrorDto.builder().code(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).build();
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> errorMap= new HashMap<>();
        //forma1 coge todos los errores
//        ex.getBindingResult().getAllErrors().forEach(
//                error->{
//            String errorField=((FieldError)error).getField();
//            String errorMesg= error.getDefaultMessage();
//           errorMap.put(errorField,errorMesg);
//                     }
//        );
        //forma2 solo obtiene los errores de campo
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorMap.put(error.getField(),error.getDefaultMessage()));
        return errorMap;
    }

    @ExceptionHandler(MediaFileNotFoundException.class)
    public ResponseEntity<ErrorDto> mediaFileNotFoundException(MediaFileNotFoundException ex){
        ErrorDto errorDto=ErrorDto.builder().code(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build();
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
