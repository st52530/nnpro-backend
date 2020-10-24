package cz.upce.vetalmael.model.dto;

import cz.upce.vetalmael.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private String email;
    private String username;
    private String fullName;
    private String password;
    private Role role;

}
