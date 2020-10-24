package cz.upce.vetalmael.controller;


import cz.upce.vetalmael.model.ClinicMedicine;
import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.dto.ClinicMedicineDto;
import cz.upce.vetalmael.model.dto.MedicineDto;
import cz.upce.vetalmael.service.MedicineService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/medicine")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping
    public ResponseEntity<Medicine> addMedicine(@RequestBody MedicineDto medicineDto) {
        return ResponseEntity.ok(medicineService.addMedicine(medicineDto));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping
    public ResponseEntity<Medicine> editMedicine(@RequestBody MedicineDto medicineDto) {
        return ResponseEntity.ok(medicineService.editMedicine(medicineDto));
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
