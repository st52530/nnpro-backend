package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.Consumable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumableRepository extends JpaRepository<Consumable, Integer> {
}
