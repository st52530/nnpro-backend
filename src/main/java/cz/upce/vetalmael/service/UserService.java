package cz.upce.vetalmael.service;


import cz.upce.vetalmael.model.User;

public interface UserService {

    User signUp(User user);

    User findByUsername(String username);
}
