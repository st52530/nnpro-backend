package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Operation;
import cz.upce.vetalmael.model.dto.OperationDto;
import cz.upce.vetalmael.service.OperationService;
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
@RequestMapping("/operations")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class OperationController {

    @Autowired
    private OperationService operationService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping
    public ResponseEntity<Operation> addAnimal(@RequestBody OperationDto operationDto) {
        return ResponseEntity.ok(operationService.addOperation(operationDto));
    }

    @PostMapping(value = "/excel", consumes = {"multipart/form-data"})
    public ResponseEntity<List<Operation>> importOperationss(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(operationService.importOperations(file));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/{idOperation}")
    public ResponseEntity<Operation> editAnimal(@RequestBody OperationDto operationDto,@PathVariable int idOperation) {
        return ResponseEntity.ok(operationService.editOperation(operationDto, idOperation));
    }

    @DeleteMapping("/{idOperation}")
    public ResponseEntity<?> removeOperation(@PathVariable int idOperation) {
        try {
            operationService.removeOperation(idOperation);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
