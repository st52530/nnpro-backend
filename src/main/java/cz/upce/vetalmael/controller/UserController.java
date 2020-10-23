package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SignInDto;
import cz.upce.vetalmael.model.dto.SingUpDto;
import cz.upce.vetalmael.service.ClientService;
import cz.upce.vetalmael.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginService loginService;



    @PostMapping("/login")
    public String login(@RequestBody SignInDto signInDto) {
        return loginService.login(signInDto);
    }

}
