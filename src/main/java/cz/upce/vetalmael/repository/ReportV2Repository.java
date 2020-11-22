package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportV2Repository extends JpaRepository<Report, Integer> {
    List<Report> findAllByVeterinary_WorkplaceAndReportState(Clinic clinic, ReportState reportState);

    List<Report> findAllByAnimalAndReportState(Animal animal, ReportState reportState);

    List<Report> findAllByAnimal_Owner(User owner);
}
