package com.torque.data.EmployeeDetails.Advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiErrors {

    private HttpStatus status;
    private String message;
    private List<String> subErrors;

}
