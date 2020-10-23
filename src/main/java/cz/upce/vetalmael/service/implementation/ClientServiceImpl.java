package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Role;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SignInDto;
import cz.upce.vetalmael.model.dto.SingUpDto;
import cz.upce.vetalmael.repository.UserRepository;
import cz.upce.vetalmael.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service(value = "clientService")
public class ClientServiceImpl implements ClientService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void addClient(SingUpDto user){
        User dbUser = new User();
        dbUser.setEmail(user.getEmail());
        dbUser.setUsername(user.getUsername());
        dbUser.setFullName(user.getFullName());
        dbUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        dbUser.setRoles(Role.CLIENT.getAuthority());
        userRepository.save(dbUser);
    }


}
