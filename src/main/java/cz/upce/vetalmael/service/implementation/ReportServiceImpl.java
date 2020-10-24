package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.*;
import cz.upce.vetalmael.model.dto.DoneReportDto;
import cz.upce.vetalmael.model.dto.ReadyReportDto;
import cz.upce.vetalmael.repository.ReportRepository;
import cz.upce.vetalmael.service.LoginService;
import cz.upce.vetalmael.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service(value = "reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private LoginService loginService;

    @Override
    public Report addReadyReport(ReadyReportDto reportDto) {
        /*Report report = new Report();
        report.setTextDescription(reportDto.getTextDescription());
        report.setReportState(reportDto.getReportState());
        Animal animal = new Animal();
        animal.setIdAnimal(reportDto.getAnimalDto().getIdAnimal());
        report.setAnimal(animal);
        return reportRepository.save(report);*/
        return null;
    }

    @Override
    public Report editReadyReport(ReadyReportDto reportDto, int idReport) {
        /*Report report = new Report();
        report.setIdReport(idReport);
        report.setTextDescription(reportDto.getTextDescription());
        report.setReportState(reportDto.getReportState());
        Animal animal = new Animal();
        animal.setIdAnimal(reportDto.getAnimalDto().getIdAnimal());
        report.setAnimal(animal);
        return reportRepository.save(report);*/
        return null;
    }

    @Override
    public Report addDoneReport(DoneReportDto reportDto, String veterinaryUsername) {
        /*Report report = new Report();

        report.setTextDiagnosis(reportDto.getTextDiagnosis());
        report.setTextRecommendation(reportDto.getTextRecommendation());
        report.setTextDescription(reportDto.getTextDescription());
        report.setReportState(reportDto.getReportState());

        Animal animal = new Animal();
        animal.setIdAnimal(reportDto.getAnimalDto().getIdAnimal());
        report.setAnimal(animal);

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setIdDiagnosis(reportDto.getDiagnosisDto().getIdDiagnosis());
        report.setDiagnosis(diagnosis);

        Operation operation = new Operation();
        operation.setIdOperation(reportDto.getOperationDto().getIdOperation());
        report.setOperation(operation);

        Set<Medicine> medicines = new HashSet<>();
        reportDto.getMedicines().forEach(medicineDto -> {
            Medicine medicine = new Medicine();
            medicine.setIdMedicine(medicineDto.getIdMedicine());
            medicines.add(medicine);
        });
        report.setMedicines(medicines);

        //Consumables

        User user = loginService.findByUsername(veterinaryUsername);
        report.setVeterinary(user);
        return reportRepository.save(report);*/
        return null;
    }

    @Override
    public Report editDoneReport(DoneReportDto reportDto,int idReport, String veterinaryUsername) {
        /*Report report = new Report();
        report.setIdReport(idReport);
        report.setTextDiagnosis(reportDto.getTextDiagnosis());
        report.setTextRecommendation(reportDto.getTextRecommendation());
        report.setTextDescription(reportDto.getTextDescription());
        report.setReportState(reportDto.getReportState());

        Animal animal = new Animal();
        animal.setIdAnimal(reportDto.getAnimalDto().getIdAnimal());
        report.setAnimal(animal);

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setIdDiagnosis(reportDto.getDiagnosisDto().getIdDiagnosis());
        report.setDiagnosis(diagnosis);

        Operation operation = new Operation();
        operation.setIdOperation(reportDto.getOperationDto().getIdOperation());
        report.setOperation(operation);

        //Tady to možná bude trochu jinak
        Set<Medicine> medicines = new HashSet<>();
        reportDto.getMedicines().forEach(medicineDto -> {
            Medicine medicine = new Medicine();
            medicine.setIdMedicine(medicineDto.getIdMedicine());
            medicines.add(medicine);
        });
        report.setMedicines(medicines);

        //Consumables

        User user = loginService.findByUsername(veterinaryUsername);
        report.setVeterinary(user);
        return reportRepository.save(report);*/
        return null;
    }
}
