package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Diagnosis;
import cz.upce.vetalmael.model.Reservation;
import cz.upce.vetalmael.model.dto.DiagnosisStatistic;
import cz.upce.vetalmael.service.StatisticService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/statistics")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/most-common-diagnosis")
    public ResponseEntity<List<DiagnosisStatistic>> getMostCommonDiagnosis(){
        return ResponseEntity.ok(statisticService.getMostCommonDiagnosis());
    }

    @GetMapping("/different-clients-in-month/{year}/{month}")
    public ResponseEntity<Integer> getDifferentClientsInMonth(@PathVariable int year, @PathVariable int month){
        return ResponseEntity.ok(statisticService.getDifferentClientsInMonth(year, month));
    }
    @GetMapping("/different-clients-in-month/{idClinic}/{year}/{month}")
    public ResponseEntity<Integer> getDifferentClientsInMonthOnClinic(@PathVariable int idClinic,@PathVariable int year, @PathVariable int month){
        return ResponseEntity.ok(statisticService.getDifferentClientsInMonthOnClinic(idClinic,year, month));
    }

    @GetMapping("/vaccination-count/{year}/{month}")
    public ResponseEntity<Integer> getVaccinationCountInMonth(@PathVariable int year, @PathVariable int month){
        return ResponseEntity.ok(statisticService.getVaccinationCountInMonth(year, month));
    }

    @GetMapping("/vaccination-count/{idClinic}/{year}/{month}")
    public ResponseEntity<Integer> getVaccinationCountInMonthOnClinic(@PathVariable int idClinic,@PathVariable int year, @PathVariable int month){
        return ResponseEntity.ok(statisticService.getVaccinationCountInMonthOnClinic(idClinic,year, month));
    }
}
