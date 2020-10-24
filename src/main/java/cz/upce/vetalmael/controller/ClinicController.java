package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.ClinicMedicine;
import cz.upce.vetalmael.model.dto.ClinicDto;
import cz.upce.vetalmael.model.dto.ClinicMedicineDto;
import cz.upce.vetalmael.service.ClinicMedicineService;
import cz.upce.vetalmael.service.ClinicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/clinic")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private ClinicMedicineService clinicMedicineService;

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

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/{idClinic}/medicine/{idMedicine}")
    public ResponseEntity<ClinicMedicine> addMedicineToClinic(@RequestBody ClinicMedicineDto clinicMedicineDto, @PathVariable int idClinic, @PathVariable int idMedicine) {
        return ResponseEntity.ok(clinicMedicineService.addClinicMedicine(clinicMedicineDto, idClinic, idMedicine));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/clinic-medicine/{idClinicMedicine}")
    public ResponseEntity<ClinicMedicine> addMedicineQuantityInStoctk(@RequestBody ClinicMedicineDto clinicMedicineDto, @PathVariable int idClinicMedicine){
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

    //Consumables
}
