package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.dto.AnimalDto;
import cz.upce.vetalmael.model.dto.SingUpDto;
import cz.upce.vetalmael.service.AnimalService;
import cz.upce.vetalmael.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/animale")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/add-animal")
    public void addAnimal(@RequestBody AnimalDto animalDto) {
        animalService.addAnimal(animalDto);
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/edit-animal")
    public void editAnimal(@RequestBody AnimalDto animalDto) {
        animalService.editAnimal(animalDto);
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/remove-animal/{idAnimal}")
    public void removeAnimal(@PathVariable int idAnimal) {
        animalService.removeAnimal(idAnimal);
    }
}
