package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Operation;
import cz.upce.vetalmael.model.dto.OperationDto;
import cz.upce.vetalmael.service.OperationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

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
