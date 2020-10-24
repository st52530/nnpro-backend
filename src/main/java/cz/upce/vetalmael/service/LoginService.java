package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.LoggedUser;
import cz.upce.vetalmael.model.dto.SignInDto;

import java.util.Optional;

public interface LoginService {

    Optional<LoggedUser> login(SignInDto user);

    User findByUsername(String username);
}
