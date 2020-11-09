package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.ClinicConsumable;
import cz.upce.vetalmael.model.ClinicMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicMedicineRepository extends JpaRepository<ClinicMedicine,Integer> {

    ClinicMedicine findByClinic_IdClinicAndMedicine_IdMedicine(int idClinic, int idMedicine);

    List<ClinicMedicine> findAllByClinic_IdClinic(int idClinic);
}
