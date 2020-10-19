package cz.upce.vetalmael.service;


import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SignUpDto;

public interface UserService {

    String login(SignUpDto user);

    User signUp(SignUpDto user);

    User findByUsername(String username);
}
