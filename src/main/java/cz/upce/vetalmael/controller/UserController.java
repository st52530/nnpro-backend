package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.LoggedUser;
import cz.upce.vetalmael.model.dto.SignInDto;
import cz.upce.vetalmael.service.LoginService;
import cz.upce.vetalmael.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoggedUser> login(@RequestBody SignInDto signInDto) {
        Optional<LoggedUser> login = loginService.login(signInDto);
        return login.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

    }

    @GetMapping("/me")
    @SecurityRequirement(name = SWAGGER_AUTH_KEY)
    public ResponseEntity<User> getLoggedUser() {
        User loggedUser = userService.getUserFromAuthenticationPrincipal();
        if(loggedUser != null){
            return ResponseEntity.ok(loggedUser);
        }
        return ResponseEntity.notFound().build();
    }

}
