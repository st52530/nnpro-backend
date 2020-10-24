package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Reservation;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.EmployeeDto;
import cz.upce.vetalmael.model.dto.ReservationDto;
import cz.upce.vetalmael.service.EmployeeService;
import cz.upce.vetalmael.service.ReservationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/reservation")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody ReservationDto reservationDto) {
        return ResponseEntity.ok(reservationService.addReservation(reservationDto));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping
    public ResponseEntity<Reservation> editReservation(@RequestBody ReservationDto reservationDto) {
        return ResponseEntity.ok(reservationService.editReservation(reservationDto));
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
}
