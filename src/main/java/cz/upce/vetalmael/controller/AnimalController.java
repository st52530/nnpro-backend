package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.dto.AnimalDto;
import cz.upce.vetalmael.service.AnimalService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/animal")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestBody AnimalDto animalDto) {
        return ResponseEntity.ok(animalService.addAnimal(animalDto));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping
    public ResponseEntity<Animal> editAnimal(@RequestBody AnimalDto animalDto) {
        return ResponseEntity.ok(animalService.editAnimal(animalDto));
    }

    @DeleteMapping("/{idAnimal}")
    public ResponseEntity<?> removeAnimal(@PathVariable int idAnimal) {
        try {
            animalService.removeAnimal(idAnimal);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
