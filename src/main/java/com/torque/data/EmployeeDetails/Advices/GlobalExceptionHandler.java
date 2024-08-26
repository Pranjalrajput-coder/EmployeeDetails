package com.torque.data.EmployeeDetails.Advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> customElementException(NoSuchElementException e) {
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseEntity<String> customMethodException(NoSuchMethodException e) {
        return new ResponseEntity<>("Method not found", HttpStatus.NOT_FOUND);
    }
}
