package com.torque.data.EmployeeDetails.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dto {

    private long id;

    @NotBlank(message = "ID SHOULD NOT BE NULL")
    private String name;

    @Email(message = "EMAIL SHOULD BE VALID")
    private String email;

    @Pattern(regexp = "^TECH|CLINICAL|CLAIMS|OPERATIONS$", message = "DEPARTMENT SHOULD BE ONE OF THE FOLLOWING: TECH - CLINICAL - CLAIMS - OPERATIONS")
    private String department;

    @Pattern(regexp = "^MALE|FEMALE|OTHERS$", message = "GENDER SHOULD BE ONE OF THE FOLLOWING: MALE - FEMALE - OTHERS")
    private String gender;

    @Max(value = 50, message = "AGE SHOULD BE LESS THAN OR EQUAL TO 50")
    @Min(value = 18, message = "AGE SHOULD BE GREATER THAN OR EQUAL TO 18")
    private Integer age;

    @PastOrPresent(message = "JOINING DATE SHOULD BE PAST OR PRESENT")
    private LocalDate dateOfJoining;

    @NotBlank(message = "ROLE SHOULD NOT BE NULL")
    private String role;

}
