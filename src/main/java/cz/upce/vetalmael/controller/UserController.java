package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SignUpDto;
import cz.upce.vetalmael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/sign-up")
    public User signUp(@RequestBody SignUpDto user) {
        return userService.signUp(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody SignUpDto signUpDto) {
        return userService.login(signUpDto);
    }

}
