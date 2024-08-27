package com.torque.data.EmployeeDetails.Advices;

import com.torque.data.EmployeeDetails.Exceptions.DataNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiErrors> customElementException(DataNotFoundException e) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)  // This will apply on validation exception for Inputsin DTO and Entites
    public ResponseEntity<ApiErrors> customInternalServerError(Exception e) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrors> bindingTheErrors(MethodArgumentNotValidException e) {
        List<String> errors = e
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiErrors apiErrors = ApiErrors
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Validation Error")
                .subErrors(errors)
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }
}
