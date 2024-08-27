package com.torque.data.EmployeeDetails.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class EmployeeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "last_onwards")// auto_increment of IDs
    @SequenceGenerator(name = "last_onwards", sequenceName = "employee_id_seq", allocationSize = 1) // this will generate the data in sequence from where last data stored
    private long id;
    private String name;
    private String email;
    private String department;
    private String gender;
    private LocalDate dateOfJoining;
    private Integer age;
    private String role;
}
