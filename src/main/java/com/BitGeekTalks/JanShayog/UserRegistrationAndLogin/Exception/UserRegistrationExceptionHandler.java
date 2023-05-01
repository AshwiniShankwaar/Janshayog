package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
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
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String,String>> handleNoSuchElementException(NoSuchElementException ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("error", "No element found with the specified criteria.");
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String,String>> handleIOException(IOException ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("error", "An I/O error occurred while processing the request.");
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String,String>> handleNullPointerException(NullPointerException ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("error", "A null value was encountered where it was not expected.");
        ex.printStackTrace();
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
