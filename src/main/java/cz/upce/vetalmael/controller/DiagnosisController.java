package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Consumable;
import cz.upce.vetalmael.model.Diagnosis;
import cz.upce.vetalmael.model.dto.DiagnosisDto;
import cz.upce.vetalmael.service.DiagnosisService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.io.IOException;
import java.util.List;

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

    @PostMapping(value = "/excel", consumes = {"multipart/form-data"})
    public ResponseEntity<List<Diagnosis>> importDiagnoses(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(diagnosisService.importDiagnosis(file));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
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

    @GetMapping("/animals/{idAnimal}")
    public ResponseEntity<List<Diagnosis>> getAnimalDiagnosis(@PathVariable int idAnimal){
        return ResponseEntity.ok(diagnosisService.getAnimalDiagnosis(idAnimal));
    }
}
