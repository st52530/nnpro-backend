package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.ClinicConsumable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicConsumableRepository extends JpaRepository<ClinicConsumable, Integer> {

    ClinicConsumable findByClinic_IdClinicAndConsumable_IdConsumable(int idClinic, int idConsumable);

    List<ClinicConsumable> findAllByClinic_IdClinic(int idClinic);
}
