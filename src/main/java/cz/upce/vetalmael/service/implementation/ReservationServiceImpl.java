package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.Reservation;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.ReservationDto;
import cz.upce.vetalmael.repository.ReservationRepository;
import cz.upce.vetalmael.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation addReservation(ReservationDto reservationDto, int idClinic, int idClient) {
        Reservation reservation = new Reservation();
        reservation.setDate(reservationDto.getDate());
        User client = new User();
        client.setIdUser(idClient);
        reservation.setClient(client);
        Clinic clinic = new Clinic();
        clinic.setIdClinic(idClinic);
        reservation.setClinic(clinic);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation editReservation(ReservationDto reservationDto, int idReservation) {
        Reservation reservation = new Reservation();
        reservation.setIdReservation(idReservation);
        reservation.setDate(reservationDto.getDate());
        return reservationRepository.save(reservation);
    }

    @Override
    public void removeReservation(int idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
