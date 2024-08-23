package com.torque.data.EmployeeDetails.Service;

import com.torque.data.EmployeeDetails.DTO.Dto;
import com.torque.data.EmployeeDetails.Entity.EmployeeEntity;
import com.torque.data.EmployeeDetails.Repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    public ModelMapper mapper = new ModelMapper();

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Dto getByEmployeeId(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepo.findById(employeeId).orElse(null);
        return mapper.map(employeeEntity, Dto.class);
    }

    public List<Dto> findAllEmployee() {
        List<EmployeeEntity> employeeEntity = employeeRepo.findAll();
        return mapper.map(employeeEntity, List.class);
    }

    public Dto create(Dto dto) {
        EmployeeEntity toSaveEntity = mapper.map(dto, EmployeeEntity.class);
        EmployeeEntity employeeEntity = employeeRepo.save(toSaveEntity);
        return mapper.map(employeeEntity, Dto.class);
    }
}
