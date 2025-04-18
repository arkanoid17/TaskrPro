package com.arka.taskrpro.controller;

import com.arka.taskrpro.exceptions.AuthException;
import com.arka.taskrpro.exceptions.TaskException;
import com.arka.taskrpro.exceptions.TokenException;
import com.arka.taskrpro.exceptions.UserException;
import com.arka.taskrpro.models.dto.ErrorDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDto> badCredentialsError(AuthException ex){
        log.error("Caught error ",ex);
        ErrorDto error = ErrorDto.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDto> userExceptions(UserException ex){
        log.error("Caught error ",ex);
        ErrorDto error = ErrorDto.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<ErrorDto> handleTaskException(TaskException ex){
        log.error("Caught error ",ex);
        ErrorDto error = ErrorDto.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ErrorDto> handleTokenException(TaskException ex){
        log.error("Caught error ",ex);
        ErrorDto error = ErrorDto.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
