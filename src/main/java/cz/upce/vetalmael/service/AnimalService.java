package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.dto.AnimalDto;

public interface AnimalService {

    Animal addAnimal(AnimalDto animalDto, int idUser);

    Animal editAnimal(AnimalDto animalDto, int idAnimal, int idUser);

    void removeAnimal(int idAnimal);
}
