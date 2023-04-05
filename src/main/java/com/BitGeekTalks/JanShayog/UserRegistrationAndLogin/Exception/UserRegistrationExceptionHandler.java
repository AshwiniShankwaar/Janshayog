package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class UserRegistrationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(
                         (error) ->
                         {
                             String fieldName = ((FieldError) error).getField();
                             String errorMessage = error.getDefaultMessage();
                             errors.put(fieldName,errorMessage);
                         }
                );
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> getException(Exception ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("error", ex.toString());
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(errors);
    }
}
