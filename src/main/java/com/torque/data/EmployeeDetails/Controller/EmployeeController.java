package com.torque.data.EmployeeDetails.Controller;


import com.torque.data.EmployeeDetails.DTO.Dto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = {"/employee"})
public class EmployeeController {

    private List<Dto> employeeList = new ArrayList<>();

    @GetMapping(path = {"/{employee_id}"})
    public Dto getEmployeeById(@PathVariable Long employee_id) {
        return new Dto(employee_id, "Tom", "tom@gmail.com", "IT");
    }

    @GetMapping(path = {"/all"})
    public List<Dto> getAllEmployees() {
        return employeeList;
    }


    @PostMapping
    public Dto createEmployee(@RequestBody Dto dto) {
        employeeList.add(dto);
        return dto;
    }




//    @PreDestroy
//    public void stop(){
//        System.out.println("EmployeeController stopped");
//    }
}
