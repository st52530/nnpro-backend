package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {
}
