package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.exception.ValidationException;
import cz.upce.vetalmael.model.*;
import cz.upce.vetalmael.repository.ReportV2Repository;
import cz.upce.vetalmael.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportV2ServiceImpl implements ReportV2Service {

    private final ReportV2Repository reportV2Repository;

    private final AnimalService animalService;

    private final EmployeeService employeeService;

    private final ConsumableService consumableService;

    private final DiagnosisService diagnosisService;

    private final OperationService operationService;

    private final MedicineService medicineService;

    @Autowired
    public ReportV2ServiceImpl(ReportV2Repository reportV2Repository,
                               AnimalService animalService,
                               EmployeeService employeeService,
                               ConsumableService consumableService,
                               DiagnosisService diagnosisService,
                               OperationService operationService,
                               MedicineService medicineService) {
        this.reportV2Repository = reportV2Repository;
        this.animalService = animalService;
        this.employeeService = employeeService;
        this.consumableService = consumableService;
        this.diagnosisService = diagnosisService;
        this.operationService = operationService;
        this.medicineService = medicineService;
    }

    @Override
    public List<Report> getReports() {
        return reportV2Repository.findAll();
    }

    @Override
    public Report getReport(Integer id) {
        Report report = reportV2Repository.findById(id).orElse(null);
        if (report == null) {
            throw new ValidationException("Report " + id + " not exists");
        }
        return report;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Report> getReports(Clinic clinic, ReportState state) {
        return reportV2Repository.findAllByVeterinary_WorkplaceAndReportState(clinic, state);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Report> getReports(Animal animal, ReportState state) {
        return reportV2Repository.findAllByAnimalAndReportState(animal, state);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Report createNewReport(Report report) {
        if (report.getAnimal() == null) {
            throw new ValidationException("Animal is null");
        }
        if (report.getVeterinary() == null) {
            throw new ValidationException("Veterinary is null");
        }
        Integer animalId = report.getAnimal().getIdAnimal();
        Integer veterinaryId = report.getVeterinary().getIdUser();

        User veterinary = employeeService.getEmployee(veterinaryId);
        if (veterinary == null) {
            throw new ValidationException("Veterinary doesnt exist");
        }

        Animal animal = animalService.getAnimal(animalId);
        if (animal == null){
            throw new ValidationException("Animal doesnt exist");
        }

        if (StringUtils.isBlank(report.getTextDescription())){
            throw new ValidationException("Description is blank");
        }

        Report reportForDb = new Report();
        reportForDb.setAnimal(animal);
        reportForDb.setVeterinary(veterinary);
        reportForDb.setTextDescription(report.getTextDescription());
        reportForDb.setReportState(ReportState.READY);

        reportV2Repository.save(reportForDb);

        return reportForDb;
    }

    @Override
    public Report finishReport(Integer reportId, Report report) {
        Report original = reportV2Repository.findById(reportId).orElse(null);
        if (original == null){
            throw new ValidationException("Report not exists");
        }
        if (original.getReportState() != ReportState.READY){
            throw new ValidationException("Report is not ready");
        }

        if (report.getDiagnosis() == null){
            throw new ValidationException("Diagnosis is null");
        }
        Diagnosis diagnosis = diagnosisService.getDiagnosis(report.getDiagnosis().getIdDiagnosis());
        if (diagnosis == null) {
            throw new ValidationException("Diagnosis doesnt exists");
        }
        if (report.getOperation() == null) {
            throw new ValidationException("Operation is null");
        }
        Operation operation = operationService.getOperation(report.getOperation().getIdOperation());
        if (operation == null) {
            throw new ValidationException("Operation doesnt exists");
        }
        if (StringUtils.isBlank(report.getTextDiagnosis())){
            throw new ValidationException("Text diagnosis empty null");
        }

        for (Medicine medicine : report.getMedicines()){
            if (medicineService.getMedicine(medicine.getIdMedicine()) == null) {
                throw new ValidationException("Medicine " + medicine.getIdMedicine() + " doesnt exists");
            }
            original.getMedicines().add(medicine);
        }

        for (Consumable consumable : report.getConsumables()){
            if (consumableService.getConsumable(consumable.getIdConsumable()) == null) {
                throw new ValidationException("Consumable " + consumable.getIdConsumable() + " doesnt exists");
            }
            original.getConsumables().add(consumable);
        }


        original.setReportState(ReportState.DONE);
        original.setDiagnosis(diagnosis);
        original.setOperation(operation);
        original.setMedicines(report.getMedicines());
        original.setConsumables(report.getConsumables());
        original.setTextRecommendation(report.getTextRecommendation());

        reportV2Repository.save(original);
        return original;
    }



}
