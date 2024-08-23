package com.torque.data.EmployeeDetails.Controller;


import com.torque.data.EmployeeDetails.DTO.Dto;
import com.torque.data.EmployeeDetails.Entity.EmployeeEntity;
import com.torque.data.EmployeeDetails.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/employee"})
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = {"/{employee_id}"})
    public Dto getEmployeeById(@PathVariable Long employee_id) {
            return employeeService.getByEmployeeId(employee_id);
        }

    @GetMapping(path = {"/all"})
    public List<Dto> getAllEmployees() {
        return employeeService.findAllEmployee();
    }


    @PostMapping
    public Dto createEmployee(@RequestBody Dto dto) {
        return employeeService.create(dto);
    }

}
