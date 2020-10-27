package cz.upce.vetalmael.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.LoggedUser;
import cz.upce.vetalmael.model.dto.SignInDto;
import cz.upce.vetalmael.repository.UserRepository;
import cz.upce.vetalmael.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cz.upce.vetalmael.security.SecurityConstants.EXPIRATION_TIME;
import static cz.upce.vetalmael.security.SecurityConstants.SECRET;

@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Optional<LoggedUser> login(SignInDto signInDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword(), new ArrayList<>()));
        LoggedUser loggedUser = null;
        if(authenticate.isAuthenticated()){
            List<String> collect = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            User user = (User) authenticate.getPrincipal();
            String token = JWT.create()
                    .withSubject(((User) authenticate.getPrincipal()).getUsername())
                    .withClaim("role", collect)
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SECRET.getBytes()));
            loggedUser = new LoggedUser(user, token);
        }
        return Optional.ofNullable(loggedUser);
    }
}
