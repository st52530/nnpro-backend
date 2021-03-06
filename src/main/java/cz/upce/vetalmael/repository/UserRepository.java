package cz.upce.vetalmael.repository;


import cz.upce.vetalmael.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>{

    User findByUsername(String username);

    List<User> findAllByRolesLike(String like);

    List<User> findAllByRolesContainingOrRolesContainingOrRolesContaining(String like, String like2, String like3);

    List<User> findAllByWorkplace_IdClinic(int idClinic);

}
