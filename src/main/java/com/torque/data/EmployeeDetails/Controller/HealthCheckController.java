package com.torque.data.EmployeeDetails.Controller;


import com.torque.data.EmployeeDetails.Advices.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/")
    public ResponseEntity<ApiResponse<String>> healthCheck(){
        return ResponseEntity.ok(new ApiResponse<>("OK"));
    }
}
