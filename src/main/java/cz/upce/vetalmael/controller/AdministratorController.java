package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SingUpDto;
import cz.upce.vetalmael.service.AdministratorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController("/administrators")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @PostMapping
    public ResponseEntity<User> addAdministrator(@RequestBody SingUpDto singUpDto) {
        return ResponseEntity.ok(administratorService.addAdministrator(singUpDto));

    }
}
