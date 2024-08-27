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
    public ResponseEntity<ApiResponse<?>> customElementException(DataNotFoundException e) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build();
        return buildError(apiErrors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> customInternalServerError(Exception e) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
        return buildError(apiErrors);
    }


    // This will apply on validation exception for Inputs in DTO and Entites
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> bindingTheErrors(MethodArgumentNotValidException e) {
        List<String> errors = e
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        // collect all the errors which can not be easily identify, so it's just map in simple format

        ApiErrors apiErrors = ApiErrors
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Validation Error")
                .subErrors(errors)
                .build();
        // return it into a ApiErrors object which reflect in PostMan API
        return buildError(apiErrors);
    }

    public ResponseEntity<ApiResponse<?>> buildError(ApiErrors apiErrors) {
        return new ResponseEntity<>(new ApiResponse<>(apiErrors), apiErrors.getStatus());
    }
}
