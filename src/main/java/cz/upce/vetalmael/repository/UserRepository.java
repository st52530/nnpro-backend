package cz.upce.vetalmael.repository;


import cz.upce.vetalmael.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{

    User findByUsername(String username);

}
