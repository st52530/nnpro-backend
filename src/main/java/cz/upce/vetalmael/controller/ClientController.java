package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.*;
import cz.upce.vetalmael.model.dto.ClientDto;
import cz.upce.vetalmael.model.dto.SingUpDto;
import cz.upce.vetalmael.service.ClientService;
import cz.upce.vetalmael.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;


@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/sign-up")
    //@SecurityRequirement(name = SWAGGER_AUTH_KEY)
    public ResponseEntity<User> signUp(@RequestBody SingUpDto user) {
        return ResponseEntity.ok(clientService.addClient(user));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/{idClient}")
    @SecurityRequirement(name = SWAGGER_AUTH_KEY)
    public ResponseEntity<User> editClient(@RequestBody ClientDto clientDto, @PathVariable int idClient) {
        return ResponseEntity.ok(clientService.editClient(clientDto, idClient));
    }

    @GetMapping("/animals")
    @SecurityRequirement(name = SWAGGER_AUTH_KEY)
    public ResponseEntity<List<Animal>> getAnimals(){
        User user = userService.getUserFromAuthenticationPrincipal();
        return ResponseEntity.ok(clientService.getAnimals(user.getIdUser()));
    }

    @GetMapping("/{idClient}/animals")
    @SecurityRequirement(name = SWAGGER_AUTH_KEY)
    public ResponseEntity<List<Animal>> getClientsAnimals(@PathVariable int idClient){
        return ResponseEntity.ok(clientService.getAnimals(idClient));
    }

    @GetMapping("/{idClient}/reports")
    @SecurityRequirement(name = SWAGGER_AUTH_KEY)
    public ResponseEntity<List<Report>> getClientReports(@PathVariable int idClient){
        return ResponseEntity.ok(clientService.getReports(idClient));
    }

    @GetMapping("/{idClient}/reservations")
    @SecurityRequirement(name = SWAGGER_AUTH_KEY)
    public ResponseEntity<List<Reservation>> getReservations(@PathVariable int idClient){
        return ResponseEntity.ok(clientService.getReservations(idClient));
    }


    @GetMapping("/reservations")
    @SecurityRequirement(name = SWAGGER_AUTH_KEY)
    public ResponseEntity<List<Reservation>> getReservations(){
        User user = userService.getUserFromAuthenticationPrincipal();
        return ResponseEntity.ok(clientService.getReservations(user.getIdUser()));
    }

    @GetMapping
    @SecurityRequirement(name = SWAGGER_AUTH_KEY)
    public ResponseEntity<List<User>> getClients(){
        return ResponseEntity.ok(clientService.getClients());
    }

    @GetMapping("/{idClient}")
    @SecurityRequirement(name = SWAGGER_AUTH_KEY)
    public ResponseEntity<User> getClient(@PathVariable int idClient){
        return ResponseEntity.ok(clientService.getClient(idClient));
    }

}
