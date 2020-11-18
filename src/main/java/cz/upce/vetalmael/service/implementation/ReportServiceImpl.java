package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.*;
import cz.upce.vetalmael.model.dto.DoneReportDto;
import cz.upce.vetalmael.model.dto.ReadyReportDto;
import cz.upce.vetalmael.repository.ReportRepository;
import cz.upce.vetalmael.service.ReportService;
import cz.upce.vetalmael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "reportService")
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserService userService;

    @Override
    public Report addReadyReport(ReadyReportDto reportDto) {
        Report report = new Report();
        report.setTextDescription(reportDto.getTextDescription());
        report.setReportState(ReportState.READY);
        Animal animal = new Animal();
        animal.setIdAnimal(reportDto.getIdAnimal());
        report.setAnimal(animal);
        return reportRepository.save(report);
    }

    @Override
    public Report editReadyReport(ReadyReportDto reportDto, int idReport) {
        Report report = new Report();
        report.setIdReport(idReport);
        report.setTextDescription(reportDto.getTextDescription());
        report.setReportState(ReportState.READY);
        Animal animal = new Animal();
        animal.setIdAnimal(reportDto.getIdAnimal());
        report.setAnimal(animal);
        return reportRepository.save(report);
    }

    @Override
    public Report addDoneReport(DoneReportDto reportDto, String veterinaryUsername) {
        Report report = makeReport(reportDto, veterinaryUsername);
        return reportRepository.save(report);
    }

    private Report makeReport(DoneReportDto reportDto, String veterinaryUsername) {
        Report report = new Report();

        report.setTextDiagnosis(reportDto.getTextDiagnosis());
        report.setTextRecommendation(reportDto.getTextRecommendation());
        report.setTextDescription(reportDto.getTextDescription());
        report.setReportState(ReportState.DONE);

        Animal animal = new Animal();
        animal.setIdAnimal(reportDto.getIdAnimal());
        report.setAnimal(animal);

        if(reportDto.getIdDiagnosis() != null) {
            Diagnosis diagnosis = new Diagnosis();
            diagnosis.setIdDiagnosis(reportDto.getIdDiagnosis());
            report.setDiagnosis(diagnosis);
        }

        if(reportDto.getIdOperation() != null) {
            Operation operation = new Operation();
            operation.setIdOperation(reportDto.getIdOperation());
            report.setOperation(operation);
        }

        if(reportDto.getSetOfIdMedicines() != null) {
            reportDto.getSetOfIdMedicines().forEach(idMedicine -> {
                Medicine medicine = new Medicine();
                medicine.setIdMedicine(idMedicine);
                report.getMedicines().add(medicine);
            });
        }

        if(reportDto.getSetOfIdConsumables() != null) {
            reportDto.getSetOfIdConsumables().forEach(idConsumable -> {
                Consumable consumable = new Consumable();
                consumable.setIdConsumable(idConsumable);
                report.getConsumables().add(consumable);
            });
        }

        User user = userService.findByUsername(veterinaryUsername);
        report.setVeterinary(user);
        return report;
    }

    @Override
    public Report makeReadyReportDone(DoneReportDto reportDto, int idReadyReport, String veterinaryUsername) {
        Report report = makeReport(reportDto, veterinaryUsername);
        report.setIdReport(idReadyReport);
        return reportRepository.save(report);
    }

    @Override
    public Report editDoneReport(DoneReportDto reportDto,int idReport, String veterinaryUsername) {
        Report report = makeReport(reportDto, veterinaryUsername);
        report.setIdReport(idReport);
        return reportRepository.save(report);
    }

    @Override
    public List<Report> getReports() {
        return reportRepository.findAll();
    }

    @Override
    public void deleteReports() {
        reportRepository.deleteAll();
    }
}
