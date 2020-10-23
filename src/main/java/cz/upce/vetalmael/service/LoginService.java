package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SignInDto;

public interface LoginService {

    String login(SignInDto user);

    User findByUsername(String username);
}
