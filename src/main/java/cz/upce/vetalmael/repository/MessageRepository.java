package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
