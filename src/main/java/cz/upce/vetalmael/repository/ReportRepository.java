package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Integer> {

    List<Report> findAllByAnimal_IdAnimal(int idAnimal);

}
