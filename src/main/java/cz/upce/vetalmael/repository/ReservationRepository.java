package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAllByClient_idUser(int idUser);

}
