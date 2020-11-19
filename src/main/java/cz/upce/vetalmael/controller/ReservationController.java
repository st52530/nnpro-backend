package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Reservation;
import cz.upce.vetalmael.model.dto.ReservationDto;
import cz.upce.vetalmael.service.ReservationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.util.List;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/reservations")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/clinic/{idClinic}/client/{idClient}")
    public ResponseEntity<Reservation> addReservation(@RequestBody ReservationDto reservationDto, @PathVariable int idClinic, @PathVariable int idClient) {
        try {
            return ResponseEntity.ok(reservationService.addReservation(reservationDto, idClinic, idClient));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/{idReservation}")
    public ResponseEntity<Reservation> editReservation(@RequestBody ReservationDto reservationDto, @PathVariable int idReservation) {
        try {
            return ResponseEntity.ok(reservationService.editReservation(reservationDto, idReservation));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idReservation}")
    public ResponseEntity<?> removeReservation(@PathVariable int idReservation) {
        try {
            reservationService.removeReservation(idReservation);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAnimals(){
        return ResponseEntity.ok(reservationService.getAllReservations());
    }
}
