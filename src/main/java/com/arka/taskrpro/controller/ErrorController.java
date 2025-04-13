package com.arka.taskrpro.controller;

import com.arka.taskrpro.exceptions.AuthException;
import com.arka.taskrpro.models.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDto> badCredentialsError(AuthException ex){
        log.error("Caught error ",ex);
        ErrorDto error = ErrorDto.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
