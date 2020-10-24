package cz.upce.vetalmael.model.dto;

import cz.upce.vetalmael.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoggedUser {
    private User user;
    private String token;
}
