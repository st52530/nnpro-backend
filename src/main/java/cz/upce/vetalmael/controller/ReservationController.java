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
@RequestMapping
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/clinic/{idClinic}/client/{idClient}/reservation")
    public ResponseEntity<Reservation> addReservation(@RequestBody ReservationDto reservationDto, @PathVariable int idClinic, @PathVariable int idClient) {
        return ResponseEntity.ok(reservationService.addReservation(reservationDto, idClinic, idClient));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/clinic/{idClinic}/client/{idClient}/reservation/{idReservation}")
    public ResponseEntity<Reservation> editReservation(@RequestBody ReservationDto reservationDto,@PathVariable int idClinic, @PathVariable int idClient, @PathVariable int idReservation) {
        return ResponseEntity.ok(reservationService.editReservation(reservationDto, idReservation, idClinic, idClient));
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
