package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByAnimal_IdAnimal(int idAnimal);

}

