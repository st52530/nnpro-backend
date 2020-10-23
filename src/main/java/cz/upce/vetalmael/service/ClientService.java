package cz.upce.vetalmael.service;


import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SignInDto;
import cz.upce.vetalmael.model.dto.SingUpDto;

public interface ClientService {

    User addUser(SingUpDto user);

}
