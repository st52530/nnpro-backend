package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportV2Repository extends JpaRepository<Report, Integer> {
    List<Report> findAllByVeterinary_Workplace(Clinic clinic);

    List<Report> findAllByAnimal(Animal animal);

    List<Report> findAllByAnimal_Owner(User owner);

    List<Report> findAllByVeterinary(User veterinary);
}
