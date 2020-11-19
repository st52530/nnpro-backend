package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAllByClient_idUser(int idUser);

    int countAllByDateGreaterThanAndDateLessThan(Date start, Date end);

}
