package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SingUpDto;
import cz.upce.vetalmael.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;


@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/sign-up")
    //@SecurityRequirement(name = SWAGGER_AUTH_KEY)
    public ResponseEntity<User> signUp(@RequestBody SingUpDto user) {
        return ResponseEntity.ok(clientService.addClient(user));
    }
}
