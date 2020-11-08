package cz.upce.vetalmael.controller;


import cz.upce.vetalmael.model.Diagnosis;
import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.dto.MedicineDto;
import cz.upce.vetalmael.service.MedicineService;
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
@RequestMapping("/medicines")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping
    public ResponseEntity<Medicine> addMedicine(@RequestBody MedicineDto medicineDto) {
        return ResponseEntity.ok(medicineService.addMedicine(medicineDto));
    }

    @PostMapping(value = "/excel", consumes = {"multipart/form-data"})
    public ResponseEntity<List<Medicine>> importMedicines(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(medicineService.importMedicine(file));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/{idMedicine}")
    public ResponseEntity<Medicine> editMedicine(@RequestBody MedicineDto medicineDto, @PathVariable int idMedicine) {
        return ResponseEntity.ok(medicineService.editMedicine(medicineDto, idMedicine));
    }

    @DeleteMapping("/{idMedicine}")
    public ResponseEntity<?> removeMedicine(@PathVariable int idMedicine) {
        try {
            medicineService.removeMedicine(idMedicine);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
