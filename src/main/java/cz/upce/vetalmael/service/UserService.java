package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Reservation;
import cz.upce.vetalmael.model.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    User getUserFromAuthenticationPrincipal();
}
