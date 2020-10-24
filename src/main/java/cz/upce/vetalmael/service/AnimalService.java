package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.dto.AnimalDto;

public interface AnimalService {

    Animal addAnimal(AnimalDto animalDto);

    Animal editAnimal(AnimalDto animalDto);

    void removeAnimal(int idAnimal);
}
