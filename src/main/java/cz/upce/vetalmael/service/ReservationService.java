package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Reservation;
import cz.upce.vetalmael.model.dto.ReservationDto;

public interface ReservationService {

    Reservation addReservation(ReservationDto reservationDto, int idClinic, int idClient);
    Reservation editReservation(ReservationDto reservationDto, int idReservation, int idClinic, int idClient);
    void removeReservation(int idReservation);
}
