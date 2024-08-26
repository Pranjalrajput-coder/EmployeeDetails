package com.torque.data.EmployeeDetails.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dto {

    private long id;
    private String name;
    private String email;
    private String department;
    private String gender;
    private LocalDate dateOfJoining;


}
