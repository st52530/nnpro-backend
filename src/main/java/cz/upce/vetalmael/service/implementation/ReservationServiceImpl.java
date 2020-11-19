package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.Reservation;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.ReservationDto;
import cz.upce.vetalmael.repository.ReservationRepository;
import cz.upce.vetalmael.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation addReservation(ReservationDto reservationDto, int idClinic, int idClient) throws Exception {
        Reservation reservation = new Reservation();
        reservation.setDate(reservationDto.getDate());
        checkDate(reservation);
        User client = entityManager.getReference(User.class, idClient);
        client.setIdUser(idClient);
        reservation.setClient(client);
        Clinic clinic = entityManager.getReference(Clinic.class, idClinic);
        clinic.setIdClinic(idClinic);
        reservation.setClinic(clinic);
        return reservationRepository.save(reservation);
    }

    private void checkDate(Reservation reservation) throws Exception {
        long tenMinutes = 600000;
        Date start = new Date(reservation.getDate().getTime()-tenMinutes);
        Date end = new Date(reservation.getDate().getTime()+tenMinutes);
        int count = reservationRepository.countAllByDateGreaterThanAndDateLessThan(start, end);
        if(count > 0){
            throw new Exception("Time already booked");
        }
    }

    @Override
    public Reservation editReservation(ReservationDto reservationDto, int idReservation) throws Exception {
        Reservation reservation = reservationRepository.getOne(idReservation);
        reservation.setDate(reservationDto.getDate());
        checkDate(reservation);
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
