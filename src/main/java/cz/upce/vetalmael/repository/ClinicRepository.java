package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic,Integer> {
}
