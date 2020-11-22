package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.exception.ValidationException;
import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.Report;
import cz.upce.vetalmael.model.ReportState;
import cz.upce.vetalmael.service.AnimalService;
import cz.upce.vetalmael.service.ClinicService;
import cz.upce.vetalmael.service.ReportV2Service;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/reports-v2")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class ReportV2Controller {

    @Autowired
    private ReportV2Service reportService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Report> getReports(@RequestParam(name = "clientId", required = false) Integer clinicId,
                                   @RequestParam(name = "animalId", required = false) Integer animalId,
                                   @RequestParam(name = "state", required = false) ReportState state) {

        if (clinicId != null){
            Clinic clinic = clinicService.getClinic(clinicId);
            if (clinic == null){
                throw new ValidationException("Clinic not exists");
            }
            return reportService.getReports(clinic, state);
        }

        if (animalId != null){
            Animal animal = animalService.getAnimal(animalId);
            if (animal == null){
                throw new ValidationException("Animal not exists");
            }
            return reportService.getReports(animal, state);
        }

        return reportService.getReports();
    }

    @GetMapping(value = "/{id}")
    public Report getReport(@PathVariable("id") Integer id) {
        return reportService.getReport(id);
    }

    @PostMapping
    public Report createNewReport(@RequestBody Report report) {
        return reportService.createNewReport(report);
    }

    @PutMapping(value = "/{id}")
    public Report finishReport(@PathVariable("id") Integer reportId,@RequestBody Report report) {
        return reportService.finishReport(reportId,report);
    }

}
