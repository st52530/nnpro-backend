package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal,Integer> {
}
