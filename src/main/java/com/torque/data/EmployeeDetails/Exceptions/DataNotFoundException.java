package com.torque.data.EmployeeDetails.Exceptions;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String message) {
        super(message);
    }
}
