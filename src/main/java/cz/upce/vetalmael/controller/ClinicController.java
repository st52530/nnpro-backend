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
@RequestMapping("/clinic")
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

    @DeleteMapping("/{idClinic}")
    public ResponseEntity<?> removeClinic(@PathVariable int idClinic) {
        try {
            clinicService.removeClinic(idClinic);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("s")
    public ResponseEntity<List<Clinic>> getClinics(){
        return ResponseEntity.ok(clinicService.getClinics());
    }

    @GetMapping("/{idClinic}")
    public ResponseEntity<Clinic> getClinic(@PathVariable int idClinic){
        return ResponseEntity.ok(clinicService.getClinic(idClinic));
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/{idClinic}/medicine/{idMedicine}")
    public ResponseEntity<ClinicMedicine> addMedicineToClinic(@RequestBody ClinicMedicineDto clinicMedicineDto, @PathVariable int idClinic, @PathVariable int idMedicine) {
        return ResponseEntity.ok(clinicMedicineService.addClinicMedicine(clinicMedicineDto, idClinic, idMedicine));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/clinic-medicine/{idClinicMedicine}")
    public ResponseEntity<ClinicMedicine> addMedicineQuantityInStock(@RequestBody ClinicMedicineDto clinicMedicineDto, @PathVariable int idClinicMedicine){
        return ResponseEntity.ok(clinicMedicineService.addQuantityInStock(clinicMedicineDto, idClinicMedicine));
    }

    @DeleteMapping("/medicine/{idClinicMedicine}")
    public ResponseEntity<?> removeClinicMedicine(@PathVariable int idClinicMedicine) {
        try {
            clinicMedicineService.removeClinicMedicine(idClinicMedicine);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/{idClinic}/consumable/{idConsumable}")
    public ResponseEntity<ClinicConsumable> addConsumableToClinic(@RequestBody ClinicConsumableDto clinicConsumableDto, @PathVariable int idClinic, @PathVariable int idConsumable) {
        return ResponseEntity.ok(clinicConsumableService.addClinicConsumable(clinicConsumableDto, idClinic, idConsumable));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/clinic-consumable/{idClinicConsumable}")
    public ResponseEntity<ClinicConsumable> addConsumableQuantityInStock(@RequestBody ClinicConsumableDto clinicConsumableDto, @PathVariable int idClinicConsumable){
        return ResponseEntity.ok(clinicConsumableService.addQuantityInStock(clinicConsumableDto, idClinicConsumable));
    }

    @DeleteMapping("/consumable/{idClinicConsumable}")
    public ResponseEntity<?> removeClinicConsumable(@PathVariable int idClinicConsumable) {
        try {
            clinicConsumableService.removeClinicConsumable(idClinicConsumable);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}