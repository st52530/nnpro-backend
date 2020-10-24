package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Role;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SingUpDto;
import cz.upce.vetalmael.repository.UserRepository;
import cz.upce.vetalmael.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "administratorService")
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User addAdministrator(SingUpDto singUpDto) {
        User user = new User();
        user.setEmail(singUpDto.getEmail());
        user.setUsername(singUpDto.getUsername());
        user.setFullName(singUpDto.getFullName());
        user.setPassword(bCryptPasswordEncoder.encode(singUpDto.getPassword()));
        user.setRoles(Role.ADMINISTRATOR.getAuthority());
        return userRepository.save(user);
    }
}
