package com.torque.data.EmployeeDetails.Advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private T data; // This generic type

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private ApiErrors error;

    // IF data is coming then this will work
    public ApiResponse(T data) {
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // IF error is coming then this will work
    public ApiResponse(ApiErrors error){
        this.timestamp = LocalDateTime.now();
        this.error = error;
    }

}
