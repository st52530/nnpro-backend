package cz.upce.vetalmael.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cz.upce.vetalmael.model.Role;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.SignUpDto;
import cz.upce.vetalmael.repository.UserRepository;
import cz.upce.vetalmael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static cz.upce.vetalmael.security.SecurityConstants.EXPIRATION_TIME;
import static cz.upce.vetalmael.security.SecurityConstants.SECRET;


@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(SignUpDto user) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        if(authenticate.isAuthenticated()){
            List<String> collect = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            return JWT.create()
                    .withSubject(((User) authenticate.getPrincipal()).getUsername())
                    .withClaim("role", collect)
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SECRET.getBytes()));
        }
        return "";
    }

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
