package com.torque.data.EmployeeDetails.Service;

import com.torque.data.EmployeeDetails.DTO.Dto;
import com.torque.data.EmployeeDetails.Entity.EmployeeEntity;
import com.torque.data.EmployeeDetails.Exceptions.DataNotFoundException;
import com.torque.data.EmployeeDetails.Repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    public ModelMapper mapper = new ModelMapper();

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Optional<Dto> getByEmployeeId(Long employeeId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepo.findById(employeeId);
        return employeeEntity.map(employeeEntity1 -> mapper.map(employeeEntity1, Dto.class));
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

    public Boolean deleteByID(Long employeeId) {
            isExist(employeeId);
            employeeRepo.deleteById(employeeId);
            return true;
    }

    public Dto updateDataById(Long employeeId, Dto dto) {
            isExist(employeeId);
            EmployeeEntity employeeEntity = mapper.map(dto, EmployeeEntity.class);
            employeeEntity.setId(employeeId);
            EmployeeEntity employeeEntity1 = employeeRepo.save(employeeEntity);
            return mapper.map(employeeEntity1, Dto.class);
    }

    public void isExist(Long employeeId) {
        boolean exist = employeeRepo.existsById(employeeId);
        if(!exist) throw new DataNotFoundException("Employee not found for this ID : " + employeeId);
    }

    public Dto updatePatchByID(Long employeeId,Map<String, Object> patchData) {
            isExist(employeeId);
            EmployeeEntity employeeEntity1 = employeeRepo.findById(employeeId).get();
            patchData.forEach((key, value) -> {
                Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, key);
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity1, value);
            });
            return mapper.map(employeeRepo.save(employeeEntity1), Dto.class);
    }

//    public String deleteAll(Dto dto) {
//        employeeRepo.deleteAll();
//        return "All employees are deleted";
//    }
}
