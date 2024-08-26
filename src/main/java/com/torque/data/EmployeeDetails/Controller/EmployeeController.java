package com.torque.data.EmployeeDetails.Controller;


import com.torque.data.EmployeeDetails.DTO.Dto;
import com.torque.data.EmployeeDetails.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = {"/employee"})
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = {"/{employee_id}"})
    public ResponseEntity<Dto> getEmployeeById(@PathVariable Long employee_id) {
        Optional<Dto> employeeDto = employeeService.getByEmployeeId(employee_id);
        return employeeDto.map(employeeDto1 -> ResponseEntity.ok(employeeDto1)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"/all"})
    public ResponseEntity<List<Dto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAllEmployee());
    }


    @PostMapping
    public ResponseEntity<Dto> createEmployee(@RequestBody @Valid Dto dto) {
        Dto savedEmployee = employeeService.create(dto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping(path = {"/{employee_id}"})
    public ResponseEntity<String> deleteByID(@PathVariable Long employee_id) {
       boolean isDeleted = employeeService.deleteByID(employee_id);
       if(isDeleted) return ResponseEntity.ok("Employee by id " + employee_id+ " is deleted");
       else return ResponseEntity.notFound().build();
    }

//    @DeleteMapping(path = {"/all"})
//    public String deleteAll(Dto dto) {
//        return employeeService.deleteAll(dto);
//    }


    @PutMapping(path = {"/{employee_id}"})
    public ResponseEntity<Dto> updateEmployeeByID(@PathVariable Long employee_id, @RequestBody @Valid Dto dto) {
        Dto updatedDto = employeeService.updateDataById(employee_id, dto);
        if(updatedDto != null) return ResponseEntity.ok(updatedDto);
        else return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = {"/{employee_id}"})
    public ResponseEntity<Dto> updatePatchByID(@RequestBody @Valid Map<String, Object> patchData, @PathVariable Long employee_id) {
        Dto updatedDto = employeeService.updatePatchByID(employee_id, patchData);
        if (updatedDto != null) return ResponseEntity.ok(updatedDto);
        else return ResponseEntity.notFound().build();
    }

}
