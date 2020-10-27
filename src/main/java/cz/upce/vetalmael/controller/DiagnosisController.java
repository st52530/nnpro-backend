package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Diagnosis;
import cz.upce.vetalmael.model.dto.DiagnosisDto;
import cz.upce.vetalmael.service.DiagnosisService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/diagnoses")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping
    public ResponseEntity<Diagnosis> addAnimal(@RequestBody DiagnosisDto diagnosisDto) {
        return ResponseEntity.ok(diagnosisService.addDiagnosis(diagnosisDto));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/{idDiagnosis}")
    public ResponseEntity<Diagnosis> editAnimal(@RequestBody DiagnosisDto diagnosisDto, @PathVariable int idDiagnosis) {
        return ResponseEntity.ok(diagnosisService.editDiagnosis(diagnosisDto, idDiagnosis));
    }

    @DeleteMapping("/{idDiagnosis}")
    public ResponseEntity<?> removeDiagnosis(@PathVariable int idDiagnosis) {
        try {
            diagnosisService.removeDiagnosis(idDiagnosis);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
