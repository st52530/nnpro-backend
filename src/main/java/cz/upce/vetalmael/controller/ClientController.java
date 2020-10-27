package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Reservation;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SingUpDto;
import cz.upce.vetalmael.service.ClientService;
import cz.upce.vetalmael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping("/client")
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

    @GetMapping("/getAnimals")
    public ResponseEntity<List<Animal>> getAnimals(){
        User user = userService.getUserFromAuthenticationPrincipal();
        return ResponseEntity.ok(clientService.getAnimals(user.getIdUser()));
    }

    @GetMapping("/getReservations")
    public ResponseEntity<List<Reservation>> getReservations(){
        User user = userService.getUserFromAuthenticationPrincipal();
        return ResponseEntity.ok(clientService.getReservations(user.getIdUser()));
    }

}
