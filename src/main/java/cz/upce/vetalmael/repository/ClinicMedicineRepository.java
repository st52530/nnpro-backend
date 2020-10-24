package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.ClinicMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicMedicineRepository extends JpaRepository<ClinicMedicine,Integer> {
}
