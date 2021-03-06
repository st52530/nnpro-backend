package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Message;
import cz.upce.vetalmael.model.Report;
import cz.upce.vetalmael.model.dto.AnimalDto;
import cz.upce.vetalmael.service.AnimalService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.util.List;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/animals")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/user/{idUser}")
    public ResponseEntity<Animal> addAnimal(@RequestBody AnimalDto animalDto, @PathVariable int idUser) {
        if(animalDto.getName() == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(animalService.addAnimal(animalDto, idUser));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/{idAnimal}/user/{idUser}")
    public ResponseEntity<Animal> editAnimal(@RequestBody AnimalDto animalDto, @PathVariable int idAnimal, @PathVariable int idUser) {
        if(animalDto.getName() == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(animalService.editAnimal(animalDto, idAnimal, idUser));
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

    @GetMapping("/{idAnimal}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable int idAnimal){
        return ResponseEntity.ok(animalService.getMessages(idAnimal));
    }

    @GetMapping("/{idAnimal}/reports")
    public ResponseEntity<List<Report>> getReports(@PathVariable int idAnimal){
        return ResponseEntity.ok(animalService.getReports(idAnimal));
    }

    @GetMapping("/{idAnimal}")
    public ResponseEntity<Animal> getAnimal(@PathVariable int idAnimal){
        Animal animal = animalService.getAnimal(idAnimal);
        return animal == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(animal);
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAnimals(){
        return ResponseEntity.ok(animalService.getAnimals());
    }

}
