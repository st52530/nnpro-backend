package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Reservation;
import cz.upce.vetalmael.model.dto.ReservationDto;

public interface ReservationService {

    Reservation addReservation(ReservationDto reservationDto);
    Reservation editReservation(ReservationDto reservationDto);
    void removeReservation(int idReservation);
}
