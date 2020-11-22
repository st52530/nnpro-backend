package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.exception.ValidationException;
import cz.upce.vetalmael.model.*;
import cz.upce.vetalmael.model.dto.ClientDto;
import cz.upce.vetalmael.model.dto.SignInDto;
import cz.upce.vetalmael.model.dto.SingUpDto;
import cz.upce.vetalmael.repository.AnimalRepository;
import cz.upce.vetalmael.repository.ReportV2Repository;
import cz.upce.vetalmael.repository.ReservationRepository;
import cz.upce.vetalmael.repository.UserRepository;
import cz.upce.vetalmael.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Service(value = "clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ReportV2Repository reportV2Repository;

    @Override
    public User addClient(SingUpDto user) {
        User dbUser = new User();
        dbUser.setEmail(user.getEmail());
        dbUser.setUsername(user.getUsername());
        dbUser.setFullName(user.getFullName());
        dbUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        dbUser.setRoles(Role.CLIENT.getAuthority());
        return userRepository.save(dbUser);
    }

    @Override
    public User editClient(ClientDto client, int idClient) {
        User user = userRepository.getOne(idClient);
        if(client.getEmail() != null){
            user.setEmail(client.getEmail());
        }
        if(client.getFullName() != null){
            user.setFullName(client.getFullName());
        }
        return userRepository.save(user);
    }

    @Override
    public List<Animal> getAnimals(int idUser) {
        return animalRepository.findAllByOwner_idUser(idUser);
    }

    @Override
    public List<Report> getReports(int idUser) {
        User user = getClient(idUser);
        if (user == null) {
            throw new ValidationException("User " + idUser + " not exists");
        }
        return reportV2Repository.findAllByAnimal_Owner(user);
    }

    @Override
    public List<Reservation> getReservations(int idUser) {
        return reservationRepository.findAllByClient_idUser(idUser);
    }

    @Override
    public User getClient(int idClient) {
        return userRepository.getOne(idClient);
    }

    @Override
    public List<User> getClients() {
        return userRepository.findAllByRolesLike("CLIENT");
    }


}
