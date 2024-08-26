package com.torque.data.EmployeeDetails.Controller;


import com.torque.data.EmployeeDetails.DTO.Dto;
import com.torque.data.EmployeeDetails.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @DeleteMapping(path = {"/{employee_id}"})
    public String deleteByID(@PathVariable Long employee_id) {
       return employeeService.deleteByID(employee_id);
    }

    @PutMapping(path = {"/{employee_id}"})
    public Dto updateEmployeeByID(@PathVariable Long employee_id, @RequestBody Dto dto) {
        Dto updatedDto = employeeService.updateDataById(employee_id, dto);
        return updatedDto;
    }

    @PatchMapping(path = {"/{employee_id}"})
    public Dto updatePatchByID(@RequestBody Map<String, Object> patchData, @PathVariable Long employee_id) {
        Dto updatedDto = employeeService.updatePatchByID(employee_id, patchData);
        return updatedDto;
    }

}
