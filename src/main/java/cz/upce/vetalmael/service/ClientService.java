package cz.upce.vetalmael.service;


import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Report;
import cz.upce.vetalmael.model.Reservation;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.ClientDto;
import cz.upce.vetalmael.model.dto.SignInDto;
import cz.upce.vetalmael.model.dto.SingUpDto;

import java.util.List;

public interface ClientService {

    User addClient(SingUpDto user);

    User editClient(ClientDto user, int idClient);

    List<Animal> getAnimals(int idUser);

    List<Report> getReports(int idUser);

    List<Reservation> getReservations(int idUser);

    User getClient(int idClient);

    List<User> getClients();

}
