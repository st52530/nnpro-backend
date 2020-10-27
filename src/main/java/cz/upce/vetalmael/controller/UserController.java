package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.LoggedUser;
import cz.upce.vetalmael.model.dto.SignInDto;
import cz.upce.vetalmael.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoggedUser> login(@RequestBody SignInDto signInDto) {

        Optional<LoggedUser> login = loginService.login(signInDto);
        return login.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

    }

}
