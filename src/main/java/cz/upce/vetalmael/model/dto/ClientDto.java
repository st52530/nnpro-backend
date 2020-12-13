package cz.upce.vetalmael.model.dto;

import cz.upce.vetalmael.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    public static final Role ROLE = Role.CLIENT;
    private String email;
    private String fullName;
    private String address;
    private String phoneNumber;
}
