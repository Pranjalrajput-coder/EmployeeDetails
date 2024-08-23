package com.torque.data.EmployeeDetails.Repository;

import com.torque.data.EmployeeDetails.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {

    //get the data, delete the data, udate the data ---> Everything is handle by JpaRepository (Hibernate)

}
