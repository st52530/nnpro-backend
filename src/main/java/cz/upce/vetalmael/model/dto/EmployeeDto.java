package cz.upce.vetalmael.model.dto;

import cz.upce.vetalmael.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private int idEmployee;
    private String email;
    private String fullName;
    private String password;
    private Role role;
    private ClinicDto clinicDto;
}