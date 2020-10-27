package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Reservation;
import cz.upce.vetalmael.model.dto.ReservationDto;

import java.util.List;

public interface ReservationService {

    Reservation addReservation(ReservationDto reservationDto, int idClinic, int idClient);
    Reservation editReservation(ReservationDto reservationDto, int idReservation);
    void removeReservation(int idReservation);
    List<Reservation> getAllReservations();
}
