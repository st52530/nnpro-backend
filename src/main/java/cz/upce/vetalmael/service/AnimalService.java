package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.dto.AnimalDto;

public interface AnimalService {

    void addAnimal(AnimalDto animalDto);

    void editAnimal(AnimalDto animalDto);

    void removeAnimal(int idAnimal);
}
