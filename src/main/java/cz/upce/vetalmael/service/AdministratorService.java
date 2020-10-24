package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SingUpDto;

public interface AdministratorService {
    User addAdministrator(SingUpDto singUpDto);
}
