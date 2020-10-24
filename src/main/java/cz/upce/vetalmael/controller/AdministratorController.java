package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SingUpDto;
import cz.upce.vetalmael.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @PostMapping("/administrator")
    public ResponseEntity<User> addAdmin(@RequestBody SingUpDto singUpDto) {
        return ResponseEntity.ok(administratorService.addAdministrator(singUpDto));

    }
}
