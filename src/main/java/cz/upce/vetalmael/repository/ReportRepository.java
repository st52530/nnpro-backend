package cz.upce.vetalmael.repository;

import cz.upce.vetalmael.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Integer> {

    List<Report> findAllByAnimal_IdAnimal(int idAnimal);

    List<Report> findAllByDiagnosisNotNullOrderByDiagnosis();

    List<Report> findByDateGreaterThanAndDateLessThan(Date start, Date end);

    List<Report> findByVeterinary_Workplace_IdClinicAndDateGreaterThanAndDateLessThan(int idClinic,Date start, Date end);

    int countAllByOperation_TypeAndDateGreaterThanAndDateLessThan(String type, Date start, Date end);

    int countAllByVeterinary_Workplace_IdClinicAndOperation_TypeAndDateGreaterThanAndDateLessThan(int idClinic,String type, Date start, Date end);
}
