package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.Consumable;
import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.dto.ConsumableDto;
import cz.upce.vetalmael.model.dto.MedicineDto;
import cz.upce.vetalmael.service.ConsumableService;
import cz.upce.vetalmael.service.MedicineService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/consumables")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class ConsumableController {

    @Autowired
    private ConsumableService consumableService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping
    public ResponseEntity<Consumable> addConsumable(@RequestBody ConsumableDto consumableDto) {
        return ResponseEntity.ok(consumableService.addConsumable(consumableDto));
    }

    @PostMapping(value = "/excel", consumes = {"multipart/form-data"})
    public ResponseEntity<List<Consumable>> importConsumables(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(consumableService.importConsumables(file));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/{idConsumable}")
    public ResponseEntity<Consumable> editConsumable(@RequestBody ConsumableDto consumableDto, @PathVariable int idConsumable) {
        return ResponseEntity.ok(consumableService.editConsumable(consumableDto, idConsumable));
    }

    @DeleteMapping("/{idConsumable}")
    public ResponseEntity<?> removeConsumable(@PathVariable int idConsumable) {
        try {
            consumableService.removeConsumable(idConsumable);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Consumable>> getConsumables() {
        return ResponseEntity.ok(consumableService.getConsumables());
    }

    @GetMapping("/{idConsumable}")
    public ResponseEntity<Consumable> getConsumable(@PathVariable int idConsumable) {
        return ResponseEntity.ok(consumableService.getConsumable(idConsumable));
    }
}
