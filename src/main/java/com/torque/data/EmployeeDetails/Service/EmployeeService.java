package com.torque.data.EmployeeDetails.Service;

import com.torque.data.EmployeeDetails.DTO.Dto;
import com.torque.data.EmployeeDetails.Entity.EmployeeEntity;
import com.torque.data.EmployeeDetails.Repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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

    public String deleteByID(Long employeeId) {
        employeeRepo.deleteById(employeeId);
        return "Employee by id " + employeeId+ " is deleted";
    }

    public Dto updateDataById(Long employeeId, Dto dto) {
            if(isExist(employeeId)) {
            EmployeeEntity employeeEntity = mapper.map(dto, EmployeeEntity.class);
            employeeEntity.setId(employeeId);
            EmployeeEntity employeeEntity1 = employeeRepo.save(employeeEntity);
            return mapper.map(employeeEntity1, Dto.class);
        } else{
            return null;
        }
    }

    public boolean isExist(Long employeeId) {
        if(employeeRepo.existsById(employeeId)) return true;
        else return false;
    }

    public Dto updatePatchByID(Long employeeId,Map<String, Object> patchData) {
        if (isExist(employeeId)) {
            EmployeeEntity employeeEntity1 = employeeRepo.findById(employeeId).get();
            patchData.forEach((key, value) -> {
                Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, key);
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity1, value);
            });
            return mapper.map(employeeRepo.save(employeeEntity1), Dto.class);
        } else return null;
    }

//    public String deleteAll(Dto dto) {
//        employeeRepo.deleteAll();
//        return "All employees are deleted";
//    }
}
