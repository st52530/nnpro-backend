package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Integer> {
}
