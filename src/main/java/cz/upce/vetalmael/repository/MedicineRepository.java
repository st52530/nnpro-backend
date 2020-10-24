package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
}
