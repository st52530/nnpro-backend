package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Role;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SignUpDto;
import cz.upce.vetalmael.repository.UserRepository;
import cz.upce.vetalmael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User signUp(SignUpDto user){
        User dbUser = new User();
        dbUser.setUsername(user.getUsername());
        dbUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        dbUser.setRoles(Role.USER.getAuthority());

        return userRepository.save(dbUser);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
