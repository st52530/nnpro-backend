package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal,Integer> {

    List<Animal> findAllByOwner_idUser(int idUser);

}

