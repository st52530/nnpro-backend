package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.ClinicConsumable;
import cz.upce.vetalmael.model.ClinicMedicine;
import cz.upce.vetalmael.model.dto.ClinicConsumableDto;
import cz.upce.vetalmael.model.dto.ClinicDto;
import cz.upce.vetalmael.model.dto.ClinicMedicineDto;
import cz.upce.vetalmael.service.ClinicConsumableService;
import cz.upce.vetalmael.service.ClinicMedicineService;
import cz.upce.vetalmael.service.ClinicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/clinics")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private ClinicMedicineService clinicMedicineService;

    @Autowired
    private ClinicConsumableService clinicConsumableService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping
    public ResponseEntity<Clinic> addClinic(@RequestBody ClinicDto clinicDto) {
        return ResponseEntity.ok(clinicService.addClinic(clinicDto));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/{idClinic}")
    public ResponseEntity<Clinic> editClinic(@RequestBody ClinicDto clinicDto, @PathVariable int idClinic) {
        return ResponseEntity.ok(clinicService.editClinic(clinicDto, idClinic));
    }

    @DeleteMapping("/{idClinic}")
    public ResponseEntity<?> removeClinic(@PathVariable int idClinic) {
        try {
            clinicService.removeClinic(idClinic);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Clinic>> getClinics(){
        return ResponseEntity.ok(clinicService.getClinics());
    }

    @GetMapping("/{idClinic}")
    public ResponseEntity<Clinic> getClinic(@PathVariable int idClinic){
        return ResponseEntity.ok(clinicService.getClinic(idClinic));
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/{idClinic}/clinic-medicine/medicine/{idMedicine}")
    public ResponseEntity<ClinicMedicine> addMedicineToClinic(@RequestBody ClinicMedicineDto clinicMedicineDto, @PathVariable int idClinic, @PathVariable int idMedicine) {
        try {
            return ResponseEntity.ok(clinicMedicineService.addClinicMedicine(clinicMedicineDto, idClinic, idMedicine));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/clinic-medicine/{idClinicMedicine}")
    public ResponseEntity<ClinicMedicine> addMedicineQuantityInStock(@RequestBody ClinicMedicineDto clinicMedicineDto, @PathVariable int idClinicMedicine){
        return ResponseEntity.ok(clinicMedicineService.editClinicMedicine(clinicMedicineDto, idClinicMedicine));
    }

    @DeleteMapping("/clinic-medicine/{idClinicMedicine}")
    public ResponseEntity<?> removeClinicMedicine(@PathVariable int idClinicMedicine) {
        try {
            clinicMedicineService.removeClinicMedicine(idClinicMedicine);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/clinic-medicine")
    public ResponseEntity<?> removeClinicMedicine() {
        try {
            clinicMedicineService.removeClinicMedicine();
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/{idClinic}/clinic-consumable/consumable/{idConsumable}")
    public ResponseEntity<ClinicConsumable> addConsumableToClinic(@RequestBody ClinicConsumableDto clinicConsumableDto, @PathVariable int idClinic, @PathVariable int idConsumable) {
        try {
            return ResponseEntity.ok(clinicConsumableService.addClinicConsumable(clinicConsumableDto, idClinic, idConsumable));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/clinic-consumable/{idClinicConsumable}")
    public ResponseEntity<ClinicConsumable> addConsumableQuantityInStock(@RequestBody ClinicConsumableDto clinicConsumableDto, @PathVariable int idClinicConsumable){
        return ResponseEntity.ok(clinicConsumableService.editClinicConsumable(clinicConsumableDto, idClinicConsumable));
    }

    @DeleteMapping("/clinic-consumable/{idClinicConsumable}")
    public ResponseEntity<?> removeClinicConsumable(@PathVariable int idClinicConsumable) {
        try {
            clinicConsumableService.removeClinicConsumable(idClinicConsumable);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @GetMapping("/{idClinic}/clinic-consumable/consumable/{idConsumable}")
    public ResponseEntity<ClinicConsumable> getConsumablesInClinic(@PathVariable int idClinic, @PathVariable int idConsumable) {
        return ResponseEntity.ok(clinicConsumableService.getClinicConsumable(idClinic, idConsumable));
    }

    @Transactional(rollbackOn = Exception.class)
    @GetMapping("/{idClinic}/clinic-consumable/consumables")
    public ResponseEntity<List<ClinicConsumable>> getConsumablesInClinic(@PathVariable int idClinic) {
        return ResponseEntity.ok(clinicConsumableService.getClinicConsumables(idClinic));
    }

    @Transactional(rollbackOn = Exception.class)
    @GetMapping("/{idClinic}/clinic-medicine/medicine/{idMedicine}")
    public ResponseEntity<ClinicMedicine> getMedicinesInClinic(@PathVariable int idClinic, @PathVariable int idMedicine) {
        return ResponseEntity.ok(clinicMedicineService.getClinicMedicine(idClinic, idMedicine));
    }

    @Transactional(rollbackOn = Exception.class)
    @GetMapping("/{idClinic}/clinic-medicine/medicines")
    public ResponseEntity<List<ClinicMedicine>> getMedicinesInClinic(@PathVariable int idClinic) {
        return ResponseEntity.ok(clinicMedicineService.getClinicMedicines(idClinic));
    }

}
